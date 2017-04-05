package com.gd.controller.userinfo;

import com.gd.annoation.Log;
import com.gd.annoation.RequestLimit;
import com.gd.domain.base.BaseModel;
import com.gd.domain.userinfo.UserInfo;
import com.gd.service.userinfo.IUserInfoService;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/7.
 * http://localhost:9090/ma/userinfo/all
 */
@Controller
@RestController
@RequestMapping("/userinfo")
public class UserInfoController {
    @Autowired
    private IUserInfoService userInfoService;
    @RequestMapping(value = "/userinfos",method = RequestMethod.GET)
   // @RequestLimit(count = 1,limitTime = 2000)
    //@Log(name = "获取所有用户信息")
    @ApiOperation(value = "获取所有用户信息", httpMethod = "GET", notes = "获取全部用户信息")
    public String userinfos(Principal principal, HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","GET"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
//        boolean flag = validateAccount(principal);
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
//        if (!flag) {
//            resultMap.put("code", "0001");//验证失败
//            resultMap.put("date", null);
//            return gson.toJson(resultMap);
//        }

        List<UserInfo> userInfoList;

        userInfoList = this.userInfoService.queryForAllObject(new BaseModel());

        resultMap.put("code", "0000");
        resultMap.put("data", userInfoList);

        return gson.toJson(resultMap);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    // @RequestLimit(count = 1,limitTime = 2000)
    //@Log(name = "查看用户信息")
    @ApiOperation(value = "查看用户信息", httpMethod = "GET", notes = "查看用户信息")
    public String show(Principal principal, HttpServletRequest request, HttpServletResponse response,@PathVariable String id){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","GET"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        UserInfo userInfo = this.userInfoService.queryForObjectById(id);
        resultMap.put("code", "0000");
        resultMap.put("data", userInfo);
        return gson.toJson(resultMap);
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
   // @RequestLimit(count = 1,limitTime = 2000)
   // @Log(name = "新增用户信息")
    @ApiOperation(value = "新增用户信息", httpMethod = "POST", notes = "新增用户信息")
    public String add(Principal principal, HttpServletRequest request,HttpServletResponse response,@ModelAttribute UserInfo userInfo){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        String resultSuccess="success";
        String resultCode="0000";
        try {
            this.userInfoService.insertForObject(userInfo);
        }catch (Exception e){
            resultCode="0001";
            resultSuccess="faild";
        }finally {
            resultMap.put("code", resultCode);
            resultMap.put("data", resultSuccess);
        }
        return gson.toJson(resultMap);
    }


    @RequestMapping(value = "/{id}/update",method = RequestMethod.POST)
    // @RequestLimit(count = 1,limitTime = 2000)
    // @Log(name = "更新用户信息")
    @ApiOperation(value = "更新用户信息", httpMethod = "POST", notes = "更新用户信息")
    public String update(Principal principal,HttpServletRequest request,HttpServletResponse response,@ModelAttribute UserInfo userInfo){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","POST,GET"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String,Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        String resultSuccess = "success";
        String resultCode = "0000";

        try {
            this.userInfoService.updateForObject(userInfo);
        }catch (Exception e){
            resultCode="0001";
            resultSuccess="faild";
        }finally {
            resultMap.put("code", resultCode);
            resultMap.put("data", resultSuccess);
        }
        return gson.toJson(resultMap);
    }


    @RequestMapping(value = "/{id}/delete",method = RequestMethod.GET)
    // @RequestLimit(count = 1,limitTime = 2000)
    // @Log(name = "删除用户信息")
    @ApiOperation(value = "删除用户信息", httpMethod = "GET", notes = "删除用户信息")
    public String delete(Principal principal,HttpServletRequest request,HttpServletResponse response,@PathVariable String id){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","GET"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String,Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        BaseModel baseModel = new BaseModel();
        baseModel.setId(id);
        String resultSuccess = "success";
        String resultCode = "0000";
        try {
            this.userInfoService.deleteForObject(baseModel);
        }catch (Exception e){
            resultCode="0001";
            resultSuccess="faild";
        }finally {
            resultMap.put("code", resultCode);
            resultMap.put("data", resultSuccess);
        }
        return gson.toJson(resultMap);
    }
}
