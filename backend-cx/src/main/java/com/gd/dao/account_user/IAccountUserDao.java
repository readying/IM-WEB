package com.gd.dao.account_user;

import com.gd.domain.account.Account;
import com.gd.domain.account_user.AccountUser;
import com.gd.domain.base.BaseModel;
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
@Repository("accountUserDao")
public interface IAccountUserDao {
    @Select("<script>SELECT  * FROM sys_account_user WHERE 1=1\n" +
            "<if test=\"id!=null and id!=''\">\n" +
            "AND ID=#{id}\n" +
            "</if>\n" +
            "<if test=\"userId!=null and userId!=''\">\n" +
            "AND USERID=#{userId}\n" +
            "</if>\n" +
            "<if test=\"accountId!=null and accountId!=''\">\n" +
            "AND ACCOUNTID=#{accountId}\n" +
            "</if></script>")
    List<AccountUser> queryForObject(AccountUser accountUser);

    //查询用户的账户信息
    @Select("SELECT sa.`ID`,sa.`CREATETIME`,sa.`IFUSE`,sa.`ORDERNUM`,sa.`PASSWORD`,sa.`PASSWORDPROMPT`,sa.`PLAT`,sa.`SALT`,sa.`TOKEN`,sa.`UPDATETIME`,sa.`USERNAME`\n" +
            " FROM sys_account sa LEFT JOIN sys_account_user sac ON sa.`ID`=sac.`ACCOUNTID` LEFT JOIN sys_userinfo su ON su.`ID`=sac.`USERID` WHERE su.`ID`=#{id}")
    List<Account> queryAccountForUser(BaseModel baseModel);
    //为用户授予账户
    @Insert({
            "<script>",
            "insert into sys_account_user (id,userId,accountId)",
            "values ",
            "<foreach  collection='accountIds' item='accountId' separator=','>",
            "( uuid(),#{id},#{accountId,jdbcType=VARCHAR})",
            "</foreach>",
            "</script>"
    })
    void insertAccountForUser(@Param("id") String id,@Param("accountIds") List<String> accountIds);

    //移除用户的账户信息
    @Delete({
            "<script>",
            "DELETE FROM sys_account_user where userId = #{id} AND " +
                    "accountId IN " +
                    "<foreach collection='accountIds' open='(' close=')' item='accountId' separator=','>" +
                    "#{accountId}" +
                    "</foreach>",
            "</script>"
    })
    void removeAccountForUser(@Param("id") String id,@Param("accountIds") List<String> accountIds);
}
