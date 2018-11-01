package com.losymear.springsecurity.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @program: springsecurity
 * @description:
 * @author: losymear
 * @create: 2018-10-31 17:13
 */
@Slf4j
@ConditionalOnProperty(name = "testFor", havingValue = "basic")
@RestController
public class TestController {

    @GetMapping("/test/permitDefault/string")
    public String getString1() {
        return "permitDefault";
    }


    @GetMapping("/test/anyUser/string")
    public String getString2() {
        return "anyUser";
    }

    @GetMapping("/test/userDBA/string")
    public String getString3() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        System.out.println(
                authentication.getAuthorities()
        );
        log.info("获取到principal {}", principal);
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            log.info("principal为UserDetails 用户名为{}", username);
        } else {
            String username = principal.toString();
            log.info("用户名为{}", username);
        }
        return "userDBA";
    }


    /**
     * 只允许USER角色访问
     * @return
     */
    @PostFilter("hasRole(\"ROLE_USER\")")
    @GetMapping("/test/annotation/hasRoleUSER")
    @PreAuthorize("hasRole('USER')")
    public String hasRole(){
        return "hasRole";
    }

    /**
     * 只允许USER角色访问，使用@Secured注解
     * @return
     */
    @GetMapping("/test/annotation/hasRoleUSER2")
    @Secured("ROLE_USER")
    public String hasRole2(){
        return "hasRole2";
    }


    /**
     * postFilter功能
     * @return
     */
    @PostFilter("filterObject.size()>2")
    @GetMapping("/test/filter")
    public Collection filter(){
        ArrayList arrayList = new ArrayList();
        // 长度为1，会被过滤掉
        arrayList.add(new ArrayList<>(Arrays.asList("c")));
        arrayList.add(new ArrayList<>(Arrays.asList("a","b","c","4")));


            return arrayList;
    }

    /**
     * 测试hasPermisson，调用PermissionEvaluator的方法，默认返回false。自定义的MyPermissionEvaluator会返回true，请取消@Configuration的注释
     * @see com.losymear.springsecurity.Config.MyPermissionEvaluator
     * @return
     */
    @PreAuthorize("hasPermission(authentication,\"test\")")
    @GetMapping("/test/permission")
    public String testPermission(){
        return "permit pass";

    }
}
