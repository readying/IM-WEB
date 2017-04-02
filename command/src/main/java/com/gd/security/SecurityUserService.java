package com.gd.security;

import com.gd.domain.account.Account;
import com.gd.domain.authority.Authority;
import com.gd.domain.saltuser.SaltUser;
import com.gd.service.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 这个代码是用来验证用户名和密码是否正确的
 * Created by dell on 2017/1/11.
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
/*
@Component：用于向spring注入一个bean
首先来实现UserDetailsService，从数据库中获取用户信息，
这个接口需要我们实现一个方法：loadUserByUsername。即通过用户名加载与该用户的用户名、密码以及权限相关的信息。
这个类注入到configure(AuthenticationManagerBuilder auth)方法中了，用于验证用户登录
 */
@Component
public class SecurityUserService implements UserDetailsService {
    //注入service类，也就是用来查询数据库中用户名和密码是否存在
    @Autowired
    private IAccountService accountService;
    @Autowired
    private EhCacheCacheManager ehCacheCacheManager;
    //实现loadUserByUsername方法，验证用户名是否正确，返回的是UserDetail类，我们返回的是SaltUser类(继承了UserDetails类)
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = new Account();
        account.setUserName(s);
        //SecurityUser user = new SecurityUser();
        // SecurityUser securityUser = securityUserDao.queryForUserByName(username);
        //查询是否存在这个account
        List<Account> accountList = this.accountService.queryForObject(account);
        //定义一个UserDetails类
        UserDetails userDetail = null;
        //用我们查询出来的account来封装我们的SaltUser类，并且返回我们的SaltUser类
        if (accountList.size() != 0) {
            //调用我们写的SaltUser实体类的构造方法
            userDetail = new SaltUser(s,
                    accountList.get(0).getPassWord(), true, true, true, true,
                    //找到这个账户的所有权限
                    findUserAuthorities2(accountList.get(0).getUserName()),
                    accountList.get(0).getSalt());
        }

        //返回我们自定义的实体SaltUser
        return userDetail;
    }
    //这个函数会在上面的函数中调用,查询这个账户所拥有的所有权限，返回的类型为GrantedAuthority类型的Collection
    public Collection<GrantedAuthority> findUserAuthorities2(String username) {
        //定义一个接收GrantedAuthority类型的List
        List<GrantedAuthority> autthorities = new ArrayList<GrantedAuthority>();
        //定义一个Account实体
        Account account = new Account();

        List<Authority> authorityList;
            account.setUserName(username);
        //查询一个Account的所有的权限
            authorityList = this.accountService.queryForAuthorities(account);

        if (authorityList == null || authorityList.size() == 0) {
            return autthorities;
        } else {
            for (Authority auth : authorityList) {
                autthorities.add(new SimpleGrantedAuthority(auth.getId()));
            }
            return autthorities;
        }
    }
}
