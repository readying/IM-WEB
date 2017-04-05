package com.gd.service.account_role;

import com.gd.domain.account_role.AccountRole;
import com.gd.domain.base.BaseModel;
import com.gd.domain.role.Role;

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
public interface IAccountRoleService {
    List<AccountRole> queryForObject(AccountRole accountRole);
    //查询账户的角色信息
    List<Role> queryRoleForAccount(BaseModel baseModel);
    //为账户授予角色
    void insertRoleForAccount(String id,List roleIds);
    //移除账户的角色信息
    void removeRoleForAccount(String id,List<String> roleIds);
}
