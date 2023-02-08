package com.lounwb.meeting.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lounwb.meeting.pojo.Department;
import com.lounwb.meeting.pojo.Employee;
import com.lounwb.meeting.pojo.Room;
import com.lounwb.meeting.service.DepartmentService;
import com.lounwb.meeting.service.EmployeeService;
import com.lounwb.meeting.service.RoomService;
import com.lounwb.meeting.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * 登录
 */
@Slf4j
@Controller
public class LoginController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    DepartmentService departmentService;



    //登陆页面
    @GetMapping({"/", "/login"})
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(Employee employee, Model model, HttpSession session) {
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        Employee user;
        //是否已注册未批准
        user = employeeService.getEmpByNameAndStatus(employee.getUsername(), 0);
        if(user != null){
            model.addAttribute("msg", "已申请注册,请耐心等待审核");
            return "login";
        }
        QueryWrapper<Employee> eq1 = queryWrapper.eq("username", employee.getUsername()).eq("password", employee.getPassword()).eq("status", 1);
        user = employeeService.getOne(eq1);
        if(user != null){
            session.setAttribute("user", user);
            return "redirect:/mt/notifications.html";
        }else{
            model.addAttribute("msg", "账号密码错误");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("/changePwd")
    public String changePwd(){

        return "change_pwd";
    }
//    @GetMapping("/changePwd")
//    public String changePwd(@RequestParam("newPwd") String newPwd, Employee employee, Model model){
//        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
//        QueryWrapper<Employee> eq = queryWrapper.eq("employee_id", employee.getEmployeeId());
//        Employee employee2 = employeeService.getOne(eq);
//        System.out.println("原密码:" + employee2.getPassword());
//        System.out.println("用户输入密码:" + employee.getPassword());
//        System.out.println("新密码:" + newPwd);
//
//        return "redirect:/notifications.html";
//    }

    @PostMapping("/doChange")
    public String doChange(@RequestParam("newPwd") String newPwd, Employee employee, Model model, HttpSession session){
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        QueryWrapper<Employee> eq = queryWrapper.eq("employee_id", employee.getEmployeeId());
        Employee employee2 = employeeService.getOne(eq);
        String originalPwd = employee2.getPassword();
        String userInputPwd = employee.getPassword();
        if(!originalPwd.equals(userInputPwd)){
            model.addAttribute("msg", "输入密码与原密码不一致,修改密码失败");
            return "change_pwd";
        }else {
            employee2.setPassword(newPwd);
            employeeService.updateById(employee2);

            session.removeAttribute("user");
            model.addAttribute("msg", "修改密码成功！请重新登陆");
            return "login";
        }
    }
    @GetMapping("/register")
    public String register(Model model){
        List<Department> departmentList = departmentService.list();
        model.addAttribute("departmentList", departmentList);
        return "register";
    }
    @PostMapping("/doRegister")
    public String doRegister(Employee employee, Model model){
        employee.setEmployeeId(UUIDUtil.getUUID());
        employee.setStatus(0);
        employee.setRole(1);
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        QueryWrapper<Employee> eq = queryWrapper.eq("username", employee.getUsername());
        Employee one = employeeService.getOne(eq);
        if(one != null){
            model.addAttribute("msg", "当前用户名已存在");
            return register(model);
        }else{
            employeeService.save(employee);
            model.addAttribute("msg", "已申请注册,请耐心等待审核");
            return "login";
        }
    }
}
