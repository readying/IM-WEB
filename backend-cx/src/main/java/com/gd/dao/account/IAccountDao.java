package com.gd.dao.account;

import com.gd.domain.account.Account;
import com.gd.domain.authority.Authority;
import com.gd.domain.base.BaseModel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
    //查询单个账户
    @Select("<script>SELECT  * FROM sys_account WHERE 1=1\n" +
            "<if test=\"id!=null and id!=''\">\n" +
            "AND ID=#{id}\n" +
            "</if>\n" +
            "<if test=\"userName!=null and userName!=''\">\n" +
            "AND USERNAME=#{userName}\n" +
            "</if>\n" +
            "<if test=\"passWord!=null and passWord!=''\">\n" +
            "AND PASSWORD=#{passWord}\n" +
            "</if>\n" +
            "<if test=\"ifUse!=null and ifUse!=''\">\n" +
            "AND IFUSE=#{ifUse}\n" +
            "</if></script>")
    List<Account> queryForObject(BaseModel baseModel);

    //根据账户id查询账户
    @Select("SELECT * FROM sys_account WHERE id=#{id}")
    Account queryForObjectById(String id);
    //查询所有账户
    @Select("SELECT * FROM sys_account")
    List<Account> queryForAllObject(BaseModel baseModel);
    //新增账户
    @Insert("INSERT INTO sys_account(id,userName,passWord,plat,passWordPrompt,salt,createTime,updateTime,ifUse,orderNum,token)" +
            " values(uuid(),#{userName},#{passWord},#{plat},#{passWordPrompt},#{salt},#{createTime},#{updateTime},#{ifUse},#{orderNum},#{token}) ")
    void insertForObject(BaseModel baseModel);
    //更新实体
    @Update("UPDATE sys_account set username=#{userName},password=#{passWord},plat=#{plat},passWordPrompt=#{passWordPrompt},salt=#{salt}," +
            "createTime=#{createTime},updateTime=#{updateTime},ifUse=#{ifUse},orderNum=#{orderNum},token=#{token} WHERE id=#{id}")
    void updateForObject(BaseModel baseModel);

    //删除实体
    @Update("UPDATE sys_account set ifUse='N',updateTime=#{updateTime} WHERE id =#{id}")
    void deleteForObject(BaseModel baseModel);
    //查询账户的权限
    @Select("<script>SELECT  * FROM v_account_authority WHERE 1=1\n" +
            "<if test=\"id!=null and id!=''\">\n" +
            "AND ACCOUNTID=#{id}\n" +
            "</if>\n" +
            "<if test=\"token!=null and token!=''\">\n" +
            "AND TOKEN=#{token}\n" +
            "</if>\n" +
            "<if test=\"userName!=null and userName!=''\">\n" +
            "AND USERNAME=#{userName}\n" +
            "</if></script>")
    List<Authority> queryForAuthorities(BaseModel baseModel);
    //查询最大排序编号,先将查询的字符串orderNum转换成int找最大值，再将字符串转换成int
    @Select("select CONCAT(MAX(cast(ordernum as UNSIGNED INTEGER))+1,'') from sys_account")
    String queryMaxOrderNum();
    //重置密码
    @Update("UPDATE sys_account set password=#{passWord},salt=#{salt} WHERE id =#{id}")
    void resetPassword(Account account);
}
