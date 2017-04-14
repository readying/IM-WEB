package com.gd.service.department;

import com.gd.domain.base.BaseModel;
import com.gd.domain.department.Department;
import com.gd.domain.userinfo.UserInfo;
import com.gd.entity.DepartmentCountNum;
import com.gd.entity.DepartmentTree;

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
public interface IDepartmentService {
    //查询所有部门
    List<Department> queryForAllObject(BaseModel baseModel);
    //根据id查询部门
    Department queryForObjectById(String id);
    //新增部门
    void insertForObject(BaseModel baseModel);
    //更新部门
    void updateForObject(BaseModel baseModel);
    //删除部门
    void deleteForObject(BaseModel baseModel);
    //根据部门id查询部门tree
    List<DepartmentTree> queryDepartmentTree(String id);
    //查询当前部门的一级下级部门
    List<DepartmentCountNum> queryDepartmentChildrenTree(String id);
    //查询当前部门的人员
    List<UserInfo> queryDepartmentUserChildrenTree(String id);
    //为部门添加成员
    void addUserForDepartment(String id,List<String> userIds);
    //移除部门成员
    void deleteUserForDepartment(String id,String userId);
    //为部门分配上级部门
    void addSuperiorDepartment(String id,String parentId);
    //为部门分配下级部门
    void addChidrenDepartment(String id,List<String> childrenDepartmentIds);
}
