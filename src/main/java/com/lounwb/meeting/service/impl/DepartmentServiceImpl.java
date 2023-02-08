package com.lounwb.meeting.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lounwb.meeting.mapper.DepartmentMapper;
import com.lounwb.meeting.pojo.Department;
import com.lounwb.meeting.service.DepartmentService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}
