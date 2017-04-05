package com.gd.service.userinfo;

import com.gd.domain.base.BaseModel;
import com.gd.domain.userinfo.UserInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/3/7.
 */
public interface IUserInfoService {
    List<UserInfo> queryForAllObject(BaseModel baseModel);
    void insertForObject(BaseModel baseModel);
    void updateForObject(BaseModel baseModel);
    void deleteForObject(BaseModel baseModel);
    UserInfo queryForObjectById(String id);
}
