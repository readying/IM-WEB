package com.gd.service.userinfo.impl;

import com.gd.dao.userinfo.IUserInfoDao;
import com.gd.domain.base.BaseModel;
import com.gd.domain.userinfo.UserInfo;
import com.gd.service.userinfo.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("userInfoService")
public class UserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private IUserInfoDao userInfoDao;

    @Override
    public List<UserInfo> queryForObject(BaseModel baseModel) {
        return this.userInfoDao.queryForObject(baseModel);
    }

    @Override
    public int insertUser(BaseModel baseModel) {
        UserInfo userInfo = (UserInfo) baseModel;
        String maxOrderNum = (Integer.parseInt(userInfoDao.queryMaxOrderNum())+1)+"";
        userInfo.setOrderNum(maxOrderNum);
        return this.userInfoDao.insertUser(baseModel);
    }

    @Override
    public int deleteUser(BaseModel baseModel) {
        return this.userInfoDao.deleteUser(baseModel);
    }

    @Override
    public int updateUser(BaseModel baseModel) {
        return this.userInfoDao.updateUser(baseModel);
    }
    //根据id查询UserInfo
    @Override
    public UserInfo queryObject(BaseModel baseModel) {
        return this.userInfoDao.queryObject(baseModel);
    }
}
