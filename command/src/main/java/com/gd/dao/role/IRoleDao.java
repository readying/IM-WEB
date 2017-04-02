package com.gd.dao.role;

import com.gd.domain.base.BaseModel;
import com.gd.domain.role.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

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
@Repository("roleDao")
public interface IRoleDao {
    @Select("<script>SELECT  * FROM sys_role WHERE 1=1\n" +
            "<if test=\"id!=null and id!=''\">\n" +
            "AND ID=#{id}\n" +
            "</if>\n" +
            "<if test=\"roleName!=null and roleName!=''\">\n" +
            "AND ROLENAME=#{roleName}\n" +
            "</if>\n" +
            "<if test=\"ifUse!=null and ifUse!=''\">\n" +
            "AND IFUSE=#{ifUse}\n" +
            "</if></script>")
    List<Role> queryForObject(BaseModel baseModel);
    //根据角色id查询角色
    @Select("SELECT * FROM sys_role WHERE id=#{id}")
    Role queryForObjectById(String id);
    //查询所有角色
    @Select("SELECT * FROM sys_role")
    List<Role> queryForAllObject(BaseModel baseModel);
    //新增角色
    @Insert("INSERT INTO sys_role(id,roleName,description,createTime,updateTime,ifUse,orderNum)" +
            " values(uuid(),#{roleName},#{description},#{createTime},#{updateTime},#{ifUse},#{orderNum}) ")
    void insertForObject(BaseModel baseModel);
    //更新实体
    @Update("UPDATE sys_role set roleName=#{roleName},description=#{description},createTime=#{createTime},updateTime=#{updateTime},ifUse=#{ifUse},orderNum=#{orderNum} where id=#{id}")
    void updateForObject(BaseModel baseModel);

    //删除实体
    @Update("UPDATE sys_role set ifUse='N',updateTime=#{updateTime} WHERE id =#{id}")
    void deleteForObject(BaseModel baseModel);
    //查询最大排序编号,先将查询的字符串orderNum转换成int找最大值，再将字符串转换成int
    @Select("select CONCAT(MAX(cast(ordernum as UNSIGNED INTEGER))+1,'') from sys_role")
    String queryMaxOrderNum();
}
