package com.gd.controller.role;

import com.gd.domain.base.BaseModel;
import com.gd.domain.role.Role;
import com.gd.service.role.IRoleService;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/13.
 */
@Controller
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @RequestMapping(value = "/roles",method = RequestMethod.GET)
    // @RequestLimit(count = 1,limitTime = 2000)
    //@Log(name = "获取所有角色信息")
    @ApiOperation(value = "获取所有角色信息", httpMethod = "GET", notes = "获取全部角色信息")
    public String resources(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","GET"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        List<Role> roleList;

        roleList = this.roleService.queryForAllObject(new BaseModel());

        resultMap.put("code", "0000");
        resultMap.put("data", roleList);

        return gson.toJson(resultMap);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    // @RequestLimit(count = 1,limitTime = 2000)
    //@Log(name = "查看角色信息")
    @ApiOperation(value = "查看角色信息", httpMethod = "GET", notes = "查看角色信息")
    public String show( HttpServletRequest request, HttpServletResponse response,@PathVariable String id){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","GET"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        Role role = this.roleService.queryForObjectById(id);
        resultMap.put("code", "0000");
        resultMap.put("data", role);
        return gson.toJson(resultMap);
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    // @RequestLimit(count = 1,limitTime = 2000)
    // @Log(name = "新增角色信息")
    @ApiOperation(value = "新增角色信息", httpMethod = "POST", notes = "新增角色信息")
    public String add( HttpServletRequest request,HttpServletResponse response, @ModelAttribute Role role){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","POST"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        String resultSuccess="success";
        String resultCode="0000";
        try {
            this.roleService.insertForObject(role);
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
    // @Log(name = "更新角色信息")
    @ApiOperation(value = "更新角色信息", httpMethod = "POST", notes = "更新角色信息")
    public String update(HttpServletRequest request,HttpServletResponse response,@ModelAttribute Role role){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","POST"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String,Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        String resultSuccess = "success";
        String resultCode = "0000";

        try {
            this.roleService.updateForObject(role);
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
    // @Log(name = "删除角色信息")
    @ApiOperation(value = "删除角色信息", httpMethod = "GET", notes = "删除角色信息")
    public String delete(HttpServletRequest request,HttpServletResponse response,@PathVariable String id){
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
            this.roleService.deleteForObject(baseModel);
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
