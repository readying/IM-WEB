package com.gd.service.userinfo;

import com.gd.domain.base.BaseModel;
import com.gd.domain.userinfo.UserInfo;

import java.util.List;

/**
 * Created by dell on 2017/4/6.
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
public interface IUserInfoService {
    List<UserInfo> queryForObject(BaseModel baseModel);
    int insertUser(BaseModel baseModel);
    int deleteUser(BaseModel baseModel);
    //更新用户信息
    int updateUser(BaseModel baseModel);
    //根据id查询用户
    UserInfo queryObject(BaseModel baseModel);
}
