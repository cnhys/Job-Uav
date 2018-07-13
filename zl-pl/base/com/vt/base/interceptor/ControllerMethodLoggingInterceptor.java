package com.vt.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 控制器方法执行拦截
 * Created by zhangtao on 16/1/11.
 */
public class ControllerMethodLoggingInterceptor extends HandlerInterceptorAdapter {
    /**
     * 是否记录日志
     */
    private boolean logging = false;
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * This implementation always returns {@code true}.
     *
     * @param request
     * @param response
     * @param handler
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod)handler;
            MethodParameter[] parameters = method.getMethodParameters();

            if (parameters != null && parameters.length == 3 && logging) {
                logger.info("执行控制器{}的方法{},使用的data参数为{}.", new Object[]{method.getBean().getClass().getName(), method.getMethod().getName(), request.getParameter("data")});
            }
        }
        return super.preHandle(request, response, handler);
    }

    public boolean isLogging() {
        return logging;
    }

    public void setLogging(boolean logging) {
        this.logging = logging;
    }
}
