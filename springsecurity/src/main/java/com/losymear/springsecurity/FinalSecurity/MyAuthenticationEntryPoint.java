package com.losymear.springsecurity.FinalSecurity;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: springsecurity
 * @description:
 * @author: losymear
 * @create: 2018-11-02 17:07
 */

@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
        res.setCharacterEncoding("UTF-8");
        res.setContentType("application/json");

        // TODO: 使用Jackson的ObjectMapper
        // TODO: 用req获取访问的url，返回
        res.getWriter().println("{\"code\":403,\"message\":\"用户验证失败！！！\",\"data\":\"\"}");
        res.getWriter().flush();

    }
}
