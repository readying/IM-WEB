package com.gd.controller.userinfo;

import com.gd.annoation.Log;
import com.gd.annoation.RequestLimit;
import com.gd.domain.account.Account;
import com.gd.domain.base.BaseModel;
import com.gd.domain.department.Department;
import com.gd.domain.role.Role;
import com.gd.domain.userinfo.UserInfo;
import com.gd.entity.UserinfoAccountRole;
import com.gd.entity.UserinfoAccountRoleMessage;
import com.gd.service.account_role.IAccountRoleService;
import com.gd.service.userinfo.IUserInfoService;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.*;

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
    @Autowired
    private IAccountRoleService accountRoleService;
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
    @RequestMapping(value = "/batch/delete",method = RequestMethod.GET)
    // @RequestLimit(count = 1,limitTime = 2000)
    // @Log(name = "删除用户信息")
    @ApiOperation(value = "批量删除用户信息", httpMethod = "GET", notes = "批量删除用户信息")
    public String batchDelete(Principal principal,HttpServletRequest request,HttpServletResponse response,@RequestParam String ids){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","GET"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String,Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        List<String> listIds = new ArrayList<String>();
        for(String s:ids.split(",")){
            listIds.add(s);
        }
        String resultSuccess = "success";
        String resultCode = "0000";
        try {
            this.userInfoService.batchDeleteObject(listIds);
        }catch (Exception e){
            resultCode="0001";
            resultSuccess="faild";
        }finally {
            resultMap.put("code", resultCode);
            resultMap.put("data", resultSuccess);
        }
        return gson.toJson(resultMap);
    }
    @RequestMapping(value = "/{id}/accounts",method = RequestMethod.GET)
    // @RequestLimit(count = 1,limitTime = 2000)
    //@Log(name = "查看用户信息")
    @ApiOperation(value = "查看用户所有账户及角色信息", httpMethod = "GET", notes = "查看用户所有账户及角色信息")
    public String showAccountsById(Principal principal, HttpServletRequest request, HttpServletResponse response,@PathVariable String id){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","GET"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        List<Account> accountList  = this.userInfoService.queryAccountForObjectById(id);
        List<UserinfoAccountRoleMessage> userinfoAccountRoleMessageList = new ArrayList<UserinfoAccountRoleMessage>();
        for(Account account:accountList){
            BaseModel baseModel = new BaseModel();
            baseModel.setId(account.getId());
            List<Role> roleList = this.accountRoleService.queryRoleForAccount(baseModel);
            List<UserinfoAccountRole> userinfoAccountRoleList = new ArrayList<UserinfoAccountRole>();
            for(Role role:roleList){
                userinfoAccountRoleList.add(new UserinfoAccountRole(role.getId(),role.getRoleName()));
            }
            UserinfoAccountRoleMessage userinfoAccountRoleMessage = new UserinfoAccountRoleMessage(account.getId(),account.getUserName(),userinfoAccountRoleList);
            userinfoAccountRoleMessageList.add(userinfoAccountRoleMessage);
        }
        resultMap.put("code", "0000");
        resultMap.put("data", userinfoAccountRoleMessageList);

        return gson.toJson(resultMap);
    }

    @RequestMapping(value = "/{id}/exitDepartment",method = RequestMethod.GET)
    @ApiOperation(value = "用户退出部门", httpMethod = "GET", notes = "用户退出部门")
    public String exitDepartment(Principal principal,HttpServletRequest request,HttpServletResponse response,@PathVariable String id,@RequestParam String departmentId){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","GET"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String,Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        String resultSuccess = "success";
        String resultCode = "0000";
        try {
            this.userInfoService.exitDepartment(id,departmentId);
        }catch (Exception e){
            resultCode="0001";
            resultSuccess="faild";
        }finally {
            resultMap.put("code", resultCode);
            resultMap.put("data", resultSuccess);
        }
        return gson.toJson(resultMap);
    }

    @RequestMapping(value = "/{id}/friend",method = RequestMethod.GET)
    @ApiOperation(value = "查询用户的好友", httpMethod = "GET", notes = "查询用户的好友")
    public String findUserFriend(Principal principal,HttpServletRequest request,HttpServletResponse response,@PathVariable String id){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","GET"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String,Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        List<UserInfo> friendList = this.userInfoService.findUserFriend(id);
        resultMap.put("code","0000");
        resultMap.put("data",friendList);
        return gson.toJson(resultMap);
    }
    @RequestMapping(value = "/{id}/rootDepartment",method = RequestMethod.GET)
    @ApiOperation(value = "查询用户的顶级公司", httpMethod = "GET", notes = "查询用户的顶级公司")
    public String queryRootDepartmentForUser(Principal principal,HttpServletRequest request,HttpServletResponse response,@PathVariable String id){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","GET"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String,Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        Department department = this.userInfoService.queryRootDepartmentForUser(id);
        resultMap.put("code","0000");
        resultMap.put("data",department);
        return gson.toJson(resultMap);
    }
}
