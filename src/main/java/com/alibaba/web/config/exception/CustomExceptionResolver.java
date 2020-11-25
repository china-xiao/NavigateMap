package com.alibaba.web.config.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        if(ex instanceof UnauthorizedException){
            return new ModelAndView("forward:/unauthorized.jsp");
        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        mv.addObject("msg", ex.getMessage());

        return mv;
    }
}
