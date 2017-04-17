package com.gd.service.role_authority;

import com.gd.domain.authority.Authority;
import com.gd.domain.base.BaseModel;
import com.gd.domain.role_authority.RoleAuthority;

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
public interface IRoleAuthorityService {
    List<RoleAuthority> queryForObject(RoleAuthority roleAuthority);
    //查询角色的权限信息
    List<Authority> queryAuthorityForRole(BaseModel baseModel);
    //为角色授予权限
    void insertAuthorityForRole(String id,List authorityIds);
    //移除角色的权限信息
    void removeAuthorityForRole(String id,List<String> authorityIds);
}
