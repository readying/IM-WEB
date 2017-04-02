package com.gd.service.role;

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
public interface IRoleService {
    List<Role> queryForObject(BaseModel baseModel);
    //查询所有角色
    List<Role> queryForAllObject(BaseModel baseModel);
    //根据id查询角色
    Role queryForObjectById(String id);
    //新增角色
    void insertForObject(BaseModel baseModel);
    //更新角色
    void updateForObject(BaseModel baseModel);
    //删除角色
    void deleteForObject(BaseModel baseModel);
}
