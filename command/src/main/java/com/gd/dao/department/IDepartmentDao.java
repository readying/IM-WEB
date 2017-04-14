package com.gd.dao.department;

import com.gd.domain.base.BaseModel;
import com.gd.domain.department.Department;
import com.gd.domain.userinfo.UserInfo;
import com.gd.entity.DepartmentCountNum;
import com.gd.entity.DepartmentTree;
import org.apache.ibatis.annotations.*;
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
    /*
    根据部门id查询该部门的所有下级，包括部门和人员，查询结构为树形结构
    创建存储过程如下：
    delimiter //
    CREATE FUNCTION `getDepartmentChildList`(rootId varchar(1000))
       RETURNS varchar(1000)
       BEGIN
        DECLARE sTemp VARCHAR(1000);
        DECLARE sTempChd VARCHAR(1000);

        SET sTemp = '$';
        SET sTempChd =rootId;

        WHILE sTempChd is not null DO
         SET sTemp = concat(sTemp,',',sTempChd);
         SELECT group_concat(childrenid) INTO sTempChd FROM (SELECT '0' AS id,departmentid AS parentid,userid AS childrenid FROM sys_user_department UNION SELECT * FROM sys_department_department) as
    sys_department_department where FIND_IN_SET(parentid,sTempChd)>0;
     END WHILE;
    RETURN sTemp;
   END
   //
delimiter ;
     */
    @Select("SELECT tmp.parentid AS parentId,sd1.departmentname AS parentName,tmp.childrenid AS id,IFNULL(sd2.departmentname,su.realname) AS nodeName FROM (SELECT * FROM(SELECT '0' AS id,departmentid AS parentid,userid AS childrenid FROM sys_user_department UNION SELECT * FROM sys_department_department) AS sys_department_department \n" +
            " WHERE FIND_IN_SET(childrenid, getDepartmentChildList(#{id}))) AS tmp LEFT JOIN sys_department sd1 ON tmp.parentid=sd1.id LEFT JOIN sys_department sd2 ON tmp.childrenid=sd2.id LEFT JOIN sys_userinfo su ON tmp.childrenid=su.id")
    List<DepartmentTree> queryDepartmentTree(String id);
    //查询department下的一级子节点,并且包含有多少个子节点
//    @Select("SELECT sd.* FROM sys_department sd LEFT JOIN " +
//            "sys_department_department sdd ON sd.`ID`=sdd.`CHILDRENID` " +
//            "WHERE sdd.`PARENTID`=#{id}")
    @Select("SELECT tmp.*," +
            "IF((SELECT COUNT(*) FROM sys_department_department sdd WHERE sdd.parentid =tmp.id)='0',(SELECT COUNT(*) FROM sys_user_department sud WHERE sud.departmentid =tmp.id),(SELECT COUNT(*) FROM sys_department_department sdd WHERE sdd.parentid =tmp.id))" +
            " AS num FROM \n" +
            "(SELECT sd.* FROM sys_department sd " +
            "LEFT JOIN sys_department_department sdd ON sd.`ID`=sdd.`CHILDRENID` WHERE sdd.`PARENTID`=#{id}) AS tmp")
    List<DepartmentCountNum> queryDepartmentChildrenTree(String id);
    //查询department下的人员，并且包含有多少个子节点
    @Select("SELECT su.* FROM sys_userinfo su LEFT JOIN " +
            "sys_user_department sud ON su.`ID`=sud.`USERID` " +
            "WHERE sud.`DEPARTMENTID`=#{id}")
    List<UserInfo> queryDepartmentUserChildrenTree(String id);
    //为部门添加人员成员
    @Insert({
            "<script>",
            "insert into sys_user_department (id,userId,departmentId)",
            "values ",
            "<foreach  collection='userIds' item='userId' separator=','>",
            "( uuid(),#{userId,jdbcType=VARCHAR},#{id})",
            "</foreach>",
            "</script>"
    })
    void addUserForDepartment(@Param("id") String id, @Param("userIds") List<String> userIds);
    //删除部门下成员
    @Delete({
            "<script>",
            "delete from sys_user_department where departmentId = #{id} and userId = #{userId}",
            "</script>"
    })
    void deleteUserForDepartment(@Param("id") String id, @Param("userId") String userId);
    //为部门分配上级部门
    @Insert({
            "<script>" ,
            "insert into sys_department_department (id,parentId,childrenId) values (uuid(),#{parentId},#{id})",
            "</script>"
    })
    void addSuperiorDepartment(@Param("id") String id,@Param("parentId") String parentId);
    //为部门添加下级部门
    @Insert({
            "<script>",
            "insert into sys_department_department (id,parentId,childrenId)",
            "values ",
            "<foreach  collection='childrenDepartmentIds' item='childrenDepartmentId' separator=','>",
            "( uuid(),#{id},#{childrenDepartmentId,jdbcType=VARCHAR})",
            "</foreach>",
            "</script>"
    })
    void addChidrenDepartment(@Param("id") String id, @Param("childrenDepartmentIds") List<String> childrenDepartmentIds);
}
