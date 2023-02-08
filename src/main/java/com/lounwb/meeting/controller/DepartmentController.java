package com.lounwb.meeting.controller;

import com.lounwb.meeting.pojo.Department;
import com.lounwb.meeting.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/dep")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    /**
     * 获取所有部门
     */
    @RequestMapping(value = "/allDeps", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Department> allDeps() {
        List<Department> departmentList = departmentService.list();


        return departmentList;
    }

}
