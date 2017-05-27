package com.gd.controller.role;

import com.gd.domain.authority.Authority;
import com.gd.domain.base.BaseModel;
import com.gd.domain.role.Role;
import com.gd.service.role.IRoleService;
import com.gd.util.TimeUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by dell on 2017/4/19.
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
@RequestMapping("/role")
@RestController
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    public String queryForList() {
        List<Map<String, Object>> roleList = new ArrayList<>();
        Map<String, Object> resultMap = new HashMap<>();
        roleList = this.roleService.queryForObject(new Role());
        resultMap.put("code", "0000");
        resultMap.put("data", roleList);

        Gson gson = new Gson();
        return gson.toJson(resultMap);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String add(@RequestBody Map<String, Object> paramMap) {
        Role role = new Role();
        role.setId(UUID.randomUUID().toString());
        role.setIfuse("y");
        role.setCreateTime(TimeUtils.getCurrentTime());
        role.setUpdateTime(TimeUtils.getCurrentTime());
        if (!StringUtils.isEmpty(paramMap.get("roleName"))) {
            role.setRoleName((String) paramMap.get("roleName"));
        }
        if (!StringUtils.isEmpty(paramMap.get("description"))) {
            role.setDescription((String) paramMap.get("description"));
        }
        if (!StringUtils.isEmpty(paramMap.get("orderNum"))) {
            role.setOrderNum((String) paramMap.get("orderNum"));
        }
//        if (!StringUtils.isEmpty(paramMap.get("authorities"))){
//            List<Authority> authorityList = new ArrayList<>();
//            authorityList =
//        }
        int flag = this.roleService.addObject(role);
        Map<String, Object> resultMap = new HashMap<>();
        if (flag == 1) {
            resultMap.put("code", "0000");
            resultMap.put("data", "success");
        } else {
            resultMap.put("code", "0002");
            resultMap.put("data", "role insert failed");
        }
        Gson gson = new Gson();
        return gson.toJson(resultMap);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") String id) {
        Role role = new Role();
        role.setId(id);
        int flag = this.roleService.deleteObject(role);
        Map<String, Object> resultMap = new HashMap<>();
        if (flag == 1) {
            resultMap.put("code", "0000");
            resultMap.put("data", "success");
        } else {
            resultMap.put("code", "0021");
            resultMap.put("data", "role delete failed");
        }
        Gson gson = new Gson();
        return gson.toJson(resultMap);
    }
}
