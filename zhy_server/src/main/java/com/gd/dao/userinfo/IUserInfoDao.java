package com.gd.dao.userinfo;

import com.gd.domain.base.BaseModel;
import com.gd.domain.userinfo.UserInfo;
import org.apache.ibatis.annotations.Delete;
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
@Repository("userInfoDao")
public interface IUserInfoDao {
    @Select("<script>SELECT  * FROM sys_userinfo WHERE ifuse!='n'\n" +
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
            "<if test=\"orgId!=null and orgId!=''\">\n" +
            "AND ORGID=#{orgId}\n" +
            "</if>\n" +
            "<if test=\"station!=null and station!=''\">\n" +
            "AND STATION=#{station}\n" +
            "</if>\n" +
            "</script>")
    List<UserInfo> queryForObject(BaseModel baseModel);
    //根据id查询UserInfo
    @Select("<script>SELECT * FROM sys_userinfo where id=#{id}</script>")
    UserInfo queryObject(BaseModel baseModel);
    //查询最大ordernum
    @Select("<script>SELECT MAX(ordernum+0) FROM sys_userinfo</script>")
    String queryMaxOrderNum();
    @Insert("<script> INSERT INTO sys_userinfo" +
            " (id,ifUse,createTime,updateTime,orderNum,realName,phone,mail,org,station,gender,nation,nativePlace,birthDate,politicalStatus,maritalStatus,nickName,picture,policeNum,identityCode,qr,qq,weChat,jobCode,autoGraph,homeAddress,officeAddress,otherAddress,officeTelephone,otherTelephone,ifHideNum,communicationId,orgId) VALUES\n" +
            "  (#{id},#{ifuse},#{createTime},#{updateTime},#{orderNum},#{realName},#{phone},#{mail},#{org},#{station},#{gender},#{nation},#{nativePlace},#{birthDate},#{politicalStatus},#{maritalStatus},#{nickName},#{picture},#{policeNum},#{identityCode},#{qr},#{qq},#{weChat},#{jobCode},#{autoGraph},#{homeAddress},#{officeAddress},#{otherAddress},#{officeTelephone},#{otherTelephone},#{ifHideNum},#{communicationId},#{orgId})" +
            "</script>")
    int insertUser(BaseModel baseModel);
    //更新用户
    @Update("<script> UPDATE sys_userinfo" +
            " set ifuse=#{ifuse},createTime=#{createTime},updateTime=#{updateTime},orderNum=#{orderNum},realName=#{realName}," +
            "phone=#{phone},mail=#{mail},org=#{org},station=#{station},gender=#{gender},nation=#{nation},nativePlace=#{nativePlace}," +
            "birthDate=#{birthDate},politicalStatus=#{politicalStatus},maritalStatus=#{maritalStatus},nickName=#{nickName},picture=#{picture}," +
            "policeNum=#{policeNum},identityCode=#{identityCode},qr=#{qr},qq=#{qq},weChat=#{weChat},jobCode=#{jobCode},autoGraph=#{autoGraph}," +
            "homeAddress=#{homeAddress},officeAddress=#{officeAddress},otherAddress=#{otherAddress},officeTelephone=#{officeTelephone}," +
            "otherTelephone=#{otherTelephone},ifHideNum=#{ifHideNum},communicationId=#{communicationId},orgId=#{orgId}" +
            " WHERE id=#{id}</script>")
    int updateUser(BaseModel baseModel);
    @Delete("<script>DELETE FROM sys_userinfo WHERE ID=#{id}</script>")
    int deleteUser(BaseModel baseModel);
}
