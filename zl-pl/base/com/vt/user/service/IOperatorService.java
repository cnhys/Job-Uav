package com.vt.user.service;

import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vt.base.exception.BizException;
import com.vt.base.service.IBaseService;
import com.vt.resource.model.Resource;
import com.vt.user.model.OperRoleRel;
import com.vt.user.model.Operator;
import com.vt.user.model.OperatorExample;
import com.vt.user.vo.OrgAndOperatorVO;
import com.vt.user.vo.RoleResOperVO;

/**
 * <p> Title:用户管理服务接口</p>
 * <p> Description:提供用户管理模块：新增，修改，查询等服务接口</p>
 * <p> Copyright: Copyright (c) 2015 </p>
 * <p> Company:xxx.co.,ltd </p>
 *
 * @author john
 * @version 1.0
 */
public interface IOperatorService extends IBaseService<Operator, OperatorExample, Integer> {

    /**
     * <p> Description: 通过用户信息查询用户与机构信息列表</p>
     *
     * @param operator
     * @return
     * @throws BizException
     */
    public List<OrgAndOperatorVO> getOrgAndOptListByOpt(Operator operator) throws BizException;

    /**
     * <p> Description: 通过用户信息查询用户与角色资源信息列表</p>
     *
     * @param operator
     * @return
     * @throws BizException
     */
    public List<RoleResOperVO> getRoleAndResListByOpt(Operator operator) throws BizException;

    /**
     * <p> Description: 通过用户信息查询资源及父资源信息列表</p>
     *
     * @param operator
     * @return
     * @throws BizException
     */
    public Set<Resource> getResourceByResSeq(Operator operator) throws BizException;

    /**
     * 查询用户的全部角色ID
     *
     * @param operatorId
     * @return
     * @throws BizException
     */
    public List<Integer> getOperRoleRelListById(Integer operatorId) throws BizException;

    /**
     * 删除指定用户的角色
     *
     * @param operatorId
     * @return
     * @throws BizException
     */
    public int deleteOperRoleRelByOperId(Integer operatorId) throws BizException;

    /**
     * 查询用户关联的角色数量
     *
     * @param operatorId
     * @return
     * @throws BizException
     */
    public int countOperRoleRelByOperId(Integer operatorId) throws BizException;

    /**
     * 为用户添加角色
     *
     * @param operRoleRel
     * @return
     * @throws BizException
     */
    public int insertOperRoleRelByOperIdAndRoleId(OperRoleRel operRoleRel) throws BizException;

    /**
     * 查询用户的角色关联信息
     *
     * @param roleId
     * @return
     * @throws BizException
     */
    public List<OperRoleRel> queryOperRoleRelByRoleId(int roleId) throws BizException;

    /**
     * 根据角色Id和机构Id查询用户Id集合
     *
     * @param roleIds
     * @param orgId
     * @return
     */
    public List<String> queryUserIdsByRoleIdsAndOrgId(List<String> roleIds, Integer orgId);

    /**
     * 根据角色编码查询用户列表
     *
     * @param userName 登录名
     * @param fullName 真实姓名
     * @param roleCode 角色编码 参数必输
     * @param status   用户状态
     * @return
     */
    public List<Operator> queryOperatorListByRoleCode(String userName, String fullName, String roleCode, String status);

    /**
     * 查询用户全部角色编码
     *
     * @param operatorId
     * @return
     */
    public List<String> queryOperRoleCodeByOperId(Integer operatorId);
    
    /**
     * 保存用户
     * @param operator：用户信息
     * @param roles：角色信息
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public void saveOperator(Operator operator,String[] roles);
    
    /**
     * 修改用户
     * @param operator
     * @param roles
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public void updateOperator(Operator operator,String[] roles);

}
