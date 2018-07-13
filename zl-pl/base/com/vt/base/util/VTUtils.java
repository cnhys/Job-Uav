/**
 *
 */
package com.vt.base.util;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vt.base.service.ISysParamService;
import com.vt.user.model.Operator;

/**
 * <h1>VT tools</h1>
 *
 * @author Tony_Zhang
 * @version 1.0
 */
public class VTUtils {
    /**
     * get current operator
     *
     * @return
     */
    public static final Operator getCurrentOperator() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return null;
        }
        return (Operator) context.getAuthentication().getPrincipal();
    }

    public static String getParamValue(String paramCode) {
        ISysParamService sysParamService = SpringContextHolder.getBean(ISysParamService.class);
        return sysParamService.getParamValueByParamName(paramCode);
    }
}
