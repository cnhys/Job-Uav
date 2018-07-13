package com.vt.role.service;

import java.util.List;

import com.vt.base.OptResult;
import com.vt.base.PageData;
import com.vt.base.PageRequest;
import com.vt.base.annotation.SysControllerLog;
import com.vt.base.exception.BizException;
import com.vt.base.service.IBaseService;
import com.vt.role.RoleConst;
import com.vt.role.model.Role;
import com.vt.role.model.RoleExample;
import com.vt.role.model.RoleResRel;

/**
 * <p> Title:role manage service </p>
 * <p> Description: </p>
 *
 * @Author:Devin Bai
 * @Time:May 29, 20159:49:52 AM
 * @Version:1.00
 * @Record <pre>
 * Version 	Author	Time		describe
 * ----------------------------------------
 * 1.00		Devin	May 29, 2015	release
 * ----------------------------------------
 * </pre>
 */
public interface IRoleService extends IBaseService<Role, RoleExample, Integer> {

    /**
     * query role infos
     *
     * @param role  query condition
     * @param model model
     * @return role info list
     */
    public PageData<Role> roleManageFilterProcess(Role role, PageRequest<RoleExample> page);

    /**
     * save role info
     *
     * @param role to save role info
     * @return
     */
    @SysControllerLog(desc = RoleConst.SYS_SRV_LOG_DESC_SAVEROLE)
    public OptResult saveRole(Role role) throws BizException;

    /**
     * update role
     *
     * @param role to update role
     * @return
     */
    @SysControllerLog(desc = RoleConst.SYS_SRV_LOG_DESC_UPDATEROLE)
    public OptResult updateRole(Role role) throws BizException;

    /**
     * batch delete roles
     *
     * @param roleIds roleIds
     * @return
     * @throws BizException
     */
    @SysControllerLog(desc = RoleConst.SYS_SRV_LOG_DESC_DELETEROLE)
    public OptResult deleteRolesById(List<Integer> roleIdList) throws BizException;

    /**
     * judge roleCode is exist or not
     *
     * @param role role
     * @return ture-not exist;false exist
     */
    public String isRoleCodeNotExist(Role role);

    /**
     * query manage role list
     *
     * @param role role
     * @return manage role list
     * @throws BizException
     */
    public PageData<Role> queryMgrRoles(Role role);

    /**
     * query RoleResRel
     *
     * @param resRel condition
     * @return
     */
    public List<RoleResRel> queryRoleResRel(RoleResRel resRel);

    /**
     * @param roleId
     * @param resIds
     * @return
     * @throws BizException
     */
    public OptResult saveRoleResRels(Integer roleId, String resIds) throws BizException;

    /**
     * @param roleId
     * @param resIds
     * @return
     * @throws BizException
     */
    public int saveRoleResRelsList(List<RoleResRel> resRe) throws BizException;

    /**
     * @param iList
     * @return
     * @throws BizException
     */
    public List<RoleResRel> queryRoleResourcesList(List<Integer> iList) throws BizException;

    /**
     * @param roleId
     * @return
     * @throws BizException
     */
    public OptResult queryResIdByRoleId(Integer roleId) throws BizException;

    /**
     * @param roleId
     * @return
     * @throws BizException
     */
    public int deleteRoleResIdByRoleId(Integer roleId) throws BizException;

    /**
     * 根据角色id查询对应操作员id列表
     * add by joy at 2015-09-04
     *
     * @param roleId
     * @return
     */
    public List<Integer> selectOperatorsByRoleId(Integer roleId);
}
