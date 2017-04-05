package com.gd.dao.userinfo;

import com.gd.domain.base.BaseModel;
import com.gd.domain.userinfo.UserInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
}
