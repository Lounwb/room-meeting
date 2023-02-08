package com.lounwb.meeting.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lounwb.meeting.pojo.Employee;

import java.util.List;

public interface EmployeeMapper extends BaseMapper<Employee> {
    List<Employee> getParticipantsByMeeting(String meetingId);

    List<Employee> getEmpByDepId(String depId);

    Employee getEmpByNameAndStatus(String username, Integer status);
}
