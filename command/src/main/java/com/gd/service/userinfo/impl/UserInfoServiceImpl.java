package com.gd.service.userinfo.impl;

import com.gd.dao.userinfo.IUserInfoDao;
import com.gd.domain.base.BaseModel;
import com.gd.domain.userinfo.UserInfo;
import com.gd.service.userinfo.IUserInfoService;
import com.gd.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/3/7.
 */
@Service("userinfoService")
public class UserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private IUserInfoDao userInfoDao;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public List<UserInfo> queryForAllObject(BaseModel baseModel) {
        return this.userInfoDao.queryForAllObject(baseModel);
    }

    @Override
    public void insertForObject(BaseModel baseModel) {
        baseModel.setIfUse("N");
        baseModel.setCreateTime(TimeUtils.getCurrentTime());
        baseModel.setUpdateTime(TimeUtils.getCurrentTime());
        baseModel.setOrderNum(userInfoDao.queryMaxOrderNum());
        userInfoDao.insertForObject(baseModel);
    }

    @Override
    public void updateForObject(BaseModel baseModel) {
        baseModel.setUpdateTime(TimeUtils.getCurrentTime());
        userInfoDao.updateForObject(baseModel);
    }

    @Override
    public void deleteForObject(BaseModel baseModel) {
        baseModel.setUpdateTime(TimeUtils.getCurrentTime());
        userInfoDao.deleteForObject(baseModel);
    }

    @Override
    public UserInfo queryForObjectById(String id) {
        return userInfoDao.queryForObjectById(id);
    }
}
