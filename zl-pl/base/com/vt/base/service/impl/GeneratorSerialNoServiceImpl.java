package com.vt.base.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSession;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.vt.base.IServiceDef;
import com.vt.base.OptResult;
import com.vt.base.client.BasSerialnoMapper;
import com.vt.base.exception.BizException;
import com.vt.base.mapper.IBaseMapper;
import com.vt.base.model.BasSerialno;
import com.vt.base.model.BasSerialnoExample;
import com.vt.base.service.BaseService;
import com.vt.base.service.IDBUtilService;
import com.vt.base.service.IGeneratorSerialNoService;
import com.vt.base.util.DateUtil;

/**
 * @author july
 * @version V1.0
 * @Title: GeneratorSerialNoServiceImpl.java
 * @Package com.vt.base.service.impl
 * @Description: TODO
 * @date Jul 1, 2015 2:38:41 PM
 */
@Service(IServiceDef.SERIALNO_SERVICE)
public class GeneratorSerialNoServiceImpl extends BaseService<BasSerialno, BasSerialnoExample, Long> implements IGeneratorSerialNoService {

    /**
     *
     */
    private static final long serialVersionUID = -7788471461923057396L;

    /**
     * sql session
     */
    @Autowired
    private SqlSession sqlSession;

    /**
     * mapper
     */
    private BasSerialnoMapper mapper;
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 默认值
     */
    private Long defaultSerialValue = Long.valueOf("1");
    /**
     * 缓存大小
     */
    private int serialCacheSize = 50;
    /**
     * 缓存大小
     */
    private int serialSize = 1;
    @Autowired
    @Qualifier(IServiceDef.DBUTIL_SERVICE)
    private IDBUtilService dbUtilService;
    /**
     * 流水号集合
     */
    private static ConcurrentHashMap<String, BasSerialno> serialCacheMap = new ConcurrentHashMap<String, BasSerialno>();

    /**
     * init
     */


    @PostConstruct
    public void init() {
        super.init();
        mapper = sqlSession.getMapper(BasSerialnoMapper.class);
        logger.info("BasSerialno Service init successfully.");
    }

    @Override
    public IBaseMapper<BasSerialno, BasSerialnoExample, Long> getMapper() {
        return mapper;
    }

    /**
     * 根据交易编号每天从1生成流水
     */
    @Override
    public Long getSerialnoByTradeCodeAndDate(String tradeCode) {
        //TODO 后续可以考虑从session中获取当前营业日

        BasSerialno basSerialno = serialCacheMap.get(tradeCode);
        if (null == basSerialno) {
            BasSerialno pk = this.queryCacheBasSerialnoValue(tradeCode, new Date());
            serialCacheMap.put(tradeCode, pk);
            return pk.getSerialValue();
        } else {
            if (basSerialno.getBussDate().compareTo(new Date()) == 0) {
                Long value = basSerialno.getCurrentValue() + 1;
                if (value > Long.valueOf(basSerialno.getCurrentValue())) {
                    basSerialno.setSerialValue(value);
                    basSerialno.setCurrentValue(value + serialCacheSize);
                    mapper.updateByPrimaryKeySelective(basSerialno);
                } else {
                    basSerialno.setSerialValue(value);
                }
                serialCacheMap.put(tradeCode, basSerialno);
                return value;
            } else {
                BasSerialno record = new BasSerialno();
                record.setCurrentValue(Long.valueOf(serialCacheSize));
                record.setSerialnoCode(tradeCode);
                record.setSerialValue(defaultSerialValue);
                record.setBussDate(new Date());
//				 mapper.insert(record);
                this.create(record);

                return defaultSerialValue;
            }
        }
    }

    /**
     * 根据交易编累加生成流水
     */
    @Override
    public Long getSerialnoByTradeCode(String tradeCode) {

        BasSerialno basSerialno = serialCacheMap.get(tradeCode);
        if (null == basSerialno) {
            BasSerialno pk = this.queryCacheBasSerialnoValue(tradeCode, null);
            serialCacheMap.put(tradeCode, pk);
            return pk.getSerialValue();
        } else {
            Long value = basSerialno.getSerialValue() + 1;
            if (value > basSerialno.getCurrentValue()) {
                basSerialno.setSerialValue(value);
                basSerialno.setCurrentValue(value + serialCacheSize);
                mapper.updateByPrimaryKeySelective(basSerialno);
            } else {
                basSerialno.setSerialValue(value);
            }
            serialCacheMap.put(tradeCode, basSerialno);
            return value;
        }
    }

    public BasSerialno queryCacheBasSerialnoValue(String tradeCode, Date date) throws BizException {
        BasSerialno record = new BasSerialno();
        BasSerialnoExample example = new BasSerialnoExample();
        if (null == date) {
            example.createCriteria().andSerialnoCodeEqualTo(tradeCode);
        } else {
            example.createCriteria().andSerialnoCodeEqualTo(tradeCode).andBussDateEqualTo(date);
        }
        List<BasSerialno> list = mapper.selectByExample(example);
        if (list.size() > 0) {
            System.out.println("SERIALNO CURRENT VALUE:" + list.get(0).getCurrentValue());
            Long currentValue = list.get(0).getCurrentValue();
            record.setId(list.get(0).getId());
            record.setCurrentValue(serialCacheSize + currentValue);
            record.setSerialnoCode(tradeCode);
            record.setBussDate(date);
            record.setSerialValue(currentValue + 1);
            mapper.updateByPrimaryKeySelective(record);
        } else {
            record.setCurrentValue(Long.valueOf(serialCacheSize));
            record.setSerialnoCode(tradeCode);
            record.setSerialValue(defaultSerialValue);
            record.setBussDate(date);
//			 mapper.insert(record);
            OptResult result = this.create(record);
            if (!result.isSuccess()) {
                throw new BizException("插入流水表失败！");
            }
        }
        return record;

    }


    public String generatorSerialNo(String tradeCode) {


        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("serialno-rules");
        System.out.println("111111111111111111111111111111111111111" + tradeCode);

        // System.out.println("00000000000000000-----:"+this.getSerialnoByTradeCode(tradeCode));
        kSession.setGlobal("serialService", this);
        BasSerialno sobj = new BasSerialno();
        sobj.setSerialnoCode(tradeCode);
        kSession.insert(sobj);
        kSession.fireAllRules();

        System.out.println("2222222222222222222222222222222222222222222222");
        System.out.println(sobj.getSerialNo());
        return sobj.getSerialNo();
    }

    @Override
    public String createBizFlowNoByTradeCode(String tradeCode) throws BizException {
        //TODO 后续可以考虑从session中获取当前营业日
        String flow = null;
        Long flowL = getSerialnoByTradeCodeAndDate2(tradeCode);
        //如果生成的序列号flow不够7位，前面补0
        if (flowL.toString().length() < 7) {
            flow = String.format("%07d", flowL);
        } else {
            flow = flowL.toString();
        }
        return tradeCode + DateUtil.getBussDate() + flow;
    }

    private Long getSerialnoByTradeCodeAndDate2(String tradeCode) throws BizException {
        //TODO 后续可以考虑从session中获取当前营业日

        BasSerialno basSerialno = serialCacheMap.get(tradeCode);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (null == basSerialno) {
            BasSerialno pk = this.queryCacheBasSerialnoValue2(tradeCode, new Date());
            serialCacheMap.put(tradeCode, pk);
            return pk.getSerialValue();
        } else {
            String bussDate = format.format(basSerialno.getBussDate());
            String curDate = format.format(new Date());
            if (bussDate.equals(curDate)) {
                Long value = basSerialno.getCurrentValue() + 1;
                if (value > Long.valueOf(basSerialno.getCurrentValue())) {
                    basSerialno.setSerialValue(value);
                    basSerialno.setCurrentValue(value);
                    mapper.updateByPrimaryKeySelective(basSerialno);
                } else {
                    basSerialno.setSerialValue(value);
                }
                serialCacheMap.put(tradeCode, basSerialno);
                return value;
            } else {
                BasSerialno record = new BasSerialno();
                record.setCurrentValue(Long.valueOf(serialSize));
                record.setSerialnoCode(tradeCode);
                record.setSerialValue(defaultSerialValue);
                record.setBussDate(new Date());
//				 mapper.insert(record);
                OptResult result = this.create(record);
                if (!result.isSuccess()) {
                    throw new BizException("插入流水表失败！");
                }

                return defaultSerialValue;
            }
        }
    }

    public BasSerialno queryCacheBasSerialnoValue2(String tradeCode, Date date) throws BizException {
        BasSerialno record = new BasSerialno();
        BasSerialnoExample example = new BasSerialnoExample();
        if (null == date) {
            example.createCriteria().andSerialnoCodeEqualTo(tradeCode);
        } else {
            example.createCriteria().andSerialnoCodeEqualTo(tradeCode).andBussDateEqualTo(date);
        }
        List<BasSerialno> list = mapper.selectByExample(example);
        if (list.size() > 0) {
            System.out.println("SERIALNO CURRENT VALUE:" + list.get(0).getCurrentValue());
            Long currentValue = list.get(0).getCurrentValue();
            record.setId(list.get(0).getId());
            record.setCurrentValue(serialSize + currentValue);
            record.setSerialnoCode(tradeCode);
            record.setBussDate(date);
            record.setSerialValue(currentValue + 1);
            mapper.updateByPrimaryKeySelective(record);
        } else {
            record.setCurrentValue(Long.valueOf(serialSize));
            record.setSerialnoCode(tradeCode);
            record.setSerialValue(defaultSerialValue);
            record.setBussDate(date);
//			 mapper.insert(record);
            OptResult result = this.create(record);
            if (!result.isSuccess()) {
                throw new BizException("插入流水表失败！");
            }
        }
        return record;

    }

    /**
     * 根据交易码生成序号
     */
    @Override
    public Long getSerialnoByTradeCode2(String tradeCode) {

        BasSerialno basSerialno = serialCacheMap.get(tradeCode);
        if (null == basSerialno) {
            BasSerialno pk = this.queryCacheBasSerialnoValue2(tradeCode, null);
            serialCacheMap.put(tradeCode, pk);
            return pk.getSerialValue();
        } else {
            Long value = basSerialno.getSerialValue() + 1;
            if (value > basSerialno.getCurrentValue()) {
                basSerialno.setSerialValue(value);
                basSerialno.setCurrentValue(value);
                mapper.updateByPrimaryKeySelective(basSerialno);
            } else {
                basSerialno.setSerialValue(value);
            }
            serialCacheMap.put(tradeCode, basSerialno);
            return value;
        }
    }

}
