package com.gd.security;

import com.gd.util.MyJJWTTokenUtils;
import com.gd.util.MySessionMapUtils;
import com.gd.util.MyTokenUtils;
import com.gd.util.MyUsernameTokenUtils;
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

/**
 * security验证用户密码的拦截组件，验证用户的登录密码是否正确
 * 这样我们可以在验证通过之后生成随机的token，并且保存到全局的session map中
 * 并且在session存入标识，从而在其他组件中可以根据标识来使用response回写token
 * 到客户端
 */
public class MyAuthenticationProvider extends DaoAuthenticationProvider {
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
        //得到request，response，session，不过这里的response是null，不能使用
        this.request = request;
        this.response = response;
        this.session = request.getSession();
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
        //如果用户已经登录
        if(MyUsernameTokenUtils.getMyUsernameTokenMap().containsKey(userDetails.getUsername().toString())){
            try {
                response.sendError(403,"User already login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            //密码验证通过！！！,存到map中
            //final String token = MyTokenUtils.getToken(userDetails.getUsername().toString(),userDetails.getPassword().toString());
            final String token = MyJJWTTokenUtils.getMyJJWTToken(userDetails.getUsername(),userDetails.getUsername(),userDetails.getPassword(),"command");
            MySessionMapUtils.getMySessionMap().put(token,userDetails.getAuthorities());
            //并且将<username,token>保存到全局map中，防止用户重复登录
            MyUsernameTokenUtils.getMyUsernameTokenMap().put(userDetails.getUsername().toString(),token);

            this.session.setAttribute("token",token);
            this.session.setAttribute("isJustLogin","true");
            Timer timer= new Timer();
            TimerTask timerTask = new TimerTask() { //创建一个新的计时器任务
                @Override
                public void run() {
                    MySessionMapUtils.getMySessionMap().remove(token);
                    MyUsernameTokenUtils.getMyUsernameTokenMap().remove(userDetails.getUsername().toString());
                    System.out.println("移除当前token，打印map"+userDetails.getUsername().toString());
                    System.out.println(MySessionMapUtils.getMySessionMap());
                    System.out.println("移除当前<username,token>，打印map"+userDetails.getUsername().toString());
                    System.out.println(MyUsernameTokenUtils.getMyUsernameTokenMap());
                }
            };
            //这里设置，10min后执行任务，任务就是移除map中的当前token
            timer.schedule(timerTask,600000);
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

