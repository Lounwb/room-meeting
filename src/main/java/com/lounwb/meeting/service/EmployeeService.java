package com.lounwb.meeting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lounwb.meeting.pojo.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService extends IService<Employee> {
    List<Employee> getParticipantsByMeeting(String meetingId);
    List<Employee> getEmpByDepId(String depId);

    Employee getEmpByNameAndStatus(String username, Integer status);

    boolean updateStatusById(String id, Integer status);
}
