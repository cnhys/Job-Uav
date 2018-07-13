package com.vt.base.controller;

import java.io.Serializable;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vt.base.OptResult;
import com.vt.base.exception.BizException;
import com.vt.user.model.Operator;

/**
 * <h1>基于REST的基控制器</h1>
 * Created by zhangtao on 15/5/27.
 */
@Controller
public class BaseRestController implements Serializable {
    private static final long serialVersionUID = 8223280557392635921L;

    /**
     * 获取当前已登录用户
     *
     * @return
     */
    public Operator getCurrentOperator() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return null;
        }
        return (Operator) context.getAuthentication().getPrincipal();
    }

    /**
     * 处理Ajax异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public OptResult onError(Exception ex) {
        OptResult result = new OptResult(false, ex.getMessage());
        result.setReturnCode(ex.getMessage());
        result.setMessage(ex.getMessage());
        ex.printStackTrace();
        if (ex instanceof BizException) {
            BizException exception = (BizException) ex;
            result.setReturnCode(exception.getErrorCode());
            result.setMessage(exception.getErrorMessage());
        }
        return result;
    }
}
