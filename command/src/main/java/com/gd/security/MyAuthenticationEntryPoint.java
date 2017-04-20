package com.gd.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 需要注入到MyExceptionTranslationFilter中，重写commence方法，提示当前token没有访问url权限
 */
public class MyAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
    public MyAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendError(401,"sorry,no privilege!");
    }
}