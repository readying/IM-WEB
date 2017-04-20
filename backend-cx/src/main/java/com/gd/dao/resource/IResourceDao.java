package com.gd.dao.resource;

import com.gd.domain.base.BaseModel;
import com.gd.domain.resource.Resource;
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
@Repository("resourceDao")
public interface IResourceDao {
    @Select("<script>SELECT  * FROM sys_resource WHERE 1=1\n" +
            "<if test=\"id!=null and id!=''\">\n" +
            "AND ID=#{id}\n" +
            "</if>\n" +
            "<if test=\"resourceName!=null and resourceName!=''\">\n" +
            "AND RESOURCENAME=#{resourceName}\n" +
            "</if>\n" +
            "<if test=\"type!=null and type!=''\">\n" +
            "AND TYPE=#{type}\n" +
            "</if>\n" +
            "<if test=\"parentId!=null and parentId!=''\">\n" +
            "AND PARENTID=#{parentId}\n" +
            "</if>\n" +
            "<if test=\"parentName!=null and parentName!=''\">\n" +
            "AND PARENTNAME=#{parentName}\n" +
            "</if>\n" +
            "<if test=\"appName!=null and appName!=''\">\n" +
            "AND APPNAME=#{appName}\n" +
            "</if>\n" +
            "<if test=\"url!=null and url!=''\">\n" +
            "AND URL=#{url}\n" +
            "</if>\n" +
            "<if test=\"level!=null and level!=''\">\n" +
            "AND LEVEL=#{level}\n" +
            "</if>\n" +
            "<if test=\"isMenuLeaf!=null and isMenuLeaf!=''\">\n" +
            "AND ISMENULEAF=#{isMenuLeaf}\n" +
            "</if>\n" +
            "<if test=\"ifUse!=null and ifUse!=''\">\n" +
            "AND IFUSE=#{ifUse}\n" +
            "</if></script>")
    List<Resource> queryForObject(BaseModel baseModel);
    //根据权限id查询资源
    @Select("SELECT * FROM sys_resource WHERE id=#{id}")
    Resource queryForObjectById(String id);
    //查询所有资源
    @Select("SELECT * FROM sys_resource")
    List<Resource> queryForAllObject(BaseModel baseModel);
    //新增资源
    @Insert("INSERT INTO sys_resource(id,resourceName,description,type,parentId,parentName,appName,url,level,isMenuLeaf,createTime,updateTime,ifUse,orderNum)" +
            " values(uuid(),#{resourceName},#{description},#{type},#{parentId},#{parentName},#{appName},#{url},#{level},#{isMenuLeaf},#{createTime},#{updateTime},#{ifUse},#{orderNum}) ")
    void insertForObject(BaseModel baseModel);
    //更新实体
    @Update("UPDATE sys_resource set resourceName=#{resourceName},description=#{description},type=#{type},parentId=#{parentId},parentName=#{parentName}," +
            "appName=#{appName},url=#{url},level=#{level},isMenuLeaf=#{isMenuLeaf},createTime=#{createTime},updateTime=#{updateTime},ifUse=#{ifUse}," +
            "orderNum=#{orderNum} where id=#{id}")
    void updateForObject(BaseModel baseModel);

    //删除实体
    @Update("UPDATE sys_resource set ifUse='N',updateTime=#{updateTime} WHERE id =#{id}")
    void deleteForObject(BaseModel baseModel);
    //查询最大排序编号,先将查询的字符串orderNum转换成int找最大值，再将字符串转换成int
    @Select("select CONCAT(MAX(cast(ordernum as UNSIGNED INTEGER))+1,'') from sys_resource")
    String queryMaxOrderNum();
}
