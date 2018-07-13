/**
 *
 */
package com.vt.user.service;

import java.io.Serializable;

import com.vt.base.OptResult;
import com.vt.user.model.Operator;

/**
 * 登录服务定义
 *
 * @author Tony_Zhang05
 * @version 1.0
 */
public interface ILoginService extends Serializable {
    /**
     * <h1>执行登录操作</h1>
     * <p>
     * 若登录失败，从errorCode和errorMessage中可获取错误信息
     * 若登录成功，返回数据中包含Operator对象
     * </p>
     *
     * @param operator
     * @return
     */
    public OptResult doLogin(Operator operator);
}
