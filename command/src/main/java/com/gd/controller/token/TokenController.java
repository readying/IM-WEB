package com.gd.controller.token;

import com.gd.util.MySessionMapUtils;
import com.gd.util.MyUsernameTokenUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制token的接口
 */
@Controller
@RestController
@RequestMapping("/token")
public class TokenController {
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @ApiOperation(value = "移除用户的token", httpMethod = "GET", notes = "移除用户的token")
    //需要的参数，username：用户的token
    public String show(HttpServletRequest request, HttpServletResponse response, @PathVariable String username){
        try {
            String token = MyUsernameTokenUtils.getMyUsernameTokenMap().get(username);
            MyUsernameTokenUtils.getMyUsernameTokenMap().remove(username);
            MySessionMapUtils.getMySessionMap().remove(token);
        }catch (Exception e){
            return "faild";
        }
        return "success";
    }
}
