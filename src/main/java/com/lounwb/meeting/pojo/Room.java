package com.lounwb.meeting.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("meeting_room_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Room {
    @TableId
    //会议室ID
    private String roomId;
    //门牌号
    private Integer roomNum;
    //会议室名称
    private String roomName;
    //最多容纳人数
    private Integer capacity;
    //当前状态(0启用  1已占用)
    private Integer status;
    //备注
    private String description;
}
