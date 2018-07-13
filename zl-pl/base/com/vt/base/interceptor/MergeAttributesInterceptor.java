/**
 *
 */
package com.vt.base.interceptor;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.vt.IConst;
import com.vt.base.controller.BaseController;

/**
 * <h1>将HttpServletRequest或HttpSession的数据合并至模型拦截器</h1>
 *
 * @author tony
 * @version 1.0
 */
public class MergeAttributesInterceptor extends HandlerInterceptorAdapter {
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);

        logger.debug("进行属性合并，将HttpServletRequest和HttpSession的属性合并至模型中...");

        if (modelAndView == null) {
            logger.debug("无需进行属性合并...");
            return;
        }

        Map<String, Object> model = modelAndView.getModel();

        Enumeration<String> attributeNames = request.getAttributeNames();
        int total = 0;

        if (attributeNames != null) {
            while (attributeNames.hasMoreElements()) {
                String attributeName = attributeNames.nextElement();
                if (!model.containsKey(attributeName)) {
                    total++;
                    model.put(attributeName, request.getAttribute(attributeName));
                }
            }
            logger.debug("合并HttpServletRequest属性完成, 共合并属性：" + total + "项.");
        }

        HttpSession session = request.getSession();

        if (session != null) {
            attributeNames = session.getAttributeNames();
            total = 0;

            while (attributeNames != null && attributeNames.hasMoreElements()) {
                String attributeName = attributeNames.nextElement();
                if (!model.containsKey(attributeName)) {
                    total++;
                    model.put(attributeName, session.getAttribute(attributeName));
                }
            }
            logger.debug("合并HttpSession属性完成, 共合并属性：" + total + "项.");
        }

        model.put(IConst.CONTEXT_PATH, request.getContextPath() + "/");
        model.put(IConst.VERSON, BaseController.getAppVersion());

        SecurityContext context = SecurityContextHolder.getContext();
        if (context != null && context.getAuthentication() != null) {
            model.put(IConst.CURRENT_USER, context.getAuthentication().getPrincipal());
        }

        logger.debug("属性合并完成.");
    }
}
