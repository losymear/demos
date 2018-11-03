package com.losymear.springsecurity.FinalSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

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
    @Autowired
    MyAccessDecisionVoter myAccessDecisionManager;
    @Autowired MyAuthenticationProvider myAuthenticationProvider;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<AccessDecisionVoter<? extends Object>> decisionVoters
                = Arrays.asList(
                myAccessDecisionManager,
                // 查看@PreAuthorize注解，以及http的antMatchers配置
                new WebExpressionVoter()
               );
        http
                .authorizeRequests()
                // 以下URL允许所有请求。当然这是默认的
                .antMatchers("/permitAll").permitAll()
                // 允许ROLEONE访问
                .antMatchers("/permitROLE_ONE_ONE","permitROLE_ONE_FAKE").hasRole("ROLEONE")
                // 允许ROLEFAKE访问
                .antMatchers("/permitROLE_FAKE_ONE","permitROLE_FAKE_FAKE").hasRole("ROLEFAKE")
                .anyRequest()
//                .permitAll()
                .authenticated()
//                .accessDecisionManager(new UnanimousBased(decisionVoters))
                // 只要有任何允许的voter
//                .accessDecisionManager(new AffirmativeBased(decisionVoters))
                .and()
//                .httpBasic().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new AuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling()
                // 用户未验证通过的响应
                .authenticationEntryPoint(myAuthenticationEntryPoint)
                // accessDenied只对验证了但是无权限的用户有效
                .accessDeniedHandler(this.myAccessDenied);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthenticationProvider);
    }
}
