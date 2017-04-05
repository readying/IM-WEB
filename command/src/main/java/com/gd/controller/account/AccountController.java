package com.gd.controller.account;

import ch.qos.logback.classic.Logger;
import com.gd.annoation.Log;
import com.gd.annoation.RequestLimit;
import com.gd.domain.account.Account;
import com.gd.domain.base.BaseModel;

import com.gd.service.account.IAccountService;
import com.google.gson.Gson;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
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
 * Created by dell on 2017/1/11.
 * Good Luck !
 * へ　　　　　／|
 * 　　/＼7　　　 ∠＿/
 * 　 /　│　　 ／　／
 * 　│　Z ＿,＜　／　　 /`ヽ
 * 　│　　　　　ヽ　　 /　　〉
 * 　 Y　　　　　`　 /　　/
 * 　ｲ●　､　●　　⊂⊃〈　　/
 * 　()　 へ　　　　|　＼〈
 * 　　>ｰ ､_　 ィ　 │ ／／
 * 　 / へ　　 /　ﾉ＜| ＼＼
 * 　 ヽ_ﾉ　　(_／　 │／／
 * 　　7　　　　　　　|／
 * 　　＞―r￣￣`ｰ―＿
 */
@Controller
@RestController
@RequestMapping("/account")
@SuppressWarnings("unused")
public class AccountController {
    @Autowired
    private IAccountService accountService;



    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @RequestLimit(count = 1,limitTime = 2000)
    @Log(name = "获取所有账户户信息")
    @ApiOperation(value = "获取所有账户信息", httpMethod = "GET", notes = "获取全部账户信息")
    public String getAllAccounts(Principal principal, HttpServletRequest request) {
        boolean flag = validateAccount(principal);
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        if (!flag) {
            resultMap.put("code", "0001");//验证失败
            resultMap.put("date", null);
            return gson.toJson(resultMap);
        }

        List<Account> accountList;

        accountList = this.accountService.queryForAllObject(new BaseModel());

        resultMap.put("code", "0000");
        resultMap.put("data", accountList);

        return gson.toJson(resultMap);
    }

    private boolean validateAccount(Principal principal) {
        String username = principal.getName();
        Account account = new Account();
        account.setUserName(username);
        List<Account> accountList = this.accountService.queryForObject(account);
        if (accountList.size() < 1) {
            return false;
        } else {
            return true;
        }
    }
    /*访问路径
   http://localhost:9090/v1.0/ma/account/accounts
   用户名：admin  密码：111111
    */
    @RequestMapping(value = "/accounts",method = RequestMethod.GET)
    // @RequestLimit(count = 1,limitTime = 2000)
    //@Log(name = "获取所有权限信息")
    @ApiOperation(value = "获取所有账户信息", httpMethod = "GET", notes = "获取全部账户信息")
    public String accounts(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","GET"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        List<Account> authorityList;

        authorityList = this.accountService.queryForAllObject(new BaseModel());

        resultMap.put("code", "0000");
        resultMap.put("data", authorityList);

        return gson.toJson(resultMap);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    // @RequestLimit(count = 1,limitTime = 2000)
    //@Log(name = "查看账户信息")
    @ApiOperation(value = "查看账户信息", httpMethod = "GET", notes = "查看账户信息")
    public String show(Principal principal, HttpServletRequest request, HttpServletResponse response,@PathVariable String id){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","GET"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        Account account = this.accountService.queryForObjectById(id);
        resultMap.put("code", "0000");
        resultMap.put("data",account);
        return gson.toJson(resultMap);
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    // @RequestLimit(count = 1,limitTime = 2000)
    // @Log(name = "新增账户信息")
    @ApiOperation(value = "新增账户信息", httpMethod = "POST", notes = "新增账户信息")
    public String add(Principal principal, HttpServletRequest request,HttpServletResponse response, @ModelAttribute Account account){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","GET"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        String resultSuccess="success";
        String resultCode="0000";
        try {
            this.accountService.insertForObject(account);
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
    // @Log(name = "更新账户信息")
    @ApiOperation(value = "更新账户信息", httpMethod = "POST", notes = "更新账户信息")
    public String update(Principal principal,HttpServletRequest request,HttpServletResponse response,@ModelAttribute Account account){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","POST,GET"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String,Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        String resultSuccess = "success";
        String resultCode = "0000";

        try {
            this.accountService.updateForObject(account);
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
    // @Log(name = "删除账户信息")
    @ApiOperation(value = "删除账户信息", httpMethod = "GET", notes = "删除账户信息")
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
            this.accountService.deleteForObject(baseModel);
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
