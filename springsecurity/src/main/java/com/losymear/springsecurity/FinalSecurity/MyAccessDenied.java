package com.losymear.springsecurity.FinalSecurity;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: springsecurity
 * @description:
 * @author: losymear
 * @create: 2018-11-02 16:26
 */

@Component
public class MyAccessDenied implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException e) throws IOException, ServletException {

        res.setCharacterEncoding("UTF-8");
        res.setContentType("application/json");

        // TODO: 使用Jackson的ObjectMapper
        // TODO: 用req获取访问的url，返回
        res.getWriter().println("{\"code\":401,\"message\":\"无访问权限\",\"data\":\"\"}");
        res.getWriter().flush();

    }
}
