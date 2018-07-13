/**
 *
 */
package com.vt.base.interceptor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.vt.IConst;

/**
 * @author Tony_Zhang05
 */
@SuppressWarnings("deprecation")
public class SessionTimeoutInterceptor extends LoginUrlAuthenticationEntryPoint {

    /* (non-Javadoc)
     * @see org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint#commence(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException exception) throws IOException, ServletException {

        response.setHeader(IConst.SESSION_STATUS, IConst.SESSION_STATUS_VALID);

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if ("XMLHttpRequest".equals(httpRequest.getHeader("X-Requested-With"))) {
            SecurityContext context = SecurityContextHolder.getContext();
            if (context != null && context.getAuthentication() != null) {
                response.setHeader(IConst.SESSION_STATUS, IConst.SESSION_STATUS_VALID);
            } else {
                response.setHeader(IConst.SESSION_STATUS, IConst.SESSION_STATUS_INVALID);
            }
        } else {
            super.commence(request, response, exception);
        }
    }
}
