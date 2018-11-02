package com.losymear.springsecurity.FinalSecurity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @program: springsecurity
 * @description:
 * @author: losymear
 * @create: 2018-11-02 17:30
 */

@Data
public class MyGrantedAuthority implements GrantedAuthority {
    private String role;

    @Override
    public String getAuthority() {
        return this.role;
    }
    

    public MyGrantedAuthority(String role) {
        this.role = role;
    }
}
