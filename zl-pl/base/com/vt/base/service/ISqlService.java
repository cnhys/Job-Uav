package com.vt.base.service;

import java.util.Map;

import com.vt.base.PageData;
import com.vt.base.PageRequest;
import com.vt.base.model.Param;
import com.vt.base.model.ParamExample;

/**
 * @author
 * @version V1.0
 * @Title: ISqlService.java
 * @Package com.vt.base.service
 * @Description: TODO
 * @date Jun 3, 2015 2:58:27 PM
 */

public interface ISqlService extends IBaseService<Param, ParamExample, String> {
    @SuppressWarnings("rawtypes")
    public PageData<Map> getBySql(PageRequest<String> pageRequest);

    public void insertBySql(String sql);

    public void updateBySql(String sql);

    public void deleteBySql(String sql);
}
