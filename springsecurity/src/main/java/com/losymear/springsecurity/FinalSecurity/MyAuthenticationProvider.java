package com.losymear.springsecurity.FinalSecurity;

import com.losymear.springsecurity.Repository.UserRepository;
import com.losymear.springsecurity.domain.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @program: springsecurity
 * @description:
 * @author: losymear
 * @create: 2018-11-02 11:11
 */

@Component
@Slf4j
@ConditionalOnProperty(name = "testFor", havingValue = "final")
public class MyAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MyAuthentication auth = (MyAuthentication) authentication;
        log.info("AuthenticationProvider得到aAuthentication对象{}",auth);
        // 如果auth为null，那么就是匿名用户，给一个anonymous权限
        if(auth==null){
            log.info("这是一个匿名用户");
            // 返回一个匿名用户，设置granted为anonymous
            return new MyAuthentication(Arrays.asList(new MyGrantedAuthority("anonymous")),new UserEntity());
        }
        String uuid = auth.getUuid();
        String digest = auth.getDigest();
        String salt = auth.getSalt();

        // NOTICE: 注意这一步实际要做的是从数据库uuid拿到有期限的token，再用token验证token是否正确
        // 代码只是模拟
        UserEntity user = userRepository.findDistinctByUuid(auth.getUuid());
        String token = user.getToken();
        if(token.equals(digest)&&token.equals(salt)){
            ((MyAuthentication) authentication).setUserEntity(userRepository.findDistinctByUuid(auth.getUuid()));
            return authentication;

        }else{
            return null;
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {

        return true;
    }
}
