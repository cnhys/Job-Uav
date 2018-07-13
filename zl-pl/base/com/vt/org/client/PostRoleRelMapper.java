package com.vt.org.client;

import java.util.List;

import com.vt.base.mapper.IBaseMapper;
import com.vt.org.model.PostRoleRel;
import com.vt.org.model.PostRoleRelExample;

public interface PostRoleRelMapper extends IBaseMapper<PostRoleRel, PostRoleRelExample, Integer> {
    /**
     * insert postRoleRel batch
     *
     * @param postRoleRelList
     * @return
     */
    public int insertPostRoleRelBatch(List<PostRoleRel> postRoleRelList);
}