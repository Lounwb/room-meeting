package com.lounwb.meeting.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lounwb.meeting.pojo.Meeting;

import java.util.List;

public interface MeetingMapper extends BaseMapper<Meeting> {

    List<Meeting> getAllCancelMeetingsByEId(String employeeId);

    List<Meeting> getMeetingsBySubId(String subscriberId);

    List<Meeting> getAllNoCancelMeetingsWithin7DaysByEId(String employeeId);

    boolean cancelMeeting(String meetingId, String time);

    List<Meeting> getMeetingList();

}
