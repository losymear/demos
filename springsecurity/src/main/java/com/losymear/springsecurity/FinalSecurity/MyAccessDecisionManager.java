package com.losymear.springsecurity.FinalSecurity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @program: springsecurity
 * @description:
 * @author: losymear
 * @create: 2018-11-02 18:58
 */

@Slf4j
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        System.out.println(authentication);
        log.info("AcessDecisionManager得到authentication {}, object {}, configAttributes {}",authentication,object,configAttributes);

    }
}
