package com.vt.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.vt.base.IServiceDef;
import com.vt.base.exception.BizException;
import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.base.util.StringUtil;
import com.vt.resource.model.Resource;
import com.vt.resource.service.IResourceService;
import com.vt.user.client.OperRoleRelMapper;
import com.vt.user.client.OperatorMapper;
import com.vt.user.model.OperRoleRel;
import com.vt.user.model.OperRoleRelExample;
import com.vt.user.model.Operator;
import com.vt.user.model.OperatorExample;
import com.vt.user.service.IOperatorService;
import com.vt.user.vo.OrgAndOperatorVO;
import com.vt.user.vo.RoleResOperVO;

/**
 * <p> Title:用户管理服务实现类</p>
 * <p> Description:提供用户管理模块：新增，修改，查询等服务实现</p>
 * <p> Copyright: Copyright (c) 2015 </p>
 * <p> Company:xxx.co.,ltd </p>
 *
 * @author john
 * @version 1.0
 */
@Service(IServiceDef.OPERATOR_SERVICE)
public class OperatorServiceImpl extends BaseService<Operator, OperatorExample, Integer> implements IOperatorService {

    private static final long serialVersionUID = 3570142679966633991L;

    @Autowired
    @Qualifier(IServiceDef.RESOURCE_SERVICE)
    private IResourceService resourceService;

    /**
     * sql session
     */
    @Autowired
    private SqlSession sqlSession;
    /**
     * mapper
     */
    private OperatorMapper mapper;

    private OperRoleRelMapper orMapper;
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * init
     */
    @PostConstruct
    public void init() {
        super.init();
        mapper = sqlSession.getMapper(OperatorMapper.class);
        orMapper = sqlSession.getMapper(OperRoleRelMapper.class);
        logger.info("PmOperator Service init successfully.");
    }

    /**
     * <p> Description: 通过用户信息查询用户与机构信息列表</p>
     *
     * @param operator
     * @return
     * @throws BizException
     */
    public List<OrgAndOperatorVO> getOrgAndOptListByOpt(Operator operator) throws BizException {
        List<OrgAndOperatorVO> orgAndOperatorVOList = mapper.selectOrgAndOptList(operator);
        return orgAndOperatorVOList;
    }

    /**
     * <p> Description: 通过用户信息查询用户与角色资源信息列表</p>
     *
     * @param operator
     * @return
     * @throws BizException
     */
    public List<RoleResOperVO> getRoleAndResListByOpt(Operator operator) throws BizException {
        List<RoleResOperVO> roleResOperVOList = mapper.selectRoleAndResList(operator);
        return roleResOperVOList;
    }

    /**
     * <p> Description: 通过用户信息查询资源及父资源信息列表</p>
     *
     * @param operator
     * @return
     * @throws BizException
     */
    public Set<Resource> getResourceByResSeq(Operator operator) throws BizException {
        //1、通过用户信息查询用户与角色资源信息列表
        List<RoleResOperVO> roleResOperVOList = mapper.selectRoleAndResList(operator);
        Set<Resource> resourcesSet = new HashSet<Resource>();
        List<Resource> resourceList = new ArrayList<Resource>();
        if (null != roleResOperVOList && roleResOperVOList.size() > 0) {
            for (RoleResOperVO roleResOperVO : roleResOperVOList) {
                //2、获取序号
                if (StringUtils.isNotBlank(roleResOperVO.getResSeq())) {
                    String[] resSeqStr = roleResOperVO.getResSeq().split(".");
                    //3、查询资源及父资源信息列表
                    resourceList = resourceService.queryResourcesList(resSeqStr);
                    resourcesSet.addAll(resourceList);
                }

            }
        }
        return resourcesSet;
    }

    @Override
    public IBaseMapper<Operator, OperatorExample, Integer> getMapper() {
        return mapper;
    }

    /**
     * 查询用户的全部角色ID
     */
    public List<Integer> getOperRoleRelListById(Integer operatorId)
            throws BizException {
        return orMapper.queryOperRoleIdByOperId(operatorId);
    }

    /**
     * 删除指定用户的角色
     */
    public int deleteOperRoleRelByOperId(Integer operatorId)
            throws BizException {
        OperRoleRelExample operRoleRelExam = new OperRoleRelExample();
        OperRoleRelExample.Criteria operCri = operRoleRelExam.createCriteria();
        operCri.andOperatorIdEqualTo(operatorId);
        return orMapper.deleteByExample(operRoleRelExam);
    }

    /**
     * 查询用户关联的角色数量
     */
    public int countOperRoleRelByOperId(Integer operatorId) throws BizException {
        OperRoleRelExample operRoleRelExa = new OperRoleRelExample();
        OperRoleRelExample.Criteria operRoleCri = operRoleRelExa.createCriteria();
        operRoleCri.andOperatorIdEqualTo(operatorId);
        return orMapper.countByExample(operRoleRelExa);
    }

    /**
     * 为用户添加角色
     */
    public int insertOperRoleRelByOperIdAndRoleId(OperRoleRel operRoleRel)
            throws BizException {
        return orMapper.insertSelective(operRoleRel);
    }

    /**
     * 查询用户的角色关联信息
     */
    public List<OperRoleRel> queryOperRoleRelByRoleId(int roleId)
            throws BizException {
        OperRoleRelExample operroleExam = new OperRoleRelExample();
        OperRoleRelExample.Criteria operCri = operroleExam.createCriteria();
        operCri.andRoleIdEqualTo(roleId);
        return orMapper.selectByExample(operroleExam);
    }

    /**
     * 根据角色Id和机构Id查询用户Id集合
     *
     * @param roleIds
     * @param orgId
     * @return
     */
    public List<String> queryUserIdsByRoleIdsAndOrgId(List<String> roleIds, Integer orgId) {
        List<String> userIdsList = new ArrayList<String>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleIds", roleIds);
        map.put("orgId", orgId);
        List<Operator> list = mapper.queryUserIdsByRoleIdsAndOrgId(map);
        if (list != null && list.size() > 0) {
            for (Operator operator : list) {
                userIdsList.add(operator.getUserName());
            }
        }
        return userIdsList;
    }

    /**
     * 根据角色编码查询用户列表 roleCode参数必输
     */
    public List<Operator> queryOperatorListByRoleCode(String userName, String fullName, String roleCode, String status) {
        if (StringUtil.isEmpty(roleCode)) {
            throw new BizException("systemmanage.user.filter.null");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("fullName", fullName);
        map.put("roleCode", roleCode);
        map.put("status", status);
        List<Operator> list = mapper.queryOperatorListByRoleCode(map);
        return list;
    }

    /**
     * 查询用户的全部角色编码
     */
    public List<String> queryOperRoleCodeByOperId(Integer operatorId) {
        return orMapper.queryOperRoleCodeByOperId(operatorId);
    }

	@Override
	public void saveOperator(Operator operator, String[] roles) {
		super.create(operator);
		if(roles!=null && roles.length>0){
			OperRoleRel orr=null;
			for(String role : roles){
				orr=new OperRoleRel();
				if(StringUtils.isNotEmpty(role)){
					orr.setRoleId(Integer.parseInt(role));
					orr.setOperatorId(operator.getOperatorId());
					orMapper.insert(orr);
				}
				
			}
		}
		
	}

	@Override
	public void updateOperator(Operator operator, String[] roles) {
		super.update(operator);
		//删除用户与角色之间的关系
		OperRoleRelExample operExample = new OperRoleRelExample();
		OperRoleRelExample.Criteria criteria = operExample.createCriteria();
		//operatorId
		criteria.andOperatorIdEqualTo(operator.getOperatorId());
		orMapper.deleteByExample(operExample);
		//重建用户与角色之间的关系
		if(roles!=null && roles.length>0){
			OperRoleRel orr=null;
			for(String role : roles){
				orr=new OperRoleRel();
				if(StringUtils.isNotEmpty(role)){
					orr.setRoleId(Integer.parseInt(role));
					orr.setOperatorId(operator.getOperatorId());
					orMapper.insert(orr);
				}
				
			}
		}
	}
}
