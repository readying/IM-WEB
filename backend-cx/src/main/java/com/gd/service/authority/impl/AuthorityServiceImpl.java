package com.gd.service.authority.impl;

import com.gd.dao.authority.IAuthorityDao;
import com.gd.domain.authority.Authority;
import com.gd.domain.base.BaseModel;
import com.gd.service.authority.IAuthorityService;
import com.gd.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@Service("authorityService")
public class AuthorityServiceImpl implements IAuthorityService {
    @Autowired
    private IAuthorityDao authorityDao;

    @Override
    public List<Authority> queryForObject(BaseModel baseModel) {
        return this.authorityDao.queryForObject(baseModel);
    }

    @Override
    public List<Authority> queryForAllObject(BaseModel baseModel) {
        return this.authorityDao.queryForAllObject(baseModel);
    }

    @Override
    public Authority queryForObjectById(String id) {
        return this.authorityDao.queryForObjectById(id);
    }

    @Override
    public void insertForObject(BaseModel baseModel) {
        baseModel.setIfUse("N");
        baseModel.setCreateTime(TimeUtils.getCurrentTime());
        baseModel.setUpdateTime(TimeUtils.getCurrentTime());
        baseModel.setOrderNum(authorityDao.queryMaxOrderNum());
        this.authorityDao.insertForObject(baseModel);
    }

    @Override
    public void updateForObject(BaseModel baseModel) {
        baseModel.setUpdateTime(TimeUtils.getCurrentTime());
        this.authorityDao.updateForObject(baseModel);
    }

    @Override
    public void deleteForObject(BaseModel baseModel) {
        baseModel.setUpdateTime(TimeUtils.getCurrentTime());
        this.authorityDao.deleteForObject(baseModel);
    }
}
