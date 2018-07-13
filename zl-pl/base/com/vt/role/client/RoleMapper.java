package com.vt.role.client;

import java.util.List;

import com.vt.base.mapper.IBaseMapper;
import com.vt.role.model.Role;
import com.vt.role.model.RoleExample;

public interface RoleMapper extends IBaseMapper<Role, RoleExample, Integer> {
    /**
     * batch delete roles
     *
     * @param integers roleIds
     * @return
     */
    public int deleteRolesById(List<Integer> roleIdList);

    /**
     * 根据角色id查询对应操作员id列表
     * add by joy at 2015-09-04
     *
     * @param roleId
     * @return
     */
    public List<Integer> selectOperatorsByRoleId(Integer roleId);

}