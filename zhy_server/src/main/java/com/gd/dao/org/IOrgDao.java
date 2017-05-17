package com.gd.dao.org;

import com.gd.domain.base.BaseModel;
import com.gd.domain.org.Org;
import com.gd.domain.userinfo.UserInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dell on 2017/4/30.
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
@Repository("orgDao")
public interface IOrgDao {
    @Select("<script>SELECT  * FROM sys_org WHERE 1=1\n" +
            "<if test=\"id!=null and id!=''\">\n" +
            "AND ID=#{id}\n" +
            "</if>\n" +
            "<if test=\"orgName!=null and orgName!=''\">\n" +
            "AND ORGNAME=#{orgName}\n" +
            "</if>\n" +
            "<if test=\"leader!=null and leader!=''\">\n" +
            "AND LEADER=#{leader}\n" +
            "</if>\n" +
            "<if test=\"parentId!=null and parentId!=''\">\n" +
            "AND PARENTID=#{parentId}\n" +
            "</if>\n" +
            "<if test=\"parentName!=null and parentName!=''\">\n" +
            "AND PARENTNAME=#{parentName}\n" +
            "</if>\n" +
            "<if test=\"path!=null and path!=''\">\n" +
            "AND PATH=#{path}\n" +
            "</if>\n" +

            "<if test=\"ifuse!=null and ifuse!=''\">\n" +
            "AND IFUSE=#{ifuse}\n" +
            "</if></script>")
    List<Org> queryForObject(BaseModel baseModel);
    //查询org下面是否存在org
    @Select("<script>SELECT * FROM sys_org WHERE parentId = #{id}</script>")
    List<Org> queryChildernByOrg(BaseModel baseModel);
    //查询org下面是否存在人
    @Select("<script>SELECT * FROM sys_userinfo WHERE orgId = #{id}</script>")
    List<UserInfo> queryChildernByUserInfo(BaseModel baseModel);
    @Insert("<script> INSERT INTO sys_org" +
            " (ID, ORGNAME, LEADER, PATH, PARENTNAME, PARENTID, CREATETIME, UPDATETIME, IFUSE, ORDERNUM) VALUES\n" +
            "  (#{id},#{orgName},#{leader},#{path},#{parentName},#{parentId},#{createTime},#{updateTime}," +
            "#{ifuse},#{orderNum})" +
            "</script>")
    int insertOrg(BaseModel baseModel);
    @Delete("<script>DELETE from sys_org where id=#{id}</script>")
    int deleteOrg(BaseModel baseModel);
}
