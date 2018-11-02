package com.losymear.springsecurity.FinalSecurity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: springsecurity
 * @description: 这是一个filter
 * @author: losymear
 * @create: 2018-11-02 11:53
 */

@Slf4j
@ConditionalOnProperty(name = "testFor", havingValue = "final")
public class AuthenticationTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String uuid = httpRequest.getHeader("uuid");
        String digest = httpRequest.getHeader("digest");
        String salt = httpRequest.getHeader("salt");

        // 项目一种可行的验证方式为：
        // 客户端用secret+salt+token来生成digest，secret是客户端和服务端共同保持的
        // token为用户登录成功时服务端返回给客户端的，之后客户端请求传输在请求头uuid，服务端用uuid在服务端取token
        // 服务端用保存的secret、请求头中的salt、用uuid换取的token来校验digest
        if (uuid == null) {
            // NOTICE: 匿名用户
            log.info("filter得到一个匿名用户");
            filterChain.doFilter(request, response);
        }
        // 如果有uuid，那么请求头必须要有salt和digest
        else if (digest == null || salt == null) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");

            response.getWriter().println("{\"code\":403,\"message\":\"校验参数不全\",\"data\":\"\"}");
            response.getWriter().flush();
            return;

        } else {
            Authentication authentication = new MyAuthentication(uuid, digest, salt);
            log.info("设置了authentication {}", authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);

        }
        // TODO: 2.如果不存在uuid，则用匿名用户
    }


}
