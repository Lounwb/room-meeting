package com.lounwb.meeting.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lounwb.meeting.pojo.Department;
import com.lounwb.meeting.pojo.Employee;
import com.lounwb.meeting.service.DepartmentService;
import com.lounwb.meeting.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/emp")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    DepartmentService departmentService;
    @ResponseBody
    @GetMapping(value = "/getEmpByDepId", produces = "application/json;charset=UTF-8")
    public List<Employee> getEmpByDepId(@RequestParam("depId") String depId){
        List<Employee> empList = employeeService.getEmpByDepId(depId);
        return empList;
    }
    @GetMapping("/register")
    public String register(Model model){
        List<Department> departmentList = departmentService.list();
        model.addAttribute("departmentList", departmentList);

        return "emp/register";
    }

    @PostMapping("/doRegister")
    public String doRegister(Employee employee, HttpSession session){
        employeeService.updateById(employee);
        session.setAttribute("user", employee);
        return "redirect:/mt/notifications.html";
    }
}
