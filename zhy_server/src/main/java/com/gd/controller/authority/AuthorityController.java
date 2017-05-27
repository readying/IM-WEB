package com.gd.controller.authority;

import com.gd.domain.authority.Authority;
import com.gd.domain.role.Role;
import com.gd.service.authority.IAuthorityService;
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
@RequestMapping("/authority")
@RestController
public class AuthorityController {
    @Autowired
    private IAuthorityService authorityService;

    @RequestMapping(method = RequestMethod.GET)
    public String queryForList() {
        List<Authority> authorityList = new ArrayList<>();
        Map<String, Object> resultMap = new HashMap<>();
        authorityList = this.authorityService.queryForObject(new Authority());
        resultMap.put("code", "0000");
        resultMap.put("data", authorityList);

        Gson gson = new Gson();
        return gson.toJson(resultMap);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String add(@RequestBody Authority authority) {
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        if (StringUtils.isEmpty(authority)) {
            resultMap.put("code", "0002");
            resultMap.put("data", "authority insert failed");
            return gson.toJson(resultMap);
        }
        authority.setId(UUID.randomUUID().toString());
        authority.setCreateTime(TimeUtils.getCurrentTime());
        authority.setUpdateTime(TimeUtils.getCurrentTime());
        authority.setIfuse("y");
        int flag = this.authorityService.addObject(authority);

        if (flag == 1) {
            resultMap.put("code", "0000");
            resultMap.put("data", "success");
        } else {
            resultMap.put("code", "0042");
            resultMap.put("data", "authority insert failed");
        }

        return gson.toJson(resultMap);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") String id) {
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        if (StringUtils.isEmpty(id)) {
            resultMap.put("code", "0043");
            resultMap.put("data", "authority delete failed");
        } else {
            Authority authority = new Authority();
            authority.setId(id);
            this.authorityService.deleteObject(authority);
            resultMap.put("code", "0000");
            resultMap.put("data", "success");
        }
        return gson.toJson(resultMap);
    }

}
