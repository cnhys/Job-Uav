package com.vt.base.service;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vt.base.OptResult;
import com.vt.base.model.Param;
import com.vt.base.model.ParamExample;

/**
 * @author july
 * @version V1.0
 * @Title: ISysParamService.java
 * @Package com.vt.base.service
 * @Description: TODO
 * @date Jun 3, 2015 2:58:27 PM
 */

public interface ISysParamService extends IBaseService<Param, ParamExample, String> {

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    OptResult delete(String entityId);

    /**
     * 根据参数名称获得参数值
     *
     * @return String
     * @author joy.qiao
     * @date Jul 7, 2015
     */
    public String getParamValueByParamName(String paramName);

    /**
     * 切换当前营业日
     *
     * @author jack.cheng
     */
    public void updateParamValueByPrimaryKey(Param param);

    /**
     * 插入当前营业日
     *
     * @author jack.cheng
     */
    public int insert(Param param);
    
    /**
     * 批量修改参数
     * @param list：参数list
     */
    public void updateParamBatch(List<Param> list);

}
