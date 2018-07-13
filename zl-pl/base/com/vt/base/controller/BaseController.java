package com.vt.base.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vt.IConst;
import com.vt.base.OptResult;
import com.vt.base.util.DateUtil;
import com.vt.user.model.Operator;

/**
 * <h1>基础控制器</h1>
 * <p>框架的基础控制器，提供可复用的基本方法</p>
 * Created by zhangtao on 15/5/27.
 */
@Controller
public class BaseController implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7216684204355799688L;

    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${app.version}")
    private String version;

    private static String _version;

    /**
     * 获取应用版本号
     *
     * @return
     */
    public static String getAppVersion() {
        return _version;
    }

    @PostConstruct
    public void init() {
        if (StringUtils.isBlank(_version)) {
            _version = version == null ? DateUtil.getCurrentDateTimeMillisStr() : version;
        }
    }

    /**
     * 初始化模型
     *
     * @param model
     */
    protected void initModel(Model model) {

    }


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
     * 错误处理方法
     *
     * @return
     */
    @ExceptionHandler(Exception.class)
    public OptResult onError(Exception ex, HttpServletRequest request) {
        // model
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("exception", ex);
        model.put("message", ex.getMessage());
        model.put(IConst.CONTEXT_PATH, request.getAttribute(IConst.CONTEXT_PATH));

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.put(IConst.CURRENT_USER, userDetails);
        logger.error(ex.getMessage(), ex);
        // mav
        return OptResult.failure("system.error");
    }
}
