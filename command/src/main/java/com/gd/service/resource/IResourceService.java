package com.gd.service.resource;

import com.gd.domain.base.BaseModel;
import com.gd.domain.resource.Resource;

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
public interface IResourceService {
    List<Resource> queryForObject(BaseModel baseModel);
    //查询所有资源
    List<Resource> queryForAllObject(BaseModel baseModel);
    //根据id查询资源
    Resource queryForObjectById(String id);
    //新增资源
    void insertForObject(BaseModel baseModel);
    //更新资源
    void updateForObject(BaseModel baseModel);
    //删除资源
    void deleteForObject(BaseModel baseModel);
}
