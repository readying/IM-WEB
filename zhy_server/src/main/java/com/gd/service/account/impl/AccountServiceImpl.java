package com.gd.service.account.impl;

import com.gd.dao.account.IAccountDao;
import com.gd.domain.account.Account;
import com.gd.domain.authority.Authority;
import com.gd.domain.base.BaseModel;

import com.gd.domain.userinfo.UserInfo;
import com.gd.service.account.IAccountService;

import com.gd.util.MakeFixLenthStringUtils;
import com.gd.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
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
    //根据账户id查询用户信息
    @Override
    public List<UserInfo> queryForUserInfoByAccount(BaseModel baseModel) {
        return this.accountDao.queryForUserInfoByAccount(baseModel);
    }
    //根据账户username查询账户实体
    @Override
    public Account queryForObjectByUsername(String username) {
        return this.accountDao.queryForObjectByUsername(username);
    }
    //查询最大ordernum，并且到已经实现加1
    @Override
    public String queryMaxOrderNum() {
        return this.accountDao.queryMaxOrderNum();
    }

    //新增账户
    @Override
    public int insertForObject(BaseModel baseModel) {
        Account account = (Account) baseModel;
        account.setIfuse("Y");
        account.setCreateTime(TimeUtils.getCurrentTime());
        account.setUpdateTime(TimeUtils.getCurrentTime());
        account.setOrderNum(this.accountDao.queryMaxOrderNum());
        //获取长度为6的随机字符串
        String randomSalt = MakeFixLenthStringUtils.getFixLenthString(6);
        account.setSalt(randomSalt);
        String currPassWord = account.getPassword();
        //设置盐加密密码
        String usePassWord =new Md5PasswordEncoder().encodePassword(currPassWord,randomSalt);
        account.setPassword(usePassWord);
        return this.accountDao.insertForObject(account);
    }
}
