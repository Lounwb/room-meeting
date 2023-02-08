package com.lounwb.meeting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lounwb.meeting.pojo.Employee;
import com.lounwb.meeting.pojo.Meeting;

import java.util.List;

public interface MeetingService extends IService<Meeting>{


    List<Meeting> getAllCancelMeetingsByEId(String employeeId);

    List<Meeting> getMeetingsBySubId(String subscriberId);

    List<Meeting> getAllNoCancelMeetingsWithin7DaysByEId(String employeeId);

    boolean cancelMeeting(String meetingId);

    List<Meeting> getMeetingList();
}
