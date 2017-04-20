package com.gd.controller.resource_authority;

import com.gd.domain.base.BaseModel;
import com.gd.domain.resource.Resource;
import com.gd.service.resource_authority.IResourceAuthorityService;
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
 * Created by Administrator on 2017/3/15.
 */
@Controller
@RestController
@RequestMapping("/resourceAuthority")
public class ResourceAuthorityController {
    @Autowired
    private IResourceAuthorityService resourceAuthorityService;
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    // @RequestLimit(count = 1,limitTime = 2000)
    // @Log(name = "查看权限的资源信息")
    @ApiOperation(value = "查看权限的资源信息", httpMethod = "GET", notes = "查看权限的资源信息")
    //需要的参数，id：权限id
    public String show(HttpServletRequest request, HttpServletResponse response, @PathVariable String id){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","GET"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        BaseModel baseModel = new BaseModel();
        baseModel.setId(id);
        List<Resource> resourceList = this.resourceAuthorityService.queryResourceForAuthority(baseModel);
        resultMap.put("code", "0000");
        resultMap.put("data", resourceList);
        return gson.toJson(resultMap);
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    // @RequestLimit(count = 1,limitTime = 2000)
    // @Log(name = "新增权限资源信息")
    @ApiOperation(value = "新增权限资源信息", httpMethod = "POST", notes = "新增权限资源信息")
    //需要的参数，id：权限id，authorityIds：资源id字符串，以逗号分隔
    public String add(HttpServletRequest request, HttpServletResponse response, @RequestParam String id, @RequestParam String resourceIds){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","POST"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String, Object> resultMap = new HashMap<>();
        List<String> resourceIdsList = new ArrayList<>();
        for(String s:resourceIds.split(",")){
            resourceIdsList.add(s);
        }
        Gson gson = new Gson();
        String resultSuccess="success";
        String resultCode="0000";
        try {
            this.resourceAuthorityService.insertResourceForAuthority(id,resourceIdsList);
        }catch (Exception e){
            resultCode="0001";
            resultSuccess="faild";
        }finally {
            resultMap.put("code", resultCode);
            resultMap.put("data", resultSuccess);
        }
        return gson.toJson(resultMap);
    }
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    // @RequestLimit(count = 1,limitTime = 2000)
    // @Log(name = "移除权限资源信息")
    @ApiOperation(value = "移除权限资源信息", httpMethod = "POST", notes = "移除权限资源信息")
    //需要的参数，id：权限id，roleIds：资源id字符串，以逗号分隔
    public String removeUserAccount(HttpServletRequest request, HttpServletResponse response, @PathVariable String id, @RequestParam String resourceIds){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","POST"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String, Object> resultMap = new HashMap<>();
        List<String> resourceIdsList = new ArrayList<>();
        for(String s:resourceIds.split(",")){
            resourceIdsList.add(s);
        }
        Gson gson = new Gson();
        String resultSuccess="success";
        String resultCode="0000";
        try {
            this.resourceAuthorityService.removeResourceForAuthority(id,resourceIdsList);
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
