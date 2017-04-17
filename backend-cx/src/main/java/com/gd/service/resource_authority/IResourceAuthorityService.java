package com.gd.service.resource_authority;

import com.gd.domain.base.BaseModel;
import com.gd.domain.resource.Resource;
import com.gd.domain.resource_authority.ResourceAuthority;

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
public interface IResourceAuthorityService {
    List<ResourceAuthority> queryForObject(ResourceAuthority resourceAuthority);
    //查询权限的资源信息
    List<Resource> queryResourceForAuthority(BaseModel baseModel);
    //为权限授予资源
    void insertResourceForAuthority(String id,List resourceIds);
    //移除权限的资源信息
    void removeResourceForAuthority(String id,List<String> resourceIds);
}
