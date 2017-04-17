package com.gd.service.resource.impl;

import com.gd.dao.resource.IResourceDao;
import com.gd.domain.base.BaseModel;
import com.gd.domain.resource.Resource;
import com.gd.service.resource.IResourceService;
import com.gd.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
@Service("resourceService")
public class ResourceServiceImpl implements IResourceService {
    @Autowired
    private IResourceDao resourceDao;

    @Override
    @Cacheable(value = "resourceCache")
    public List<Resource> queryForObject(BaseModel baseModel) {
        return this.resourceDao.queryForObject(baseModel);
    }

    @Override
    public List<Resource> queryForAllObject(BaseModel baseModel) {
        return this.resourceDao.queryForAllObject(baseModel);
    }

    @Override
    public Resource queryForObjectById(String id) {
        return this.resourceDao.queryForObjectById(id);
    }

    @Override
    public void insertForObject(BaseModel baseModel) {
        baseModel.setIfUse("N");
        baseModel.setCreateTime(TimeUtils.getCurrentTime());
        baseModel.setUpdateTime(TimeUtils.getCurrentTime());
        baseModel.setOrderNum(resourceDao.queryMaxOrderNum());
        this.resourceDao.insertForObject(baseModel);
    }

    @Override
    public void updateForObject(BaseModel baseModel) {
        baseModel.setUpdateTime(TimeUtils.getCurrentTime());
        this.resourceDao.updateForObject(baseModel);
    }

    @Override
    public void deleteForObject(BaseModel baseModel) {
        baseModel.setUpdateTime(TimeUtils.getCurrentTime());
        this.resourceDao.deleteForObject(baseModel);
    }
}
