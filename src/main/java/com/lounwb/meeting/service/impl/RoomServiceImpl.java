package com.lounwb.meeting.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lounwb.meeting.mapper.RoomMapper;
import com.lounwb.meeting.pojo.Room;
import com.lounwb.meeting.service.RoomService;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {

}
