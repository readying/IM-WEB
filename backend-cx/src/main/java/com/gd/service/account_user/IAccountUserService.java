package com.gd.service.account_user;

import com.gd.domain.account.Account;
import com.gd.domain.account_user.AccountUser;
import com.gd.domain.base.BaseModel;

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
public interface IAccountUserService {
    List<AccountUser> queryForObject(AccountUser accountUser);
    //查询用户的账户信息
    List<Account> queryAccountForUser(BaseModel baseModel);
    //为用户授予账户
    void insertAccountForUser(String id,List accountIds);
    //移除用户的账户信息
    void removeAccountForUser(String id,List<String> accountIds);
}
