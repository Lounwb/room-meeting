package com.lounwb.meeting.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lounwb.meeting.mapper.MeetingMapper;
import com.lounwb.meeting.pojo.Employee;
import com.lounwb.meeting.pojo.Meeting;
import com.lounwb.meeting.service.MeetingService;
import com.lounwb.meeting.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingServiceImpl extends ServiceImpl<MeetingMapper, Meeting> implements MeetingService {
    @Autowired
    MeetingMapper meetingMapper;


    @Override
    public List<Meeting> getAllCancelMeetingsByEId(String employeeId) {
        List<Meeting> cancelMeetingList = meetingMapper.getAllCancelMeetingsByEId(employeeId);
        return cancelMeetingList;
    }


    @Override
    public List<Meeting> getMeetingsBySubId(String subscriberId) {
        List<Meeting> myMeetingList = meetingMapper.getMeetingsBySubId(subscriberId);
        return myMeetingList;
    }

    @Override
    public List<Meeting> getAllNoCancelMeetingsWithin7DaysByEId(String employeeId) {
        String sysTime = DateTimeUtil.getSysTime();
        List<Meeting> meetingList = meetingMapper.getAllNoCancelMeetingsWithin7DaysByEId(employeeId);
        return meetingList;
    }

    @Override
    public boolean cancelMeeting(String meetingId) {
        String time = DateTimeUtil.getSysTime();
        boolean flag = false;
        flag = meetingMapper.cancelMeeting(meetingId, time);
        return flag;
    }

    @Override
    public List<Meeting> getMeetingList() {
        return meetingMapper.getMeetingList();
    }

}
