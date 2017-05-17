package com.gd.controller.account;

import ch.qos.logback.classic.Logger;
import com.gd.annoation.Log;
import com.gd.annoation.RequestLimit;
import com.gd.domain.account.Account;
import com.gd.domain.base.BaseModel;

import com.gd.domain.userinfo.UserInfo;
import com.gd.service.account.IAccountService;
import com.google.gson.Gson;

import io.swagger.annotations.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@Api(value = "AccountController",description = "账户相关api")
@SuppressWarnings("unused")
public class AccountController {
    @Autowired
    private IAccountService accountService;
    @GetMapping("/all")
    @RequestLimit(count = 1,limitTime = 2000)
    @CrossOrigin(origins = "*", maxAge = 360000)
    @Log(name = "获取所有用户信息")
    @ApiOperation(value = "获取所有用户信息", httpMethod = "GET", notes = "获取全部账户信息")
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

    @RequestMapping(value = "/userInfo/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据账户获取用户信息",notes = "根据账户获取用户信息",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "No Privilege"),
            @ApiResponse(code = 405, message = "Invalid input")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "账户id", required = true, dataType = "String", paramType = "path")
    })
    public String getUserInfoByAccounts(@PathVariable String id, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();

        List<UserInfo> userInfoList;
        BaseModel baseModel = new BaseModel();
        baseModel.setId(id);
        userInfoList = this.accountService.queryForUserInfoByAccount(baseModel);
        resultMap.put("code", "0000");
        resultMap.put("data", userInfoList);
        return gson.toJson(resultMap);
    }

//    @RequestMapping(value = "/account/{username}", method = RequestMethod.GET)
//    @ApiOperation(value = "根据账户username获取账户信息",notes = "根据账户username获取账户信息",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ApiResponses(value = {
//            @ApiResponse(code = 401, message = "No Privilege"),
//            @ApiResponse(code = 405, message = "Invalid input")
//    })
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "username", value = "账户username", required = true, dataType = "String", paramType = "path")
//    })
//    public String getObjectByUsername(@PathVariable String username, HttpServletRequest request) {
//        Map<String, Object> resultMap = new HashMap<>();
//        Gson gson = new Gson();
//
//        Account account = this.accountService.queryForObjectByUsername(username);
//        resultMap.put("code", "0000");
//        resultMap.put("data", account);
//        return gson.toJson(resultMap);
//    }


    private boolean validateAccount(Principal principal) {
        String username = principal.getName();
        Account account = new Account();
        account.setUsername(username);
        List<Account> accountList = this.accountService.queryForObject(account);
        if (accountList.size() < 1) {
            return false;
        } else {
            return true;
        }
    }
}
