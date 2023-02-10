package com.lounwb.meeting.service.impl;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lounwb.meeting.mapper.EmployeeMapper;
import com.lounwb.meeting.pojo.Employee;
import com.lounwb.meeting.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getParticipantsByMeeting(String meetingId) {
        return employeeMapper.getParticipantsByMeeting(meetingId);
    }

    @Override
    public List<Employee> getEmpByDepId(String depId) {
        return employeeMapper.getEmpByDepId(depId);
    }

    @Override
    public Employee getEmpByNameAndStatus(String username, Integer status) {
        return employeeMapper.getEmpByNameAndStatus(username, status);
    }

    @Override
    public boolean updateStatusById(String id, Integer status) {

        return employeeMapper.updateStatusById(id, status);
    }

}
