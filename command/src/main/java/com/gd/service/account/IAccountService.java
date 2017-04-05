package com.gd.service.account;

import com.gd.domain.account.Account;
import com.gd.domain.authority.Authority;
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
public interface IAccountService {
    List<Account> queryForObject(BaseModel baseModel);
    List<Account> queryForAllObject(BaseModel baseModel);
    List<Authority> queryForAuthorities(BaseModel baseModel);
    //根据id查询账户
    Account queryForObjectById(String id);
    //新增账户
    void insertForObject(BaseModel baseModel);
    //更新账户
    void updateForObject(BaseModel baseModel);
    //删除账户
    void deleteForObject(BaseModel baseModel);
}
