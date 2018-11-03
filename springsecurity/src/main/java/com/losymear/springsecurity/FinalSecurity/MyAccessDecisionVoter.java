package com.losymear.springsecurity.FinalSecurity;

import com.losymear.springsecurity.Repository.RoleUrlMapping;
import com.losymear.springsecurity.domain.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.persistence.Access;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: springsecurity
 * @description:
 * @author: losymear
 * @create: 2018-11-02 18:58
 */

@Slf4j
@Component
@ConditionalOnProperty(name = "testFor", havingValue = "final")
//public class MyAccessDecisionManager implements AccessDecisionManager {
public class MyAccessDecisionVoter implements AccessDecisionVoter {

    @Autowired
    private RoleUrlMapping roleUrlMapping;

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class clazz) {
        return true;
    }

    /**
     * 如果只是简单的字符串匹配，不如直接加下WebSecurityConfigurerAdapter上
     * @param authentication
     * @param object
     * @param configAttributes
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
//    @Override
//    public int vote(Authentication authentication, Object object, Collection collection) {
//        return 0;
//    }

    @Override
    public int vote(Authentication authentication, Object object, Collection configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
       MyAuthentication auth =  (MyAuthentication) authentication;
       UserEntity user= auth.getUserEntity();
        log.info("user{}",user);
        log.info("authorities {}",auth.getAuthorities());
        // 获取用户的role
      Set<String> userRoles= auth.getAuthorities().stream()
                .map(c ->{
                    log.info("用户有authority {}",c);
                       return  ((MyGrantedAuthority) c).getRole();
                        }

                )
        .collect(Collectors.toSet());

      // 用户允许访问的url
        Set<String> allowedStrings = new HashSet<String>();
      userRoles.stream().forEach(role->{
        allowedStrings.addAll(roleUrlMapping.getMap().get(role));
      });

       HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        System.out.println(request.getRequestURL().toString());
        System.out.println(object);
        if(allowedStrings.stream().anyMatch(c->{

            AntPathRequestMatcher antPathMatcher = new AntPathRequestMatcher(c);
            return antPathMatcher.matches(request);
        })){
            return ACCESS_GRANTED;
        }
        log.info("投票不通过");
        return ACCESS_DENIED;



    }
}
