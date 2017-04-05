package com.gd.service.department;

import com.gd.domain.base.BaseModel;
import com.gd.domain.department.Department;

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
}
