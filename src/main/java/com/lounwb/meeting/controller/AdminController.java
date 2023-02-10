package com.lounwb.meeting.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lounwb.meeting.pojo.Department;
import com.lounwb.meeting.pojo.Employee;
import com.lounwb.meeting.pojo.Room;
import com.lounwb.meeting.service.DepartmentService;
import com.lounwb.meeting.service.EmployeeService;
import com.lounwb.meeting.service.RoomService;
import com.lounwb.meeting.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    //一页放10条数据
    private static final Integer PAGE_SIZE = 10;
    @Autowired
    RoomService roomService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/mtr/add")
    public String add(){
        return "admin/meeting/room/add_room";
    }
    @PostMapping("/mtr/doAdd")
    public String doAdd(Room room){
        room.setRoomId(UUIDUtil.getUUID());
        roomService.save(room);
        return "redirect:/mt/notifications.html";
    }
    @GetMapping("/dept")
    public String dept(Model model){
        List<Department> departmentList = departmentService.list();
        model.addAttribute("departmentList", departmentList);
        return "admin/department/departments";
    }
    @GetMapping("/emp/approve")
    public String employeeApprove(Model model){
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        QueryWrapper<Employee> eq = queryWrapper.eq("status", 0);
        List<Employee> employeeList = employeeService.list(eq);
        model.addAttribute("employeeList", employeeList);

        return "admin/employee/approve";
    }
    @GetMapping("/emp/doApprove")
    public String employeeDoApprove(@RequestParam("id") String id,
                                    @RequestParam("status") Integer status){
        employeeService.updateStatusById(id, status);

        return "redirect:/admin/emp/approve";
    }
    @GetMapping("/emp/search")
    public String employeeSearch(){
        return "admin/employee/search";
    }
    @PostMapping("/dept/add")
    public String addDepartment(@RequestParam("departmentName") String departmentName){
        Department department = new Department();
        department.setDepartmentId(UUIDUtil.getUUID());
        department.setDepartmentName(departmentName);
        departmentService.save(department);

        return "redirect:/admin/dept";
    }
    @ResponseBody
    @PostMapping(value = "/dept/update", produces = "application/json")
    public String updateDept(Department department) throws JSONException {
        departmentService.updateById(department);
        JSONObject json = new JSONObject();
        json.put("msg", "success");
        return json.toString();
    }

}
