package com.gd.service.account.impl;

import com.gd.dao.account.IAccountDao;
import com.gd.domain.account.Account;
import com.gd.domain.authority.Authority;
import com.gd.domain.base.BaseModel;

import com.gd.service.account.IAccountService;

import com.gd.util.MakeFixLenthStringUtils;
import com.gd.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Time;
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
@Service("accountService")
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountDao accountDao;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Account> queryForObject(BaseModel baseModel) {
        return this.accountDao.queryForObject(baseModel);
    }

    @Override
    public List<Account> queryForAllObject(BaseModel baseModel) {
        return this.accountDao.queryForAllObject(baseModel);
    }

    @Override
    @Cacheable(value = "authorities")
    public List<Authority> queryForAuthorities(BaseModel baseModel) {
        return this.accountDao.queryForAuthorities(baseModel);
    }
    //根据id查询账户
    @Override
    public Account queryForObjectById(String id) {
        return this.accountDao.queryForObjectById(id);
    }
    //新增账户
    @Override
    public void insertForObject(BaseModel baseModel) {
        baseModel.setIfUse("N");
        baseModel.setCreateTime(TimeUtils.getCurrentTime());
        baseModel.setUpdateTime(TimeUtils.getCurrentTime());
        baseModel.setOrderNum(accountDao.queryMaxOrderNum());
        if(baseModel instanceof Account){
            String randomSalt = MakeFixLenthStringUtils.getFixLenthString(6);
            ((Account) baseModel).setSalt(randomSalt);
            String currPassWord = ((Account) baseModel).getPassWord();
            String usePassWord =new Md5PasswordEncoder().encodePassword(currPassWord,randomSalt);
            ((Account) baseModel).setPassWord(usePassWord);
        }
        this.accountDao.insertForObject(baseModel);
    }
    //更新账户
    @Override
    public void updateForObject(BaseModel baseModel) {
        baseModel.setUpdateTime(TimeUtils.getCurrentTime());
        this.accountDao.updateForObject(baseModel);
    }
    //删除账户
    @Override
    public void deleteForObject(BaseModel baseModel) {
        baseModel.setUpdateTime(TimeUtils.getCurrentTime());
        this.accountDao.deleteForObject(baseModel);
    }
}
