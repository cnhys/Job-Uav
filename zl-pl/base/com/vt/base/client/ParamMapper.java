package com.vt.base.client;

import java.util.List;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.model.Param;
import com.vt.base.model.ParamExample;

public interface ParamMapper extends IBaseMapper<Param, ParamExample, String> {

    /**
     * 根据参数名称获得参数值
     *
     * @param paramName
     * @return
     */
    public Param selectByPrimaryKey(String paramName);

    /**
     * 切换当前营业日
     *
     * @param param
     * @return
     */
    public void updateParamValueByPrimaryKey(Param param);
    
    /**
     * 批量修改参数
     * @param list：参数list
     */
    public void updateParamBatch(List<Param> list);

}