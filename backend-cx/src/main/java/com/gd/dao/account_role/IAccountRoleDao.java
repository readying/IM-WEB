package com.gd.dao.account_role;

import com.gd.domain.account_role.AccountRole;
import com.gd.domain.base.BaseModel;
import com.gd.domain.role.Role;
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
@Repository("accountRoleDao")
public interface IAccountRoleDao {
    @Select("<script>SELECT  * FROM sys_account_role WHERE 1=1\n" +
            "<if test=\"id!=null and id!=''\">\n" +
            "AND ID=#{id}\n" +
            "</if>\n" +
            "<if test=\"accountId!=null and accountId!=''\">\n" +
            "AND ACCOUNTID=#{accountId}\n" +
            "</if>\n" +
            "<if test=\"roleId!=null and roleId!=''\">\n" +
            "AND ROLEID=#{roleId}\n" +
            "</if></script>")
    List<AccountRole> queryForObject(AccountRole accountRole);

    //查询账户的角色信息
    @Select("SELECT sr.`ID`,sr.`ROLENAME`,sr.`DESCRIPTION`,sr.`CREATETIME`,sr.`UPDATETIME`,sr.`IFUSE`,sr.`ORDERNUM` FROM sys_role sr LEFT JOIN " +
            "sys_account_role sar ON sr.`ID`=sar.`RoleID` LEFT JOIN" +
            " sys_account sa ON sa.`ID`=sar.`ACCOUNTID` WHERE sa.`ID`=#{id}")
    List<Role> queryRoleForAccount(BaseModel baseModel);
    //为账户授予角色
    @Insert({
            "<script>",
            "insert into sys_account_role (id,accountId,roleId)",
            "values ",
            "<foreach  collection='roleIds' item='roleId' separator=','>",
            "( uuid(),#{id},#{roleId,jdbcType=VARCHAR})",
            "</foreach>",
            "</script>"
    })
    void insertRoleForAccount(@Param("id") String id, @Param("roleIds") List<String> roleIds);

    //移除账户的角色信息
    @Delete({
            "<script>",
            "DELETE FROM sys_account_role where accountId = #{id} AND " +
                    "roleId IN " +
                    "<foreach collection='roleIds' open='(' close=')' item='roleId' separator=','>" +
                    "#{roleId}" +
                    "</foreach>",
            "</script>"
    })
    void removeRoleForAccount(@Param("id") String id,@Param("roleIds") List<String> roleIds);

    //查询多个账户的角色集合
    @Select({
            "<script>",
            "SELECT sr.* FROM sys_role sr " +
                    "LEFT JOIN sys_account_role sar ON sr.`ID`=sar.`ROLEID` " +
                    "WHERE sar.`ACCOUNTID` IN ",
            "<foreach  collection='accountIds' item='accountId' open='(' separator=',' close=')'>",
            "#{accountId,jdbcType=VARCHAR}",
            "</foreach>",
            "</script>"
    })
    List<Role> queryRolesForAccountList(@Param("accountIds") List<String> accountIds);
}
