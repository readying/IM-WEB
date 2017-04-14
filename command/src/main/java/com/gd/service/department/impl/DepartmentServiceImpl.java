package com.gd.service.department.impl;


import com.gd.dao.department.IDepartmentDao;
import com.gd.domain.base.BaseModel;
import com.gd.domain.department.Department;
import com.gd.domain.userinfo.UserInfo;
import com.gd.entity.DepartmentCountNum;
import com.gd.entity.DepartmentTree;
import com.gd.service.department.IDepartmentService;
import com.gd.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
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
@Service("departmentService")
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private IDepartmentDao departmentDao;


    @Override
    public List<Department> queryForAllObject(BaseModel baseModel) {
        return this.departmentDao.queryForAllObject(baseModel);
    }

    @Override
    public Department queryForObjectById(String id) {
        return this.departmentDao.queryForObjectById(id);
    }

    @Override
    public void insertForObject(BaseModel baseModel) {
        baseModel.setIfUse("N");
        baseModel.setCreateTime(TimeUtils.getCurrentTime());
        baseModel.setUpdateTime(TimeUtils.getCurrentTime());
        baseModel.setOrderNum(departmentDao.queryMaxOrderNum());
        this.departmentDao.insertForObject(baseModel);
    }

    @Override
    public void updateForObject(BaseModel baseModel) {
        baseModel.setUpdateTime(TimeUtils.getCurrentTime());
        this.departmentDao.updateForObject(baseModel);
    }

    @Override
    public void deleteForObject(BaseModel baseModel) {
        baseModel.setUpdateTime(TimeUtils.getCurrentTime());
        this.departmentDao.deleteForObject(baseModel);
    }

    @Override
    public List<DepartmentTree> queryDepartmentTree(String id) {
        return this.departmentDao.queryDepartmentTree(id);
    }

    @Override
    public List<DepartmentCountNum> queryDepartmentChildrenTree(String id) {
        return this.departmentDao.queryDepartmentChildrenTree(id);
    }

    @Override
    public List<UserInfo> queryDepartmentUserChildrenTree(String id) {
        return this.departmentDao.queryDepartmentUserChildrenTree(id);
    }

    @Override
    public void addUserForDepartment(String id, List<String> userIds) {
        this.departmentDao.addUserForDepartment(id,userIds);
    }

    @Override
    public void deleteUserForDepartment(String id, String userId) {
        this.departmentDao.deleteUserForDepartment(id,userId);
    }

    @Override
    public void addSuperiorDepartment(String id, String parentId) {
        this.departmentDao.addSuperiorDepartment(id,parentId);
    }

    @Override
    public void addChidrenDepartment(String id, List<String> childrenDepartmentIds) {
        this.departmentDao.addChidrenDepartment(id,childrenDepartmentIds);
    }
}
