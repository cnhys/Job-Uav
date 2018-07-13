/**
 *
 */
package com.vt.user.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.vt.base.IServiceDef;
import com.vt.base.OptResult;
import com.vt.user.model.Operator;
import com.vt.user.model.OperatorExample;
import com.vt.user.service.ILoginService;
import com.vt.user.service.IOperatorService;

/**
 * <h1>登录服务实现</h1>
 *
 * @author Tony_Zhang05
 * @version 1.0
 */
@Service(IServiceDef.LOGIN_SERVICE)
public class LoginServiceImpl implements ILoginService {
    private static final long serialVersionUID = -6817884535598718268L;
    /**
     * 操作员服务
     */
    @Autowired
    @Qualifier(IServiceDef.OPERATOR_SERVICE)
    private IOperatorService operatorService;

    /* (non-Javadoc)
     * @see com.vt.user.service.ILoginService#doLogin(com.vt.user.model.Operator)
     */
    public OptResult doLogin(Operator operator) {
        // 0. 检查参数
        if (operator == null) {
            return new OptResult(false, "login.operator.is.null");
        }
        if (StringUtils.isBlank(operator.getUserName())) {
            return new OptResult(false, "login.username.is.blank");
        }
        if (StringUtils.isBlank(operator.getPassword())) {
            return new OptResult(false, "login.password.is.blank");
        }
        // 1. 根据操作员登录名查询操作员信息
        OperatorExample example = new OperatorExample();
        example.createCriteria().andUserNameEqualTo(operator.getUserName());
        List<Operator> data = operatorService.getResult(example);
        if (data == null || data.size() == 0) {
            // 用户不存在
            return new OptResult(false, "login.user.not.exist");
        }
        Operator dbOper = data.get(0);
        // 2. 比较密码
        // TODO 增加加密算法
        if (!StringUtils.equals(dbOper.getPassword(), operator.getPassword())) {
            // 密码不匹配
            return new OptResult(false, "login.user.password.missmatch");
        }
        // 3. 存入至OptResult中
        OptResult result = new OptResult(true);
        result.setData(dbOper);
        return result;
    }
}
