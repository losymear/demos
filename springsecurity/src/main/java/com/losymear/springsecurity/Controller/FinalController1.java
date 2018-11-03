package com.losymear.springsecurity.Controller;

import com.losymear.springsecurity.domain.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

/**
 * @program: springsecurity
 * @description:
 * @author: losymear
 * @create: 2018-11-02 10:08
 */

@RestController
@ConditionalOnProperty(name = "testFor", havingValue = "final")
@Slf4j
public class FinalController1 {

    /**
     * 测试@AuthenticationPrincipal注解的使用
     * @param userEntity
     * @return
     */
    @GetMapping("/testPrincipal")
    public UserEntity testPrincipal(@AuthenticationPrincipal UserEntity userEntity) {
        log.info("/test/permitDefault/string中得到{}",userEntity);
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return userEntity;
    }



    /**
     * 允许所有访问，包括匿名
     * @return
     */
    @GetMapping("/permitAll")
    public String permitAll(){
        return "permit all";
    }


    /**
     * 允许ROLEONE访问
     * @return
     */
    @PreAuthorize("hasRole('ROLEONE')")
    @GetMapping("/permitROLE_ONE_ONE")
    public String permitROLE_ONE_ONE(){
        return "permit roleone";
    }

    /**
     * 允许ROLEONE访问
     * @return
     */
    @PreAuthorize("hasRole('ROLEONE')")
    @GetMapping("/permitROLE_FAKE_ONE")
    public String permitROLE_FAKE_ONE(){
        return "permit roleone";
    }


    /**
     * 允许ROLEFAKE访问
     * @return
     */
    @PreAuthorize("hasRole('ROLEFAKE')")
    @GetMapping("/permitROLE_ONE_FAKE")
    public String permitROLE_ONE_FAKE(){
        return "permit roleone";
    }


    /**
     * 允许ROLEFAKE访问
     * @return
     */
    @PreAuthorize("hasRole('ROLEFAKE')")
    @GetMapping("/permitROLE_FAKE_FAKE")
    public String permitROLE_FAKE_FAKE(){
        return "permit roleone";
    }
}
