package com.gd.dao.resource_authority;

import com.gd.domain.resource_authority.ResourceAuthority;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

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
public interface IResourceAuthorityDao {
    @Select("<script>SELECT  * FROM sys_resource_authority WHERE 1=1\n" +
            "<if test=\"id!=null and id!=''\">\n" +
            "AND ID=#{id}\n" +
            "</if>\n" +
            "<if test=\"authorityId!=null and authorityId!=''\">\n" +
            "AND AUTHORITYID=#{authorityId}\n" +
            "</if>\n" +
            "<if test=\"resourceId!=null and resourceId!=''\">\n" +
            "AND RESOURCEID=#{resourceId}\n" +
            "</if></script>")
    List<ResourceAuthority> queryForObject(ResourceAuthority resourceAuthority);

    @Delete("<script>" +
            "DELETE FROM sys_resource_authority WHERE 1=1" +
            "<if test=\"id!=null and id!=''\">\n" +
            "AND ID=#{id}\n" +
            "</if>\n" +
            "<if test=\"authorityId!=null and authorityId!=''\">\n" +
            "AND AUTHORITYID=#{authorityId}\n" +
            "</if>\n" +
            "<if test=\"resourceId!=null and resourceId!=''\">\n" +
            "AND RESOURCEID=#{resourceId}\n" +
            "</if>\n" +
            "</script>")
    int deleteObject(ResourceAuthority resourceAuthority);
}
