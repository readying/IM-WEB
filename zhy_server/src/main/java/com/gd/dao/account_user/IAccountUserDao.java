package com.gd.dao.account_user;

import com.gd.domain.account_user.AccountUser;
import org.apache.ibatis.annotations.Insert;
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
    //新增账户-用户
    @Insert("<script>insert into sys_account_user (id,accountId,userId) values (#{id},#{accountId},#{userId})</script>")
    int insertForObject(AccountUser accountUser);
}
