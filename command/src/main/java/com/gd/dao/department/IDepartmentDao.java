package com.gd.dao.department;

import com.gd.domain.base.BaseModel;
import com.gd.domain.department.Department;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/3/13.
 */
@Repository("departmentDao")
public interface IDepartmentDao {
    //根据id查询部门
    @Select("SELECT * FROM sys_department WHERE id=#{id}")
    Department queryForObjectById(String id);
    //查询所有部门
    @Select("SELECT * FROM sys_department")
    List<Department> queryForAllObject(BaseModel baseModel);
    //新增部门
    @Insert("INSERT INTO sys_department(id,departmentName,leader,administrator,groups,createTime,updateTime,ifUse,orderNum)" +
            " values(uuid(),#{departmentName},#{leader},#{administrator},#{groups},#{createTime},#{updateTime},#{ifUse},#{orderNum})")
    void insertForObject(BaseModel baseModel);
    //更新实体
    @Update("UPDATE sys_department set departmentName=#{departmentName},leader=#{leader},administrator=#{administrator},groups=#{groups},createTime=#{createTime},updateTime=#{updateTime},ifUse=#{ifUse},orderNum=#{orderNum} where id=#{id}")
    void updateForObject(BaseModel baseModel);

    //删除实体
    @Update("UPDATE sys_department set ifUse='N' WHERE id =#{id}")
    void deleteForObject(BaseModel baseModel);
    //查询最大排序编号,先将查询的字符串orderNum转换成int找最大值，再将字符串转换成int
    @Select("select CONCAT(MAX(cast(ordernum as UNSIGNED INTEGER))+1,'') from sys_department")
    String queryMaxOrderNum();
}
