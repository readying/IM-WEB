package com.gd.service.userinfo;

import com.gd.domain.account.Account;
import com.gd.domain.base.BaseModel;
import com.gd.domain.department.Department;
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
    //批量删除用户
    void batchDeleteObject(List<String> listIds);
    UserInfo queryForObjectById(String id);
    List<Account> queryAccountForObjectById(String id);
    //用户退出部门
    void exitDepartment(String id,String departmentId);
    //查询用户的好友
    List<UserInfo> findUserFriend(String id);
    //查询用户的顶级部门
    Department queryRootDepartmentForUser(String id);
}
