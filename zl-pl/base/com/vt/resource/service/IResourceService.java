package com.vt.resource.service;

import java.util.List;
import java.util.Map;

import com.vt.base.OptResult;
import com.vt.base.PageData;
import com.vt.base.service.IBaseService;
import com.vt.resource.model.Resource;
import com.vt.resource.model.ResourceExample;
import com.vt.user.model.Operator;

public interface IResourceService extends IBaseService<Resource, ResourceExample, Integer> {

    /**
     * 查询资源列表数据
     *
     * @param condition
     * @return
     */
    public PageData<Resource> queryResources(Resource condition);

    /**
     * 保存资源数据
     *
     * @param resource
     */
    public OptResult saveResource(Resource resource);

    /**
     * 更新资源数据
     *
     * @param resource
     * @return
     */
    public OptResult updateResource(Resource resource);

    /**
     * 获取用户首层菜单
     *
     * @param resId
     * @return
     */
    public List<Map<String, Object>> queryRoleTreeMainResources(Operator oper);

    /**
     * 获取顶级菜单的子级菜单
     *
     * @param resId
     * @param oper
     * @return
     */
    public List<Map<String, Object>> getRoleTreeResources(Integer resId, Operator oper);

    /**
     * 查询树节点数据
     *
     * @param resId    资源ID
     * @param parentId 资源父ID
     * @return 资源列表
     */
    public List<Map<String, Object>> queryTreeResources(Integer resId);

    /**
     * 根据资源ID列表查询资源明细列表
     *
     * @param resIds
     * @return
     */
    public List<Resource> queryResourcesList(String[] resIds);

    /**
     * 获取角色对应的资源树
     *
     * @param oper
     * @return
     */
    public List<Map<String, Object>> queryRoleTreeResources(Operator oper,Integer parentId);

    /**
     * 获取用户对应的按钮列表
     *
     * @param oper
     * @return
     */
    public List<String> queryUserBtnResources(Operator oper);
}
