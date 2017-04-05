package com.gd.service.authority;

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
public interface IAuthorityService {
    List<Authority> queryForObject(BaseModel baseModel);
    //查询所有权限
    List<Authority> queryForAllObject(BaseModel baseModel);
    //根据id查询权限
    Authority queryForObjectById(String id);
    //新增权限
    void insertForObject(BaseModel baseModel);
    //更新权限
    void updateForObject(BaseModel baseModel);
    //删除权限
    void deleteForObject(BaseModel baseModel);
}
