package com.gd.config;

import com.gd.security.*;

import com.gd.service.account.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import org.springframework.security.access.AccessDecisionManager;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;


/**
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
@Configuration
@SuppressWarnings("all")
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public static String REALM = "MY_REALM";

    @Autowired
    private SecurityUserService securityUserService;
    @Autowired
    private AccountServiceImpl accountService;

//    @Autowired
//    private MySecurityMetadataSource mySecurityMetadataSource;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(securityUserService);

        auth.authenticationProvider(authenticationProvider());
    }


    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login").permitAll();
//                    http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
        http.authorizeRequests().anyRequest().authenticated().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                fsi.setSecurityMetadataSource(securityMetadataSource());
                fsi.setAccessDecisionManager(accessDecisionManager());
                fsi.setAuthenticationManager(authenticationManagerBean());
                return fsi;
            }
        });
        http.csrf().disable();
        http.anonymous().disable();
        http.authorizeRequests().anyRequest().authenticated().expressionHandler(webSecurityExpressionHandler()).and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthentryPoint());
        //http.regexMatcher("/pc").formLogin().loginPage("/login.html");
    }

    @Bean(name = "securityMetadataSource")
    FilterInvocationSecurityMetadataSource securityMetadataSource() {
        return new MySecurityMetadataSource();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui.html", "/swagger/**", "/v2/api-docs", "/webjars/**"
                , "/swagger-resources/**", "/images/**", "/configuration/**");
    }

    @Bean(name = "authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() {
        AuthenticationManager authenticationManager = null;
        try {
            authenticationManager = super.authenticationManagerBean();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authenticationManager;
    }

    @Bean(name = "accessDecisionManager")
    public AccessDecisionManager accessDecisionManager() {

        return new MyAccessDecisionManager();
    }

    @Bean(name = "expressionHandler")
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler webSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
        return webSecurityExpressionHandler;
    }

    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthentryPoint() {
        return new CustomBasicAuthenticationEntryPoint();
    }

    @Bean(name = "saltSource")
    public ReflectionSaltSource saltSource() {
        ReflectionSaltSource saltSource = new ReflectionSaltSource();
        saltSource.setUserPropertyToUse("salt");
        return saltSource;
    }
    //用系统默认的验证密码通过
    @Bean(name = "authenticationProvider")
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(securityUserService);
        authenticationProvider.setSaltSource(saltSource());
        return authenticationProvider;
    }
   //自定义密码验证:不用
   /* @Bean(name = "authenticationProvider")
    public DaoAuthenticationProvider authenticationProvider(){
        MyAuthenticationProvider myAuthenticationProvider = new MyAuthenticationProvider();
        myAuthenticationProvider.setAccountService(accountService);
        myAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        myAuthenticationProvider.setUserDetailsService(securityUserService);
        myAuthenticationProvider.setSaltSource(saltSource());
        return myAuthenticationProvider;
    }*/

    @Bean
    public Md5PasswordEncoder passwordEncoder() {
        return new Md5PasswordEncoder();
    }


}
