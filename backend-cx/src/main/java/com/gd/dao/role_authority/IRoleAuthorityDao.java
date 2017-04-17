package com.gd.dao.role_authority;

import com.gd.domain.authority.Authority;
import com.gd.domain.base.BaseModel;
import com.gd.domain.role_authority.RoleAuthority;
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
@Repository("roleAuthorityDao")
public interface IRoleAuthorityDao {
    @Select("<script>SELECT  * FROM sys_role_authority WHERE 1=1\n" +
            "<if test=\"id!=null and id!=''\">\n" +
            "AND ID=#{id}\n" +
            "</if>\n" +
            "<if test=\"authorityId!=null and authorityId!=''\">\n" +
            "AND AUTHORITYID=#{authorityId}\n" +
            "</if>\n" +
            "<if test=\"roleId!=null and roleId!=''\">\n" +
            "AND ROLEID=#{roleId}\n" +
            "</if></script>")
    List<RoleAuthority> queryForObject(RoleAuthority roleAuthority);

    //查询角色的权限信息
    @Select("SELECT sa.`ID`,sa.`DESCRIPTION`,sa.`AUTHORITYNAME`,sa.`CREATETIME`,sa.`UPDATETIME`,sa.`UPDATETIME`,sa.`IFUSE` FROM sys_authority sa LEFT JOIN \n" +
            "            sys_role_authority sra ON sa.`ID`=sra.`AUTHORITYID` LEFT JOIN sys_role sr ON sr.`ID`=sra.`ROLEID` WHERE sr.`ID`=#{id}")
    List<Authority> queryAuthorityForRole(BaseModel baseModel);
    //为角色授予权限
    @Insert({
            "<script>",
            "insert into sys_role_authority (id,roleId,authorityId)",
            "values ",
            "<foreach  collection='authorityIds' item='authorityId' separator=','>",
            "( uuid(),#{id},#{authorityId,jdbcType=VARCHAR})",
            "</foreach>",
            "</script>"
    })
    void insertAuthorityForRole(@Param("id") String id, @Param("authorityIds") List<String> authorityIds);

    //移除角色的权限信息
    @Delete({
            "<script>",
            "DELETE FROM sys_role_authority where roleId = #{id} AND " +
                    "authorityId IN " +
                    "<foreach collection='authorityIds' open='(' close=')' item='authorityId' separator=','>" +
                    "#{authorityId}" +
                    "</foreach>",
            "</script>"
    })
    void removeAuthorityForRole(@Param("id") String id,@Param("authorityIds") List<String> authorityIds);
}
