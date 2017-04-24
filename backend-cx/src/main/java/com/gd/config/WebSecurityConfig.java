package com.gd.config;

import com.gd.security.*;

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
/*
@Configuration
启动springboot扫描这个配置文件
@EnableWebSecurity
禁用Boot的默认Security配置，配合@Configuration启用自定义配置（需要扩展WebSecurityConfigurerAdapter）
 */
@Configuration
@SuppressWarnings("all")
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public static String REALM = "MY_REALM";
    //我自己定义的验证用户登录的用户名和密码
    @Autowired
    private SecurityUserService securityUserService;
    //我自己定义,查询当前url需要的权限id的
    @Autowired
    private MySecurityMetadataSource mySecurityMetadataSource;

    /*
    这个configure就是验证用户登录的用户名和密码是否正确的
    注入的authenticationProvider在下面定义的
     */
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(securityUserService);

        auth.authenticationProvider(authenticationProvider());
    }

    /*
    这个configure就是验证账户的权限和当前url所需要的权限是否匹配
    注入的mySecurityMetadataSource：在security文件夹下，查询当前访问的url所需要的全部权限
    注入的accessDecisionManager：在后面定义的，返回时security文件夹下就MyAccessDecisionManager，检验用户的权限是否满足当前url权限
    注入的authentcationManagerBean：在后面定义，是重写的方法[-
     */
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("*").permitAll();

//        /*
        //这里的定义为任何请求都要拦截，经过自定义的ObjectPostProcessor
        http.authorizeRequests().anyRequest().authenticated().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                fsi.setSecurityMetadataSource(mySecurityMetadataSource);
                fsi.setAccessDecisionManager(accessDecisionManager());
                fsi.setAuthenticationManager(authenticationManagerBean());
                return fsi;
            }
        });
        //然后定义匿名不允许，Basic提交需要验证，使用的注入为security文件夹下的CustomBasicAuthenticationEntryPoint文件
        http.anonymous().disable();
        http.authorizeRequests().anyRequest().authenticated().expressionHandler(webSecurityExpressionHandler()).and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthentryPoint());

//        */

        http.csrf().disable();
        //http.regexMatcher("/pc").formLogin().loginPage("/login.html");
    }

    /*
    定义不需要拦截的请求
     */
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
    /*
    这个bean注入到了configure(HttpSecurity http)中，返回的是自己写的MyAccessDecisionManager，放在security目录下，验证用户是否有权限访问url的
     */

    @Bean(name = "accessDecisionManager")
    public AccessDecisionManager accessDecisionManager() {

        return new MyAccessDecisionManager();
    }

    @Bean(name = "expressionHandler")
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler webSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
        return webSecurityExpressionHandler;
    }
    /*
    定义Basic提交验证的，注入的文件在security文件夹下的CustomBasicAuthenticationEntryPoint
     */
    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthentryPoint() {
        return new CustomBasicAuthenticationEntryPoint();
    }
    //用户登录的盐
    @Bean(name = "saltSource")
    public ReflectionSaltSource saltSource() {
        ReflectionSaltSource saltSource = new ReflectionSaltSource();
        saltSource.setUserPropertyToUse("salt");
        return saltSource;
    }
    /*
    configure(AuthenticationManagerBuilder auth)中注入的bean，这个是用来验证用户名和密码是否正确的，
    在这个类中注入了
    passwordEncoder：密码加密规则，在下面定义的
    securityUserService：在security文件夹下，SecurityUserService类
    saltSource：盐信息，也就是密码之后再加入一段随机的md5加密的数据，在上面定义的

     */
    @Bean(name = "authenticationProvider")
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(securityUserService);
        authenticationProvider.setSaltSource(saltSource());
        return authenticationProvider;
    }

    //用户登录的密码加密方式
    @Bean
    public Md5PasswordEncoder passwordEncoder() {
        return new Md5PasswordEncoder();
    }


}
