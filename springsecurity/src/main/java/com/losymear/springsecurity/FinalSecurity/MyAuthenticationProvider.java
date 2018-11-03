package com.losymear.springsecurity.FinalSecurity;

import com.losymear.springsecurity.Repository.UserRepository;
import com.losymear.springsecurity.Repository.UserRoleRepository;
import com.losymear.springsecurity.domain.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MyAuthentication auth = (MyAuthentication) authentication;
        // 如果auth为null，那么就是匿名用户，给一个anonymous权限
        log.info("get an auth{}",auth);
        if (auth.getUuid()==null) {
            log.debug("这是一个匿名用户");
            // 返回一个匿名用户，设置granted为anonymous
            return auth;
        }
        String uuid = auth.getUuid();
        String digest = auth.getDigest();
        String salt = auth.getSalt();

        // NOTICE: 注意这一步实际要做的是从数据库uuid拿到有期限的token，再用token验证token是否正确
        // 代码只是模拟
        UserEntity user = userRepository.findDistinctByUuid(uuid);
        // 如果找不到用户，直接返回会。进入MyAuthenticationEntryPoint
        if(user==null){
            return null;
        }
        // TODO: 如果不存在则抛出
        String token = user.getToken();
        if (token.equals(digest) && token.equals(salt)) {
            auth.setUserEntity(userRepository.findDistinctByUuid(uuid));
            List<String> grands = userRoleRepository.findRolesByUserId(uuid);
            // 从表中读取用户的所有的role
            List<MyGrantedAuthority> grants = grands.stream().map(c -> new MyGrantedAuthority(c)).collect(Collectors.toList());
            // TODO: 这里需要根据数据库获取role
            System.out.println(grands);
            //
            MyAuthentication returnAuth= new MyAuthentication(grants, userRepository.findDistinctByUuid(uuid));
            // NOTICE: 必须调用setAuthenticated，否则会多次调用该Provider！
            returnAuth.setAuthenticated(true);
            return returnAuth;

        } else {
            return null;
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {

        return true;
    }
}
