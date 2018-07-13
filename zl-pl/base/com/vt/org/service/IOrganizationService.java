package com.vt.org.service;

import java.util.List;
import java.util.Map;

import com.vt.base.service.IBaseService;
import com.vt.org.model.Organization;
import com.vt.org.model.OrganizationExample;

/**
 * <p> Title:机构管理服务接口</p>
 * <p> Description:提供机构管理模块：新增，修改，查询等服务接口</p>
 * <p> Copyright: Copyright (c) 2015 </p>
 * <p> Company:xxx.co.,ltd </p>
 *
 * @author john
 * @version 1.0
 */
public interface IOrganizationService extends IBaseService<Organization, OrganizationExample, Integer> {
    /**
     * 构建机构树
     *
     * @param orgId
     * @return
     */
    public List<Map<String, Object>> buildOrgnizationTree(Integer orgId);
}
