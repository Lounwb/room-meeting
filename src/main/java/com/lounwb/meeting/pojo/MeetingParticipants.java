package com.lounwb.meeting.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("meeting_tbl")
@Data
public class MeetingParticipants {
    private String meetingId;
    private String employeeId;
}
