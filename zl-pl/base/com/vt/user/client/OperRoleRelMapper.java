package com.vt.user.client;

import java.math.BigDecimal;
import java.util.List;

import com.vt.base.exception.BizException;
import com.vt.base.mapper.IBaseMapper;
import com.vt.user.model.OperRoleRel;
import com.vt.user.model.OperRoleRelExample;

public interface OperRoleRelMapper extends IBaseMapper<OperRoleRel, OperRoleRelExample, BigDecimal> {
    /**
     * 查询用户的全部角色ID
     *
     * @param operatorId
     * @return
     * @throws BizException
     */
    public List<Integer> queryOperRoleIdByOperId(Integer operatorId) throws BizException;

    /**
     * 查看用户角色编码
     *
     * @param operatorId
     * @return
     */
    public List<String> queryOperRoleCodeByOperId(Integer operatorId);
}
