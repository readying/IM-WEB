package com.gd.dao.authority;


import com.gd.domain.authority.Authority;
import com.gd.domain.base.BaseModel;
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
@Repository("authorityDao")
public interface IAuthorityDao {
    //查询单个权限
    @Select("<script>SELECT  * FROM sys_authority WHERE 1=1\n" +
            "<if test=\"id!=null and id!=''\">\n" +
            "AND ID=#{id}\n" +
            "</if>\n" +
            "<if test=\"authorityName!=null and authorityName!=''\">\n" +
            "AND AUTHORITYNAME=#{authorityName}\n" +
            "</if>\n" +
            "<if test=\"ifUse!=null and ifUse!=''\">\n" +
            "AND IFUSE=#{ifUse}\n" +
            "</if></script>")
    List<Authority> queryForObject(BaseModel baseModel);
    //根据权限id查询权限
    @Select("SELECT * FROM sys_authority WHERE id=#{id}")
    Authority queryForObjectById(String id);
    //查询所有权限
    @Select("SELECT * FROM sys_authority")
    List<Authority> queryForAllObject(BaseModel baseModel);
    //新增账户
    @Insert("INSERT INTO sys_authority(id,authorityName,description,createTime,updateTime,ifUse,orderNum)" +
            " values(uuid(),#{authorityName},#{description},#{createTime},#{updateTime},#{ifUse},#{orderNum}) ")
    void insertForObject(BaseModel baseModel);
    //更新实体
    @Update("UPDATE sys_authority set authorityName=#{authorityName},description=#{description}" +
            ",createTime=#{createTime},updateTime=#{updateTime},ifUse=#{ifUse},orderNum=#{orderNum} where id=#{id}")
    void updateForObject(BaseModel baseModel);

    //删除实体
    @Update("UPDATE sys_authority set ifUse='N',updateTime=#{updateTime} WHERE id =#{id}")
    void deleteForObject(BaseModel baseModel);
    //查询最大排序编号,先将查询的字符串orderNum转换成int找最大值，再将字符串转换成int
    @Select("select CONCAT(MAX(cast(ordernum as UNSIGNED INTEGER))+1,'') from sys_authority")
    String queryMaxOrderNum();
}
