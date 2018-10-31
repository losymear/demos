package com.losymear.springsecurity.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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


@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        System.out.println(1);
//        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
//        return manager;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/login", "/mobile/login", "/api/auth/**", "/reservations/**","/test/permitDefault").permitAll()
                .antMatchers("/test/userDBA/**").hasRole("DBA")
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER"))
                .withUser(User.withDefaultPasswordEncoder().username("DBA").password("password").roles("DBA"));
    }



}
