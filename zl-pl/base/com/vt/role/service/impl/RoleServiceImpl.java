package com.vt.role.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.vt.IConst;
import com.vt.base.IServiceDef;
import com.vt.base.OptResult;
import com.vt.base.PageData;
import com.vt.base.PageRequest;
import com.vt.base.exception.BizException;
import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.base.service.IDBUtilService;
import com.vt.base.util.ListUtil;
import com.vt.base.util.StringUtil;
import com.vt.role.client.RoleMapper;
import com.vt.role.client.RoleResRelMapper;
import com.vt.role.model.Role;
import com.vt.role.model.RoleExample;
import com.vt.role.model.RoleResRel;
import com.vt.role.model.RoleResRelExample;
import com.vt.role.service.IRoleService;

/**
 * <p> Title:Role Service impl </p>
 * <p> Description: </p>
 *
 * @Author:Devin Bai
 * @Time:May 29, 20159:50:22 AM
 * @Version:1.00
 * @Record <pre>
 * Version 	Author	Time		describe
 * ----------------------------------------
 * 1.00		Devin	May 29, 2015	release
 * ----------------------------------------
 * </pre>
 */
@Service(IServiceDef.ROLE_MANAGE_SERVICE)
public class RoleServiceImpl extends BaseService<Role, RoleExample, Integer> implements IRoleService {
    /**
     * Role Service
     */
    private static final long serialVersionUID = 339714035634623025L;

    /**
     * db util service
     */
    @Autowired
    @Qualifier(IServiceDef.DBUTIL_SERVICE)
    private IDBUtilService dbUtilService;
    /**
     * sql session
     */
    @Autowired
    private SqlSession sqlSession;
    /**
     * mapper
     */
    private RoleMapper mapper;
    /**
     * RoleResRelMapper
     */
    private RoleResRelMapper relMapper;
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
        mapper = sqlSession.getMapper(RoleMapper.class);
        relMapper = sqlSession.getMapper(RoleResRelMapper.class);
        logger.info("Role Service init successfully.");
    }

    /**
     * override,get mapper
     */
    @Override
    public IBaseMapper<Role, RoleExample, Integer> getMapper() {
        return mapper;
    }

    @Override
    public PageData<Role> roleManageFilterProcess(Role role, PageRequest<RoleExample> page) {
        // 1. package query conditions
        RoleExample example = new RoleExample();
        if (StringUtils.isNotBlank(role.getRoleCode())) {
            example.and().andRoleCodeLike(StringUtil.dbQueryLike(role.getRoleCode()));
        }
        if (StringUtils.isNotBlank(role.getRoleName())) {
            example.and().andRoleNameLike(StringUtil.dbQueryLike(role.getRoleName()));
        }
        if (StringUtils.isNotBlank(role.getStatus()) && !"2".equals(role.getStatus())) {
            example.and().andStatusEqualTo(role.getStatus());
        }
        page.setCondition(example);
        // 2. query
        return query(page);
    }

    @Override
    public OptResult saveRole(Role role) throws BizException {
        // 1. validate
        if (role == null) {
            throw new BizException("systemmanage.role.save.null");
        }

        // 2. build role time info
        role.setCreateDate(new Date());
        role.setLastupDate(new Date());

        // 4. save role
        return create(role);
    }

    @Override
    public OptResult updateRole(Role role) throws BizException {
        // 1. validate
        if (role == null) {
            throw new BizException("systemmanage.role.update.null");
        }
        if (role.getRoleId() == null || role.getRoleId() == 0) {
            throw new BizException("systemmanage.role.update.id.null");
        }

        // 2. build role time info
        role.setLastupDate(new Date());

        // 3. update role
        return update(role);
    }

    @Override
    public OptResult deleteRolesById(List<Integer> roleIdList) throws BizException {
        // 1. validate
        if (ListUtil.isEmpty(roleIdList)) {
            throw new BizException("systemmanage.role.delete.null");
        }
        int count = mapper.deleteRolesById(roleIdList);
        if (count > 0) {
            return new OptResult(true);
        }
        return new OptResult(false, "database.error");
    }

    @Override
    public String isRoleCodeNotExist(Role role) {
        // 1. validate
        if (role == null || StringUtils.isBlank(role.getRoleCode())) {
            return Boolean.TRUE.toString();
        }

        // 2. build query conditions
        RoleExample example = new RoleExample();
        example.and().andRoleCodeEqualTo(role.getRoleCode());

        // 3. get the count and judge
        int count = mapper.countByExample(example);
        if (count > 0) {
            return Boolean.FALSE.toString();
        }

        return Boolean.TRUE.toString();
    }

    @Override
    public PageData<Role> queryMgrRoles(Role role) {
        // 1. init PageData
        PageData<Role> result = new PageData<Role>();
        result.setSuccess(true);
        result.setLimit(10);
        result.setStart(0);
        result.setTotal(100);

        // 2. validate
        if (role == null || StringUtils.isBlank(role.getMgrRoleIdList())) {
            result.setSuccess(false);
            return result;
        }

        // 3. build query conditions
        RoleExample example = new RoleExample();
        example.and().andRoleIdIn(ListUtil.strToIntegerList(role.getMgrRoleIdList(), IConst.REGEX_COMMA));

        // 4. query
        List<Role> roleList = getResult(example);
        result.setData(roleList);

        return result;
    }

    @Override
    public OptResult saveRoleResRels(Integer roleId, String resIds) throws BizException {
        // 1. validate
        if (roleId == null || roleId == 0 || StringUtils.isBlank(resIds)) {
            return new OptResult(false, "param.null");
        }

        // 2. delete all old data
        RoleResRelExample example = new RoleResRelExample();
        example.and().andRoleIdEqualTo(roleId);
        relMapper.deleteByExample(example);

        // 3. build batch insert list
        List<RoleResRel> resRelList = new ArrayList<RoleResRel>();
        String[] resIdArr = resIds.split(",");
        for (String resId : resIdArr) {
            RoleResRel roleResRel = new RoleResRel();
            roleResRel.setRoleId(roleId);
            roleResRel.setResId(Integer.parseInt(resId));
            resRelList.add(roleResRel);
        }

        // 4. insert
        int count = relMapper.insertRoleResRelBatch(resRelList);
        if (count > 0) {
            return new OptResult(true);
        }
        return new OptResult(false, "database.error");
    }

    @Override
    public List<RoleResRel> queryRoleResRel(RoleResRel resRel) {
        // 1. validate
        if (resRel == null) {
            return null;
        }

        // 2. build condition
        RoleResRelExample example = new RoleResRelExample();
        if (resRel.getRoleId() != null) {
            example.and().andRoleIdEqualTo(resRel.getRoleId());
        }
        if (resRel.getResId() != null) {
            example.and().andResIdEqualTo(resRel.getResId());
        }

        return relMapper.selectByExample(example);
    }

    @Override
    public int saveRoleResRelsList(List<RoleResRel> resRe) throws BizException {
        return relMapper.insertRoleResRelBatch(resRe);
    }

    @Override
    public List<RoleResRel> queryRoleResourcesList(List<Integer> iList)
            throws BizException {
        RoleResRelExample roleResExam = new RoleResRelExample();
        RoleResRelExample.Criteria roleResCri = roleResExam.createCriteria();
        roleResCri.andRoleIdIn(iList);
        List<RoleResRel> roleList = relMapper.selectByExample(roleResExam);
        return roleList;
    }

    @Override
    public OptResult queryResIdByRoleId(Integer roleId) throws BizException {
        OptResult opResult = new OptResult(true);
        String result = "";
        RoleResRelExample roleResExam = new RoleResRelExample();
        RoleResRelExample.Criteria roleResCri = roleResExam.createCriteria();
        roleResCri.andRoleIdEqualTo(roleId);
        List<RoleResRel> roleResList = relMapper.selectByExample(roleResExam);
        if (roleResList == null) {
            opResult.setData(result);
            return opResult;
        }
        for (RoleResRel roleRes : roleResList) {
            result = result + roleRes.getResId() + ",";
        }
        opResult.setData(result);
        return opResult;
    }

    @Override
    public int deleteRoleResIdByRoleId(Integer roleId) throws BizException {
        RoleResRelExample roleResExam = new RoleResRelExample();
        RoleResRelExample.Criteria roleResCri = roleResExam.createCriteria();
        roleResCri.andRoleIdEqualTo(roleId);
        int result = relMapper.deleteByExample(roleResExam);
        return result;
    }

    @Override
    public List<Integer> selectOperatorsByRoleId(Integer roleId) {
        return mapper.selectOperatorsByRoleId(roleId);
    }

}
