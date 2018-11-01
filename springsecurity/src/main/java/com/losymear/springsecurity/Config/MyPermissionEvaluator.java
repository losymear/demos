package com.losymear.springsecurity.Config;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

/**
 * @program: springsecurity
 * @description: 取消@Configuration的注释会生效
 * Spring Security的ACL功能是什么?
 * @author: losymear
 * @create: 2018-11-01 19:14
 */

//@Configuration
public class MyPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return true;
    }
}
