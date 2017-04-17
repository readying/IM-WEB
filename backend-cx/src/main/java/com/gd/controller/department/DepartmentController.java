package com.gd.controller.department;

import com.gd.domain.base.BaseModel;
import com.gd.domain.department.Department;
import com.gd.domain.role.Role;
import com.gd.domain.userinfo.UserInfo;
import com.gd.entity.DepartmentCountNum;
import com.gd.entity.DepartmentTree;
import com.gd.entity.DepartmentTreeJson;
import com.gd.service.department.IDepartmentService;
import com.gd.service.role.IRoleService;
import com.gd.util.ListJsonToTreeJson;
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
 * Created by Administrator on 2017/3/13.
 */
@Controller
@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;
    @RequestMapping(value = "/departments",method = RequestMethod.GET)
    // @RequestLimit(count = 1,limitTime = 2000)
    //@Log(name = "获取所有角色信息")
    @ApiOperation(value = "获取所有部门信息", httpMethod = "GET", notes = "获取全部部门信息")
    public String resources(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","GET"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        List<Department> departmentList;

        departmentList = this.departmentService.queryForAllObject(new BaseModel());

        resultMap.put("code", "0000");
        resultMap.put("data", departmentList);

        return gson.toJson(resultMap);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    // @RequestLimit(count = 1,limitTime = 2000)
    //@Log(name = "查看部门信息")
    @ApiOperation(value = "查看部门信息", httpMethod = "GET", notes = "查看部门信息")
    public String show( HttpServletRequest request, HttpServletResponse response,@PathVariable String id){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","GET"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        Department department = this.departmentService.queryForObjectById(id);
        resultMap.put("code", "0000");
        resultMap.put("data", department);
        return gson.toJson(resultMap);
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    // @RequestLimit(count = 1,limitTime = 2000)
    // @Log(name = "新增部门信息")
    @ApiOperation(value = "新增部门信息", httpMethod = "POST", notes = "新增部门信息")
    public String add( HttpServletRequest request,HttpServletResponse response, @ModelAttribute Department department){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","POST"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        String resultSuccess="success";
        String resultCode="0000";
        try {
            this.departmentService.insertForObject(department);
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
    // @Log(name = "更新部门信息")
    @ApiOperation(value = "更新部门信息", httpMethod = "POST", notes = "更新部门信息")
    public String update(HttpServletRequest request,HttpServletResponse response,@ModelAttribute Department department){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","POST"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String,Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        String resultSuccess = "success";
        String resultCode = "0000";

        try {
            this.departmentService.updateForObject(department);
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
    // @Log(name = "删除部门信息")
    @ApiOperation(value = "删除部门信息", httpMethod = "GET", notes = "删除部门信息")
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
            this.departmentService.deleteForObject(baseModel);
        }catch (Exception e){
            resultCode="0001";
            resultSuccess="faild";
        }finally {
            resultMap.put("code", resultCode);
            resultMap.put("data", resultSuccess);
        }
        return gson.toJson(resultMap);
    }
    @RequestMapping(value = "/{id}/tree",method = RequestMethod.GET)
    @ApiOperation(value = "查询当前部门的树形结构信息",httpMethod = "GET",notes = "查询当前部门的树形结构信息")
    public String queryDepartmentTree(HttpServletRequest request,HttpServletResponse response,@PathVariable String id){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","GET"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        Department rootDepartment = this.departmentService.queryForObjectById(id);
        DepartmentTree departmentTree = new DepartmentTree();
        departmentTree.setId(id);
        departmentTree.setNodeName(rootDepartment.getDepartmentName());
        departmentTree.setParentId("0");
        List<DepartmentTree> departmentTreeJson = this.departmentService.queryDepartmentTree(id);
        departmentTreeJson.add(departmentTree);
        //转换成树形json
        List<DepartmentTreeJson> departmentTreeJsonList = ListJsonToTreeJson.buildListToTree(departmentTreeJson);
        resultMap.put("code", "0000");
        resultMap.put("data", departmentTreeJsonList);
        return gson.toJson(resultMap);
    }
    @RequestMapping(value = "/{id}/childrenTree",method = RequestMethod.GET)
    @ApiOperation(value = "查询当前部门的下级部门的树形结构信息",httpMethod = "GET",notes = "查询当前部门的下级部门的树形结构信息")
    public String queryDepartmentChildrenTree(HttpServletRequest request,HttpServletResponse response,@PathVariable String id){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","GET"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        List<DepartmentCountNum> childrenDepartmentTree = this.departmentService.queryDepartmentChildrenTree(id);
        List<UserInfo> childrenDepartmentUserTree = this.departmentService.queryDepartmentUserChildrenTree(id);
        //转换成树形json
        resultMap.put("code", "0000");
        if(childrenDepartmentTree.size()==0 || childrenDepartmentTree==null){
            resultMap.put("isFather","false");
            resultMap.put("data", childrenDepartmentUserTree);
        }else{
            resultMap.put("isFather","true");
            resultMap.put("data", childrenDepartmentTree);
        }
        return gson.toJson(resultMap);
    }
    @RequestMapping(value = "/{id}/addUser",method = RequestMethod.POST)
    @ApiOperation(value = "为部门添加成员",httpMethod = "POST",notes = "为部门添加成员")
    //id:部门id；userIds:人员id字符串，以逗号分隔
    public String addUserForDepartment(HttpServletRequest request,HttpServletResponse response,@PathVariable String id,@RequestParam String userIds){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","POST"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        List<String> userIdsList = new ArrayList<String>();
        for(String s:userIds.split(",")){
            userIdsList.add(s);
        }
        String resultSuccess = "success";
        String resultCode = "0000";
        try {
            this.departmentService.addUserForDepartment(id,userIdsList);
        }catch (Exception e){
            resultCode="0001";
            resultSuccess="faild";
        }finally {
            resultMap.put("code", resultCode);
            resultMap.put("data", resultSuccess);
        }
        return gson.toJson(resultMap);
    }
    @RequestMapping(value = "/{id}/addSuperiorDepartment",method = RequestMethod.GET)
    @ApiOperation(value = "为部门分配上级部门",httpMethod = "GET",notes = "为部门分配上级部门")
    public String addSuperiorDepartment(HttpServletRequest request,HttpServletResponse response,@PathVariable String id,@RequestParam String parentId){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","GET"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        String resultSuccess = "success";
        String resultCode = "0000";
        try {
            this.departmentService.addSuperiorDepartment(id,parentId);
        }catch (Exception e){
            resultCode="0001";
            resultSuccess="faild";
        }finally {
            resultMap.put("code", resultCode);
            resultMap.put("data", resultSuccess);
        }
        return gson.toJson(resultMap);
    }
    @RequestMapping(value = "/{id}/addChidrenDepartment",method = RequestMethod.POST)
    @ApiOperation(value = "为部门添加下级部门",httpMethod = "POST",notes = "为部门添加下级部门")
    //id:部门id；userIds:下级部门id字符串，以逗号分隔
    public String addChidrenDepartment(HttpServletRequest request,HttpServletResponse response,@PathVariable String id,@RequestParam String chidrenIds){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","POST"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        List<String> childrenDepartmentIdsList = new ArrayList<String>();
        for(String s:chidrenIds.split(",")){
            childrenDepartmentIdsList.add(s);
        }
        String resultSuccess = "success";
        String resultCode = "0000";
        try {
            this.departmentService.addChidrenDepartment(id,childrenDepartmentIdsList);
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
