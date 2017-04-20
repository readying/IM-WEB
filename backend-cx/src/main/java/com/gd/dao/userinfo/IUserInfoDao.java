package com.gd.dao.userinfo;

import com.gd.domain.account.Account;
import com.gd.domain.base.BaseModel;
import com.gd.domain.department.Department;
import com.gd.domain.userinfo.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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
@Repository("userInfoDao")
public interface IUserInfoDao {
    //查询单个实体
    @Select("<script>SELECT  * FROM sys_userinfo WHERE 1=1\n" +
            "<if test=\"id!=null and id!=''\">\n" +
            "AND ID=#{id}\n" +
            "</if>\n" +
            "<if test=\"realName!=null and realName!=''\">\n" +
            "AND REALNAME=#{realName}\n" +
            "</if>\n" +
            "<if test=\"phone!=null and phone!=''\">\n" +
            "AND PHONE=#{phone}\n" +
            "</if>\n" +
            "<if test=\"mail!=null and mail!=''\">\n" +
            "AND MAIL=#{mail}\n" +
            "</if>\n" +
            "<if test=\"org!=null and org!=''\">\n" +
            "AND ORG=#{org}\n" +
            "</if>\n" +
            "<if test=\"station!=null and station!=''\">\n" +
            "AND STATION=#{station}\n" +
            "</if>\n" +
            "<if test=\"ifUse!=null and ifUse!=''\">\n" +
            "AND IFUSE=#{ifUse}\n" +
            "</if></script>")
     List<UserInfo> queryForObject(BaseModel baseModel);
    //根据id查询单个实体
    @Select("SELECT * FROM sys_userinfo where id = #{id}")
    UserInfo queryForObjectById(String id);
    //查询所有实体
    @Select("SELECT * FROM sys_userinfo")
    List<UserInfo> queryForAllObject(BaseModel baseModel);
    //新增实体
    @Insert("INSERT INTO sys_userinfo(id,ifUse,createTime,updateTime,orderNum,realName,phone,mail,org,station,gender,nation,nativePlace,birthDate,politicalStatus,maritalStatus,nickName,picture,policeNum,identityCode,qr,qq,weChat,jobCode,autoGraph,homeAddress,officeAddress,otherAddress,officeTelephone,otherTelephone,ifHideNum)" +
            " values(uuid(),#{ifUse},#{createTime},#{updateTime},#{orderNum},#{realName},#{phone},#{mail},#{org},#{station},#{gender},#{nation},#{nativePlace},#{birthDate},#{politicalStatus},#{maritalStatus},#{nickName},#{picture},#{policeNum},#{identityCode},#{qr},#{qq},#{weChat},#{jobCode},#{autoGraph},#{homeAddress},#{officeAddress},#{otherAddress},#{officeTelephone},#{otherTelephone},#{ifHideNum}) ")
    void insertForObject(BaseModel baseModel);
    //更新实体
    @Update("UPDATE sys_userinfo set ifUse=#{ifUse},createTime=#{createTime},updateTime=#{updateTime},orderNum=#{orderNum}," +
            "realName=#{realName},phone=#{phone},mail=#{mail},org=#{org},station=#{station},gender=#{gender},nation=#{nation}," +
            "nativePlace=#{nativePlace},birthDate=#{birthDate},politicalStatus=#{politicalStatus},maritalStatus=#{maritalStatus}," +
            "nickName=#{nickName},picture=#{picture},policeNum=#{policeNum},identityCode=#{identityCode},qr=#{qr},qq=#{qq},weChat=#{weChat}," +
            "jobCode=#{jobCode},autoGraph=#{autoGraph},homeAddress=#{homeAddress},officeAddress=#{officeAddress},otherAddress=#{otherAddress}," +
            "officeTelephone=#{officeTelephone},otherTelephone=#{otherTelephone},ifHideNum=#{ifHideNum} where id=#{id}")
    void updateForObject(BaseModel baseModel);
    //删除实体
    @Update("Update sys_userinfo set ifuse='N',updateTime=#{updateTime} where id = #{id}")
    void deleteForObject(BaseModel baseModel);
    //查询最大排序编号,先将查询的字符串orderNum转换成int找最大值，再将字符串转换成int
    @Select("select CONCAT(MAX(cast(ordernum as UNSIGNED INTEGER))+1,'') from sys_userinfo")
    String queryMaxOrderNum();
    //查询用户的所有账户
    @Select("select sa.* from sys_account sa left join sys_account_user sau on sa.`ID`=sau.`ACCOUNTID" +
            "` left join sys_userinfo su on sau.`USERID`=su.`ID` " +
            "where su.`ID`=#{id}")
    List<Account> queryAccountForObjectById(String id);
    //用户退出部门
    @Delete("delete from sys_user_department sud where sud.userId=#{id} and sud.departmentId=#{departmentId}")
    void exitDepartment(@Param("id") String id,@Param("departmentId") String departmentId);
    //查询用户的好友
    @Select("SELECT su.* FROM sys_userinfo su " +
            "LEFT JOIN sys_user_user suu ON su.`ID`=suu.`USERIDRIGHT`" +
            " WHERE suu.`USERIDLEFT`=#{id}")
    List<UserInfo> findUserFriend(String id);
    //查询用户的顶级部门
    /*函数如下
    delimiter //
    CREATE FUNCTION `rc`(departmentId varchar(50))
       RETURNS varchar(50)
       BEGIN
        DECLARE i varchar(50);
	set i = departmentId;

        WHILE (select count(*) from sys_department_department sdd where sdd.childrenId = i) <> 0 DO
         set i = (select sdd.parentId from sys_department_department sdd where sdd.childrenId = i);
     END WHILE;
    RETURN i;
   END
   //
delimiter ;
     */
    @Select("select * from sys_department sd where sd.`ID`=" +
            "(select rc((select sdd.`PARENTID` from sys_department_department sdd where " +
            "sdd.`CHILDRENID`=(select sud.departmentId from sys_user_department sud " +
            "where sud.userId=#{id}))))")
    Department queryRootDepartmentForUser(String id);
}
