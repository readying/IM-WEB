package com.gd.security;

import com.gd.domain.account.Account;
import com.gd.service.account.impl.AccountServiceImpl;
import com.gd.service.userinfo.impl.UserInfoServiceImpl;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
/*
验证用户密码的，不需要注入到spring，这里重写了DaoAuthenticationProvider中的additionalAuthenticationChecks方法
就是验证用户的密码

 */
/**
 * Created by Administrator on 2017/3/23.
 */
public class MyAuthenticationProvider extends DaoAuthenticationProvider{
    private AccountServiceImpl accountService;

    public AccountServiceImpl getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    public static final String USER_NOT_FOUND_PASSWORD = "userNotFoundPassword";
    public PasswordEncoder passwordEncoder;
    public String userNotFoundEncodedPassword;
    public SaltSource saltSource;
    public UserDetailsService userDetailsService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        HttpServletResponse response =  ((ServletRequestAttributes) requestAttributes).getResponse();
        this.request = request;
        this.response = response;
        this.session = request.getSession();
        System.out.println("得到了request+response！！！");
        return super.authenticate(authentication);
    }

    public MyAuthenticationProvider() {
        this.setPasswordEncoder((PasswordEncoder)(new PlaintextPasswordEncoder()));
    }
    @Override
    public void additionalAuthenticationChecks(final UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        Object salt = null;
        if(this.saltSource != null) {
            salt = this.saltSource.getSalt(userDetails);
        }
        if(authentication.getCredentials() == null) {
            this.logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        } else {
            String presentedPassword = authentication.getCredentials().toString();
            if(!this.passwordEncoder.isPasswordValid(userDetails.getPassword(), presentedPassword, salt)) {
                this.logger.debug("Authentication failed: password does not match stored value");
                throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
            }
        }
        //此时密码验证通过
        System.out.println("密码验证通过！！！,写出账户信息");
        Gson gson = new Gson();
        Account account = this.accountService.queryForObjectByUsername(userDetails.getUsername());
        try {
            session.setAttribute("account",gson.toJson(account));
            //response.getWriter().write(gson.toJson(account));
            System.out.println(gson.toJson(account));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void doAfterPropertiesSet() throws Exception {
        Assert.notNull(this.userDetailsService, "A UserDetailsService must be set");
    }
    @Override
    public void setPasswordEncoder(Object passwordEncoder) {
        Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
        if(passwordEncoder instanceof PasswordEncoder) {
            this.setPasswordEncoder((PasswordEncoder)passwordEncoder);
        } else if(passwordEncoder instanceof org.springframework.security.crypto.password.PasswordEncoder) {
            final org.springframework.security.crypto.password.PasswordEncoder delegate = (org.springframework.security.crypto.password.PasswordEncoder)passwordEncoder;
            this.setPasswordEncoder(new PasswordEncoder() {
                public String encodePassword(String rawPass, Object salt) {
                    this.checkSalt(salt);
                    return delegate.encode(rawPass);
                }

                public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
                    this.checkSalt(salt);
                    return delegate.matches(rawPass, encPass);
                }

                private void checkSalt(Object salt) {
                    Assert.isNull(salt, "Salt value must be null when used with crypto module PasswordEncoder");
                }
            });
        } else {
            throw new IllegalArgumentException("passwordEncoder must be a PasswordEncoder instance");
        }
    }
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
        this.userNotFoundEncodedPassword = passwordEncoder.encodePassword("userNotFoundPassword", (Object)null);
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public PasswordEncoder getPasswordEncoder() {
        return this.passwordEncoder;
    }
    @Override
    public void setSaltSource(SaltSource saltSource) {
        this.saltSource = saltSource;
    }
    @Override
    public SaltSource getSaltSource() {
        return this.saltSource;
    }
    @Override
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Override
    public UserDetailsService getUserDetailsService() {
        return this.userDetailsService;
    }
}
