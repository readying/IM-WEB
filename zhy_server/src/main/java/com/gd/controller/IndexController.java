package com.gd.controller;

import com.gd.domain.account.Account;
import com.gd.domain.userinfo.UserInfo;
import com.gd.service.account.IAccountService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2017/1/13.
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
public class IndexController {
    @Autowired
    private IAccountService accountService;
    @GetMapping("/login")
    //@CrossOrigin(origins = "*", maxAge = 36000)
    public Object index(@AuthenticationPrincipal User loadUser) {
        if (loadUser != null) {
            String username = loadUser.getUsername();
            List<UserInfo> userInfoList = accountService.queryForUserInfoByAccount(accountService.queryForObjectByUsername(username));
            Map<String,Object> map = new HashedMap();
            map.put("account",loadUser);
            map.put("userinfo",userInfoList);
            return map;
        } else
            return "null";
    }
}
