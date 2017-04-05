package com.gd.dao.resource_authority;

import com.gd.domain.base.BaseModel;
import com.gd.domain.resource.Resource;
import com.gd.domain.resource_authority.ResourceAuthority;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
@Repository("resourceAuthorityDao")
public interface IResourceAuthorityDao {
    @Select("<script>SELECT  * FROM sys_resource_authority WHERE 1=1\n" +
            "<if test=\"id!=null and id!=''\">\n" +
            "AND ID=#{id}\n" +
            "</if>\n" +
            "<if test=\"authorityId!=null and authorityId!=''\">\n" +
            "AND AUTHORITYID=#{authorityId}\n" +
            "</if>\n" +
            "<if test=\"resourceId!=null and resourceId!=''\">\n" +
            "AND RESOURCEID=#{resourceId}\n" +
            "</if></script>")
    List<ResourceAuthority> queryForObject(ResourceAuthority resourceAuthority);

    //查询权限的资源信息
    @Select("SELECT sr.`ID`,sr.`APPNAME`,sr.`CREATETIME`,sr.`DESCRIPTION`,sr.`IFUSE`,sr.`ISMENULEAF`,sr.`LEVEL`,sr.`ORDERNUM`,sr.`PARENTID`,sr.`PARENTNAME`,sr.`RESOURCENAME`,sr.`TYPE`,sr.`UPDATETIME`,sr.`URL` FROM sys_resource sr LEFT JOIN \n" +
            " sys_resource_authority sra ON sr.`ID`=sra.`RESOURCEID` LEFT JOIN sys_authority sa ON sa.`ID`=sra.`AUTHORITYID` WHERE sa.`ID`=#{id}")
    List<Resource> queryAuthorityForRole(BaseModel baseModel);
    //为权限赋予资源
    @Insert({
            "<script>",
            "insert into sys_resource_authority (id,authorityId,resourceId)",
            "values ",
            "<foreach  collection='resourceIds' item='resourceId' separator=','>",
            "( uuid(),#{id},#{resourceId,jdbcType=VARCHAR})",
            "</foreach>",
            "</script>"
    })
    void insertAuthorityForRole(@Param("id") String id, @Param("resourceIds") List<String> resourceIds);

    //移除权限的资源信息
    @Delete({
            "<script>",
            "DELETE FROM sys_resource_authority where authorityId = #{id} AND " +
                    "resourceId IN " +
                    "<foreach collection='resourceIds' open='(' close=')' item='resourceId' separator=','>" +
                    "#{resourceId}" +
                    "</foreach>",
            "</script>"
    })
    void removeResourceForAuthority(@Param("id") String id,@Param("resourceIds") List<String> resourceIds);
}
