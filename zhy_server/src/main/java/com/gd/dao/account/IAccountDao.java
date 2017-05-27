package com.gd.dao.account;

import com.gd.domain.account.Account;
import com.gd.domain.authority.Authority;
import com.gd.domain.base.BaseModel;
import com.gd.domain.userinfo.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dell on 2017/1/11.
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
@Repository("accountDao")
public interface IAccountDao {

    @Select("<script>SELECT  * FROM sys_account WHERE 1=1\n" +
            "<if test=\"id!=null and id!=''\">\n" +
            "AND ID=#{id}\n" +
            "</if>\n" +
            "<if test=\"username!=null and username!=''\">\n" +
            "AND USERNAME=#{username}\n" +
            "</if>\n" +
            "<if test=\"password!=null and password!=''\">\n" +
            "AND PASSWORD=#{password}\n" +
            "</if>\n" +
            "<if test=\"ifuse!=null and ifuse!=''\">\n" +
            "AND IFUSE=#{ifuse}\n" +
            "</if></script>")
    List<Account> queryForObject(BaseModel baseModel);

    @Select("SELECT * FROM sys_account")
    List<Account> queryForAllObject(BaseModel baseModel);

    @Select("<script>SELECT  * FROM v_account_authority WHERE 1=1\n" +
            "<if test=\"id!=null and id!=''\">\n" +
            "AND ACCOUNTID=#{id}\n" +
            "</if>\n" +
            "<if test=\"token!=null and token!=''\">\n" +
            "AND TOKEN=#{token}\n" +
            "</if>\n" +
            "<if test=\"username!=null and username!=''\">\n" +
            "AND USERNAME=#{username}\n" +
            "</if></script>")
    List<Authority> queryForAuthorities(BaseModel baseModel);
    //根据账户id查询用户信息
    @Select("<script>SELECT su.* FROM sys_userinfo su " +
                "LEFT JOIN sys_account_user sau ON su.`ID`=sau.`USERID` " +
                    "LEFT JOIN sys_account sa ON sau.`ACCOUNTID`=sa.`ID` " +
                        "WHERE sa.`ID`=#{id}</script>")
    List<UserInfo> queryForUserInfoByAccount(BaseModel baseModel);
    //根据账户username查询账户实体
    @Select("<script>SELECT * FROM sys_account sa WHERE sa.`USERNAME`=#{username}</script>")
    Account queryForObjectByUsername(@Param("username") String username);
    //查询最大排序编号,先将查询的字符串orderNum转换成int找最大值，再将字符串转换成int
    @Select("select CONCAT(MAX(cast(ordernum as UNSIGNED INTEGER))+1,'') from sys_account")
    String queryMaxOrderNum();
    //插入账户
    @Insert("<script>INSERT INTO sys_account (id,username,password,salt,createtime,updatetime,ifuse,ordernum,token,appId)" +
            " values (#{id},#{username},#{password},#{salt},#{createTime},#{updateTime},#{ifuse},#{orderNum},#{token},#{appId})</script>")
    int insertForObject(BaseModel baseModel);
}
