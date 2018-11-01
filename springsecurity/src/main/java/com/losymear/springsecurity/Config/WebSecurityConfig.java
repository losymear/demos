package com.losymear.springsecurity.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

/**
 * @program: qumianshi
 * @description:
 * @author: losymear
 * @create: 2018-10-31 15:34
 */


@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled=true)
@ConditionalOnProperty(name = "testFor", havingValue = "basic")
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/login", "/test/permitDefault/**","/test/filter","/test/permission").permitAll()
                .antMatchers("/test/userDBA/**").hasRole("DBA")
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser(User.withDefaultPasswordEncoder().username("user").password("pass").roles("USER"))
                .withUser(User.withDefaultPasswordEncoder().username("DBA").password("pass").roles("DBA"));
    }



}
