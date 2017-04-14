package com.gd.security;

import com.gd.util.MySessionMapUtils;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Athos on 2016-10-16.
 * 这个代码是验证用户所拥有的权限是否==当前url的所需要的权限
 */

@Component("myAccessDecisionManager")
public class MyAccessDecisionManager  implements AccessDecisionManager {



    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if(configAttributes.size()==0){
            return;
        }
        Iterator<ConfigAttribute> ite = configAttributes.iterator();
        //正常情况下的判断权限代码
//        while(ite.hasNext()){
//            ConfigAttribute ca = ite.next();
//            String needRole = (ca).getAttribute();
//            for (GrantedAuthority ga : authentication.getAuthorities()){
//                if (needRole.equals(ga.getAuthority())){
//                    return;
//                }
//            }
//        }
        //token六：下面使用token验证用户是否有权限
        String token = "";
        if(((FilterInvocation) object).getHttpRequest().getHeader("token") != null && !((FilterInvocation) object).getHttpRequest().getHeader("token").equals("")){
            token = ((FilterInvocation) object).getHttpRequest().getHeader("token");
        }
        while(ite.hasNext()){
            ConfigAttribute ca = ite.next();
            String needRole = (ca).getAttribute();
            if(token!="" && !token.equals("")){
                if(MySessionMapUtils.getMySessionMap().get(token) != null){
                    for(Object o:MySessionMapUtils.getMySessionMap().get(token)){
                        GrantedAuthority grantedAuthority = (GrantedAuthority) o;
                        if (needRole.equals(grantedAuthority.getAuthority())){
                            return;
                        }
                    }
                }
            }
        }
        throw new AccessDeniedException("没有权限,拒绝访问!");
    }
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return false;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
