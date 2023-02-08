package com.lounwb.meeting.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("employee_tbl")
@Data
public class Employee implements Serializable {
    @TableId
    //员工ID
    private String employeeId;
    //员工姓名
    private String employeeName;
    //用户名
    private String username;
    //电话
    private String phone;
    //电子邮件
    private String email;
    //状态（0表示未审批  1表示审批通过 2表示审批未通过）
    private Integer status;
    //部门编号
    private String departmentId;
    //密码
    private String password;
    //角色（1表示普通用户  2表示管理员）
    private Integer role;
}
