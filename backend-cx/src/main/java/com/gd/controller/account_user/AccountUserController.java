package com.gd.controller.account_user;

import com.gd.dao.account_user.IAccountUserDao;
import com.gd.domain.account.Account;
import com.gd.domain.base.BaseModel;
import com.gd.service.account_user.IAccountUserService;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/14.
 * 处理用户--账户请求的controller
 */
@Controller
@RestController
@RequestMapping("/accountUser")
public class AccountUserController {
    @Autowired
    private IAccountUserService accountUserService;
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    // @RequestLimit(count = 1,limitTime = 2000)
    // @Log(name = "查看用户账户信息")
    @ApiOperation(value = "查看用户账户信息", httpMethod = "GET", notes = "查看用户账户信息")
    //需要的参数，id：用户id，accountIds：账户id字符串，以逗号分隔
    public String show(HttpServletRequest request, HttpServletResponse response, @PathVariable String id){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","GET"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        BaseModel baseModel = new BaseModel();
        baseModel.setId(id);
        List<Account> accountList = this.accountUserService.queryAccountForUser(baseModel);
        resultMap.put("code", "0000");
        resultMap.put("data", accountList);
        return gson.toJson(resultMap);
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    // @RequestLimit(count = 1,limitTime = 2000)
    // @Log(name = "新增用户账户信息")
    @ApiOperation(value = "新增用户账户信息", httpMethod = "POST", notes = "新增用户账户信息")
    //需要的参数，id：用户id，accountIds：账户id字符串，以逗号分隔
    public String add(HttpServletRequest request, HttpServletResponse response, @RequestParam String id, @RequestParam String accountIds){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","POST"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String, Object> resultMap = new HashMap<>();
        List<String> accountIdsList = new ArrayList<>();
        for(String s:accountIds.split(",")){
            accountIdsList.add(s);
        }
        Gson gson = new Gson();
        String resultSuccess="success";
        String resultCode="0000";
        try {
            this.accountUserService.insertAccountForUser(id,accountIdsList);
        }catch (Exception e){
            resultCode="0001";
            resultSuccess="faild";
        }finally {
            resultMap.put("code", resultCode);
            resultMap.put("data", resultSuccess);
        }
        return gson.toJson(resultMap);
    }
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    // @RequestLimit(count = 1,limitTime = 2000)
    // @Log(name = "移除用户账户信息")
    @ApiOperation(value = "移除用户账户信息", httpMethod = "POST", notes = "移除用户账户信息")
    //需要的参数，id：用户id，accountIds：账户id字符串，以逗号分隔
    public String removeUserAccount(HttpServletRequest request, HttpServletResponse response, @RequestParam String id, @RequestParam String accountIds){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","POST"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String, Object> resultMap = new HashMap<>();
        List<String> accountIdsList = new ArrayList<>();
        for(String s:accountIds.split(",")){
            accountIdsList.add(s);
        }
        Gson gson = new Gson();
        String resultSuccess="success";
        String resultCode="0000";
        try {
            this.accountUserService.removeAccountForUser(id,accountIdsList);
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
