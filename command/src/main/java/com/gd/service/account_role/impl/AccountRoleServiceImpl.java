package com.gd.service.account_role.impl;

import com.gd.dao.account_role.IAccountRoleDao;
import com.gd.domain.account_role.AccountRole;
import com.gd.domain.base.BaseModel;
import com.gd.domain.role.Role;
import com.gd.service.account_role.IAccountRoleService;
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
@Service("userRoleService")
public class AccountRoleServiceImpl implements IAccountRoleService {
    @Autowired
    private IAccountRoleDao accountRoleDao;

    @Override
    public List<AccountRole> queryForObject(AccountRole accountRole) {
        return this.accountRoleDao.queryForObject(accountRole);
    }

    @Override
    public List<Role> queryRoleForAccount(BaseModel baseModel) {
        return this.accountRoleDao.queryRoleForAccount(baseModel);
    }

    @Override
    public void insertRoleForAccount(String id, List roleIds) {
        this.accountRoleDao.insertRoleForAccount(id,roleIds);
    }

    @Override
    public void removeRoleForAccount(String id, List<String> roleIds) {
        this.accountRoleDao.removeRoleForAccount(id,roleIds);
    }
}
