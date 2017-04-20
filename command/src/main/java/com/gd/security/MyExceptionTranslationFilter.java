package com.gd.security;

import com.gd.util.MySessionMapUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.ExceptionTranslationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于捕获用户访问url但没有登录的异常，因为token无session验证，每次请求都将视为无session
 * 所以都会被这个类捕获到这个异常
 */
public class MyExceptionTranslationFilter  extends ExceptionTranslationFilter {
    public MyExceptionTranslationFilter(AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationEntryPoint);
    }
    //捕获异常由这个方法处理
    @Override
    public void sendStartAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, AuthenticationException reason)
            throws ServletException, IOException {
        String token = "";
        if(request.getHeader("token") == null || request.getHeader("token").equals("")){
            response.sendError(401,"Missing token");
        }else {
            token = request.getHeader("token");
            if(MySessionMapUtils.getMySessionMap().containsKey(token)){
                super.sendStartAuthentication(request, response, chain, reason);
            }else{
                response.sendError(401,"No exist token or invalid token");
            }
        }
        //super.sendStartAuthentication(request, response, chain, reason);
        System.out.println("调用了我的sendStartAuthentication处理用户未登录！！！");
    }
}

