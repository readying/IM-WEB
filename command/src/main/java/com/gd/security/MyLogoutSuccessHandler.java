package com.gd.security;

import com.gd.util.MySessionMapUtils;
import com.gd.util.MyUsernameTokenUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义注销方法，移除token
 */
public class MyLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("调用了我的注销成功！！！");
        //注销需要username和token
        String username = request.getHeader("username");
        String token = request.getHeader("token");
        if(token == null || token.equals("")){
            response.sendError(401,"Missing token");
        }else if(username == null || username.equals("")){
            response.sendError(401,"Missing username");
        }else{
            MySessionMapUtils.getMySessionMap().remove(token);
            MyUsernameTokenUtils.getMyUsernameTokenMap().remove(username);
            response.setStatus(200,"logout success");
        }
        //下面父类的方法主要是跳转
        //super.onLogoutSuccess(request, response, authentication);
    }
}
