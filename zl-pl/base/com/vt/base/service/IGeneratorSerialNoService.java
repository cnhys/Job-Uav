package com.vt.base.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vt.base.model.BasSerialno;
import com.vt.base.model.BasSerialnoExample;

/**
 * @author july
 * @version V1.0
 * @Title: IGeneratorSerialNoService.java
 * @Package com.vt.base.service
 * @Description: TODO
 * @date Jul 1, 2015 2:29:32 PM
 */

public interface IGeneratorSerialNoService extends IBaseService<BasSerialno, BasSerialnoExample, Long> {
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public Long getSerialnoByTradeCodeAndDate(String tradeCode);

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public Long getSerialnoByTradeCode(String tradeCode);


    public String generatorSerialNo(String tradeCode);

    /**
     * 用于生成业务流水号
     * 生成规则：
     * 接口编码（4位）+日期（8位）+序列号（7位流水）
     * 其中每个接口的序列号每天从1开始重新生成
     *
     * @param tradeCode
     * @return
     */
    public String createBizFlowNoByTradeCode(String tradeCode);

    /**
     * 根据交易码生成序号
     *
     * @param tradeCode
     * @return
     */
    public Long getSerialnoByTradeCode2(String tradeCode);

}
