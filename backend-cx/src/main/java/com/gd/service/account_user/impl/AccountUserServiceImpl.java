package com.gd.service.account_user.impl;

import com.gd.dao.account_user.IAccountUserDao;
import com.gd.domain.account.Account;
import com.gd.domain.account_user.AccountUser;
import com.gd.domain.base.BaseModel;
import com.gd.service.account_user.IAccountUserService;
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
@Service("accountUserService")
public class AccountUserServiceImpl implements IAccountUserService {
    @Autowired
    private IAccountUserDao accountUserDao;

    @Override
    public List<AccountUser> queryForObject(AccountUser accountUser) {
        return this.accountUserDao.queryForObject(accountUser);
    }

    @Override
    public List<Account> queryAccountForUser(BaseModel baseModel) {
        return this.accountUserDao.queryAccountForUser(baseModel);
    }

    @Override
    public void insertAccountForUser(String id, List accountIds) {
        this.accountUserDao.insertAccountForUser(id,accountIds);
    }

    @Override
    public void removeAccountForUser(String id, List<String> accountIds) {
        this.accountUserDao.removeAccountForUser(id,accountIds);
    }
}
