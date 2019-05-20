package com.djsenglish.interceptor;

import com.djsenglish.util.JWTUtil;
import com.djsenglish.util.PropertiesUtil;
import com.auth0.jwt.interfaces.Claim;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;


/**
 * @author shuo
 */
@Configuration
public class TokenInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);
    // 设置不拦截的路径


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        boolean flag = false;
        String token = request.getHeader("token");
        if (token == null) {
            // 跳转返回未登录
            request.getRequestDispatcher("/user/need_login.do").forward(request, response);
            logger.info("未登录");
        } else {
            try {
                Map<String, Claim> map = JWTUtil.verifyToken(token);
                int id = map.get("id").asInt();
                request.setAttribute("id", id);
                return true;
            } catch (Exception e) {
                request.getRequestDispatcher("/user/need_login.do").forward(request, response);
                logger.info("登录过期");
            }
        }

        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
