package com.losymear.springsecurity.FinalSecurity;

import com.losymear.springsecurity.domain.UserEntity;
import lombok.Data;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Collection;

/**
 * @program: springsecurity
 * @description: 参考https://gist.github.com/adatta02/1103f7a81a5e6c1931bf9a09b6131d59
 * @author: losymear
 * @create: 2018-11-02 15:55
 */
@Data
public class MyAuthentication extends AbstractAuthenticationToken {
    private String uuid;
    private String salt;
    private String digest;
    private UserEntity userEntity;


    public MyAuthentication(String uuid,String digest,String salt){
        super(Arrays.asList());
        this.uuid=uuid;
        this.digest=digest;
        this.salt = salt;
    }
    public MyAuthentication(Collection<? extends GrantedAuthority> authorities, UserEntity userEntity) {
        super(authorities);
        this.uuid = uuid;
        this.userEntity = userEntity;
    }
    // 用不到该方法
    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userEntity;
    }

}
