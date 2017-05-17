package com.gd.service.account;

import com.gd.domain.account.Account;
import com.gd.domain.authority.Authority;
import com.gd.domain.base.BaseModel;
import com.gd.domain.userinfo.UserInfo;

import java.util.List;

/**
 * Created by dell on 2017/1/12.
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
public interface IAccountService {
    List<Account> queryForObject(BaseModel baseModel);
    List<Account> queryForAllObject(BaseModel baseModel);
    List<Authority> queryForAuthorities(BaseModel baseModel);
    List<UserInfo> queryForUserInfoByAccount(BaseModel baseModel);
    //根据username查询账户实体
    Account queryForObjectByUsername(String username);
    //查询最大排序编号
    String queryMaxOrderNum();
    //新增account
    int insertForObject(BaseModel baseModel);
}
