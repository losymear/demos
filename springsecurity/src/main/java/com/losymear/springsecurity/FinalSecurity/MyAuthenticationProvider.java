package com.losymear.springsecurity.FinalSecurity;

import com.losymear.springsecurity.Repository.UserRepository;
import com.losymear.springsecurity.Repository.UserRoleRepository;
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
//@ConditionalOnProperty(name = "testFor", havingValue = "final")
public class MyAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MyAuthentication auth = (MyAuthentication) authentication;
        log.info("AuthenticationProvider得到aAuthentication对象{}", auth);
        // 如果auth为null，那么就是匿名用户，给一个anonymous权限
        if (auth == null) {
            log.info("这是一个匿名用户");
            // 返回一个匿名用户，设置granted为anonymous
            return new MyAuthentication(Arrays.asList(new MyGrantedAuthority("anonymous")), new UserEntity());
        }
        String uuid = auth.getUuid();
        String digest = auth.getDigest();
        String salt = auth.getSalt();

        // NOTICE: 注意这一步实际要做的是从数据库uuid拿到有期限的token，再用token验证token是否正确
        // 代码只是模拟
        UserEntity user = userRepository.findDistinctByUuid(uuid);
        String token = user.getToken();
        if (token.equals(digest) && token.equals(salt)) {
            auth.setUserEntity(userRepository.findDistinctByUuid(uuid));
            List<String> grands = userRoleRepository.findRolesByUserId(uuid);
            // 从表中读取用户的所有的role
            List<MyGrantedAuthority> grants = grands.stream().map(c -> new MyGrantedAuthority(c)).collect(Collectors.toList());
            // TODO: 这里需要根据数据库获取role
            System.out.println(grants.get(0).getAuthority());
            //
            MyAuthentication returnAuth= new MyAuthentication(grants, userRepository.findDistinctByUuid(uuid));
            // NOTICE: 必须执行，否则会多次调用该Provider！
            System.out.println(returnAuth.getAuthorities());
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
