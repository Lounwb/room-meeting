package com.lounwb.meeting.interceptor;

import com.lounwb.meeting.pojo.Employee;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminVerifyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Employee user = (Employee) session.getAttribute("user");
        Integer role = user.getRole();
        if(role == 2){
            return true;
        }else {
            return false;
        }
    }
}
