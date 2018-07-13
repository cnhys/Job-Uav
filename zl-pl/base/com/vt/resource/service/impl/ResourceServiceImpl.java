package com.vt.resource.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.vt.base.BussConst;
import com.vt.base.IServiceDef;
import com.vt.base.OptResult;
import com.vt.base.PageData;
import com.vt.base.PageRequest;
import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.base.service.IDBUtilService;
import com.vt.resource.client.ResourceMapper;
import com.vt.resource.model.Resource;
import com.vt.resource.model.ResourceExample;
import com.vt.resource.service.IResourceService;
import com.vt.role.model.RoleResRel;
import com.vt.role.service.IRoleService;
import com.vt.user.model.Operator;
import com.vt.user.service.IOperatorService;

@Service(IServiceDef.RESOURCE_SERVICE)
public class ResourceServiceImpl extends BaseService<Resource, ResourceExample, Integer> implements
        IResourceService {

    private static final long serialVersionUID = -6587110962901760169L;

    @Autowired
    private SqlSession sqlSession;

    private ResourceMapper resourceMapper;

    @Autowired
    @Qualifier(IServiceDef.DBUTIL_SERVICE)
    private IDBUtilService dbUtilService;

    @Autowired
    @Qualifier(IServiceDef.OPERATOR_SERVICE)
    private IOperatorService operatorService;

    @Autowired
    @Qualifier(IServiceDef.ROLE_MANAGE_SERVICE)
    private IRoleService roleService;
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @PostConstruct
    public void init() {
        super.init();
        resourceMapper = sqlSession.getMapper(ResourceMapper.class);
        logger.info("PmResource Service init successfully.");
    }

    public PageData<Resource> queryResources(Resource condition) {
        PageRequest<ResourceExample> pageRequest = new PageRequest<ResourceExample>();
        pageRequest.setStart(0);
        pageRequest.setLimit(10);
        ResourceExample example = new ResourceExample();
//    	example.createCriteria().
        pageRequest.setCondition(example);
        PageData<Resource> data = super.query(pageRequest);
        return data;
    }

    @Override
    public OptResult saveResource(Resource resource) {
        if (resource.getParentId() == null || resource.getParentId().intValue() == 0) {
            resource.setResLevel(BussConst.ONE);
        } else {
            //设置资源级别
            Resource parentResource = resourceMapper.selectByPrimaryKey(resource.getParentId());
            resource.setResLevel(parentResource.getResLevel().intValue() + 1);
        }
        if (StringUtils.isNotBlank(resource.getResPath())) {
            resource.setIsLeaf("1");
        } else {
            resource.setIsLeaf("0");
        }
        resource.setStatus("1");
        OptResult result = super.create(resource);
        return result;
    }

    @Override
    public OptResult updateResource(Resource resource) {
        if (resource.getParentId() == null || resource.getParentId().intValue() == 0) {
            resource.setResLevel(BussConst.ONE);
        } else {
            //设置资源级别
            Resource parentResource = resourceMapper.selectByPrimaryKey(resource.getParentId());
            resource.setResLevel(parentResource.getResLevel().intValue() + 1);
        }
        if (StringUtils.isNotBlank(resource.getResPath())) {
            resource.setIsLeaf("1");
        } else {
            resource.setIsLeaf("0");
        }
        resource.setStatus("1");
        OptResult result = super.update(resource);
        return result;
    }

    /**
     * 操作员对应资源树封装
     */
    @Override
    public List<Map<String, Object>> queryRoleTreeMainResources(Operator oper) {
        List<Integer> iList = operatorService.getOperRoleRelListById(oper.getOperatorId());
        if (iList == null) {
            return null;
        }
        List<RoleResRel> roleList = roleService.queryRoleResourcesList(iList);
        if (roleList == null) {
            return null;
        }
        List<Integer> resourceList = new ArrayList<Integer>();
        for (RoleResRel roleRes : roleList) {
            resourceList.add(roleRes.getResId());
        }
        // TODO
        List<Map<String, Object>> mapList = queryTreeMainResources(1001, resourceList);
        return mapList;
    }

    /**
     * 资源树形成 - 主菜单
     *
     * @param resId
     * @param resourceList
     * @return
     */
    private List<Map<String, Object>> queryTreeMainResources(Integer resId, List<Integer> resourceList) {
        ResourceExample condition = new ResourceExample();
        condition.createCriteria().andParentIdEqualTo(resId);
        condition.createCriteria().andResIdIn(resourceList);
        List<Resource> list = this.getResult(condition);
        List<Map<String, Object>> treeNodes = new ArrayList<Map<String, Object>>();

        if (list != null && list.size() > 0) {
            for (Resource r : list) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", r.getResId());
                map.put("menuCName", r.getResName());
                map.put("iconCls", r.getImagepath());
                if ("1".equals(r.getIsLeaf())) {
                    map.put("isLeaf", true);
                }
                treeNodes.add(map);
            }
        } else {
            return null;
        }
        return treeNodes;
    }

    @Override
    public List<Map<String, Object>> queryTreeResources(Integer resId) {
		ResourceExample condition = new ResourceExample();
		if(resId != null && resId.intValue() == 0){
			condition.createCriteria().andParentIdEqualTo(BussConst.ZERO);
			condition.setOrderByClause("DISPLAY_ORDER");
		}else{
			condition.createCriteria().andParentIdEqualTo(resId);
			condition.setOrderByClause("DISPLAY_ORDER");
		}
		List<Resource> list = this.getResult(condition);
		List<Map<String,Object>> treeNodes = new ArrayList<Map<String,Object>>(); 
		
		if(list != null && list.size() > 0){
			for(Resource r : list){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", r.getResId());
				map.put("text", r.getResName());
				map.put("iconCls", r.getImagepath());
				if("1".equals(r.getIsLeaf())){
					map.put("isLeaf", true);
				}
				treeNodes.add(map);
				List<Map<String,Object>> list1 = queryTreeResources(r.getResId());
				if(list1 != null && list1.size() > 0){
					map.put("isLeaf", false);
					map.put("children", list1);
				}
				if(list1 == null || list1.size() == 0){
					continue;
				}
			}
		}else{
			return null;
		}
		
		return treeNodes;
    }

    /**
     * 操作员对应资源树封装
     */
    public List<Map<String, Object>> queryRoleTreeResources(Operator oper,Integer parentId) {
		ResourceExample condition = new ResourceExample();
		ResourceExample.Criteria criteria = condition.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		criteria.andStatusEqualTo("1");
		condition.setOrderByClause("DISPLAY_ORDER ASC");
		List<Resource> reList = this.getResult(condition);
		if(reList == null){
			return null;
		}
		List<Map<String,Object>> treeMap = getRoleTreeResources(parentId, oper);
		return treeMap;
    }

    /**
     * 角色对应资源树封装
     *
     * @param resId
     * @param oper
     * @return
     */
    @Override
    public List<Map<String, Object>> getRoleTreeResources(Integer resId, Operator oper) {

        List<Integer> iList = operatorService.getOperRoleRelListById(oper.getOperatorId());
        if (iList == null || iList.size() == 0) {
            return null;
        }
        List<RoleResRel> roleList = roleService.queryRoleResourcesList(iList);
        if (roleList == null || roleList.size() == 0) {
            return null;
        }
        List<Integer> resourceList = new ArrayList<Integer>();
        for (RoleResRel roleRes : roleList) {
            resourceList.add(roleRes.getResId());
        }
        List<Map<String, Object>> mapList = getRoleResourceTree(resId, resourceList);
        return mapList;

    }

    public List<Resource> queryResourcesList(String[] resIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("array", resIds);
        List<Resource> listresResources = resourceMapper.queryResourcesList(resIds);
        return listresResources;
    }

    @Override
    public IBaseMapper<Resource, ResourceExample, Integer> getMapper() {
        return resourceMapper;
    }

    /**
     * 资源树形成
     * 注释掉，新增同名方法支持easyui树
     * @param resId
     * @param resourceList
     * @return
     */
    /*private List<Map<String,Object>> getRoleResourceTree(Integer resId,List<Integer> resourceList){
		ResourceExample condition = new ResourceExample();
		ResourceExample.Criteria resCri = condition.createCriteria();
		resCri.andParentIdEqualTo(resId);
		resCri.andResIdIn(resourceList);
		resCri.andResTypeEqualTo("1");
		List<Resource> list = this.getResult(condition);
		List<Map<String,Object>> treeNodes = new ArrayList<Map<String,Object>>(); 
		
		if(list != null && list.size() > 0){
			for(Resource r : list){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", r.getResId());
				map.put("menuCName", r.getResName());
				map.put("iconCls", r.getImagepath());
				map.put("url", r.getResPath());
				if("1".equals(r.getIsLeaf())){
					map.put("isLeaf", true);
				}
				treeNodes.add(map);
				List<Map<String,Object>> list1 = getRoleResourceTree(r.getResId(),resourceList);
				if(list1 != null && list1.size() > 0){
					map.put("isLeaf", false);
					map.put("children", list1);
				}
				if(list1 == null || list1.size() == 0){
					continue;
				}
			}
		}else{
			return null;
		}
		
		return treeNodes;
	}*/

    /**
     * 资源树形成
     *
     * @param resId
     * @param resourceList
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private List<Map<String, Object>> getRoleResourceTree(Integer resId, List<Integer> resourceList) {
        ResourceExample condition = new ResourceExample();
        ResourceExample.Criteria resCri = condition.createCriteria();
        resCri.andParentIdEqualTo(resId);
        resCri.andResIdIn(resourceList);
        resCri.andResTypeEqualTo("1");
        List<Resource> list = this.getResult(condition);
        List<Map<String, Object>> treeNodes = new ArrayList<Map<String, Object>>();

        if (list != null && list.size() > 0) {
            for (Resource r : list) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", r.getResId());
                map.put("text", r.getResName());
                Map attributes = new HashMap();
                attributes.put("url", r.getResPath());
                map.put("attributes", attributes);
                treeNodes.add(map);
                List<Map<String, Object>> list1 = getRoleResourceTree(r.getResId(), resourceList);
                if (list1 != null && list1.size() > 0) {
                    map.put("children", list1);
                }
                if (list1 == null || list1.size() == 0) {
                    continue;
                }
            }
        } else {
            return null;
        }

        return treeNodes;
    }

    @Override
    public List<String> queryUserBtnResources(Operator oper) {
        ResourceExample condition = new ResourceExample();
        condition.createCriteria().andParentIdEqualTo(BussConst.ZERO);
        List<Resource> reList = this.getResult(condition);
        if (reList == null) {
            return null;
        }

        List<Integer> iList = operatorService.getOperRoleRelListById(oper.getOperatorId());
        if (iList == null) {
            return null;
        }
        List<RoleResRel> roleList = roleService.queryRoleResourcesList(iList);
        if (roleList == null) {
            return null;
        }
        List<Integer> resourceList = new ArrayList<Integer>();
        for (RoleResRel roleRes : roleList) {
            resourceList.add(roleRes.getResId());
        }

        ResourceExample conditionRes = new ResourceExample();
        ResourceExample.Criteria resCri = conditionRes.createCriteria();
        resCri.andResIdIn(resourceList);
        resCri.andResTypeEqualTo("2");
        List<Resource> list = this.getResult(conditionRes);
        List<String> resultList = new ArrayList<String>();

        if (list != null && list.size() > 0) {
            for (Resource r : list) {
                resultList.add(r.getResPath());
            }
        } else {
            return null;
        }
        return resultList;
    }
}
