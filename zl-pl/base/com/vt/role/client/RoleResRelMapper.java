package com.vt.role.client;

import java.util.List;

import com.vt.base.mapper.IBaseMapper;
import com.vt.role.model.RoleResRel;
import com.vt.role.model.RoleResRelExample;

public interface RoleResRelMapper extends IBaseMapper<RoleResRel, RoleResRelExample, Integer> {

    /**
     * insert roleResRel batch
     *
     * @param roleResRelList
     * @return
     */
    public int insertRoleResRelBatch(List<RoleResRel> roleResRelList);
}