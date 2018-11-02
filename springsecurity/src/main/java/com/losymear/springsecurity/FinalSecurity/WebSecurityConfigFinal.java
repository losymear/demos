package com.losymear.springsecurity.FinalSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @program: springsecurity
 * @description:
 * @author: losymear
 * @create: 2018-11-02 11:00
 */

@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled=true)
@ConditionalOnProperty(name = "testFor", havingValue = "final")
@EnableWebSecurity
public class WebSecurityConfigFinal extends WebSecurityConfigurerAdapter {
    @Autowired MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    @Autowired MyAccessDenied myAccessDenied;
    @Autowired MyAccessDecisionManager myAccessDecisionManager;
    @Autowired MyAuthenticationProvider myAuthenticationProvider;


//    @Autowired
//    private CustomAuthenticationProvider customAuthenticationProvider;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(customAuthenticationProvider);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().permitAll()
//                .accessDecisionManager(myAccessDecisionManager)
                .and()
                .httpBasic().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(new AuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling()
                // 用户未验证通过
                .authenticationEntryPoint(myAuthenticationEntryPoint)
                // accessDenied只对验证了但是无权限的用户有效
                .accessDeniedHandler(this.myAccessDenied);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthenticationProvider);
    }
}
