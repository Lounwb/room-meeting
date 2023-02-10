package com.lounwb.meeting.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("department_tbl")
public class Department {
    @TableId

    //部门编号
    private String departmentId;
    //部门名称
    private String departmentName;

}
