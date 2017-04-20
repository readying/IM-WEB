package com.gd.service.role.impl;


import com.gd.dao.role.IRoleDao;
import com.gd.domain.base.BaseModel;
import com.gd.domain.role.Role;
import com.gd.service.role.IRoleService;
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
@Service("roleService")
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> queryForObject(BaseModel baseModel) {
        return this.roleDao.queryForObject(baseModel);
    }

    @Override
    public List<Role> queryForAllObject(BaseModel baseModel) {
        return this.roleDao.queryForAllObject(baseModel);
    }

    @Override
    public Role queryForObjectById(String id) {
        return this.roleDao.queryForObjectById(id);
    }

    @Override
    public void insertForObject(BaseModel baseModel) {
        baseModel.setIfUse("N");
        baseModel.setCreateTime(TimeUtils.getCurrentTime());
        baseModel.setUpdateTime(TimeUtils.getCurrentTime());
        baseModel.setOrderNum(roleDao.queryMaxOrderNum());
        this.roleDao.insertForObject(baseModel);
    }

    @Override
    public void updateForObject(BaseModel baseModel) {
        baseModel.setUpdateTime(TimeUtils.getCurrentTime());
        this.roleDao.updateForObject(baseModel);
    }

    @Override
    public void deleteForObject(BaseModel baseModel) {
        baseModel.setUpdateTime(TimeUtils.getCurrentTime());
        this.roleDao.deleteForObject(baseModel);
    }

}
