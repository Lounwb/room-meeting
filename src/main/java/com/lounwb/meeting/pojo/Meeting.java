package com.lounwb.meeting.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@TableName("meeting_tbl")
@ToString
@Data
public class Meeting {
    @TableId
    //会议ID
    private Integer meetingId;
    //会议名称
    private String meetingName;
    //房间号
    private String roomId;
    //谁预订的
    private String subscriberId;
    //参加人数
    private Integer numberOfParticipants;
    //开始时间
    private String startTime;
    //结束时间
    private String endTime;
    //预约时间
    private String reservationTime;
    //取消时间
    private Date canceledTime;
    //会议说明
    private String description;
    //状态（0启用  1已占用）
    private Integer status;
    //取消原因
    private String canceledReason;
}
