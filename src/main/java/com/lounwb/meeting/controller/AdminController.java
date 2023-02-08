package com.lounwb.meeting.controller;

import com.lounwb.meeting.pojo.Room;
import com.lounwb.meeting.service.RoomService;
import com.lounwb.meeting.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    RoomService roomService;

    @GetMapping("/mtr/add")
    public String add(){
        return "admin/meeting/room/add_room";
    }
    @PostMapping("/mtr/doAdd")
    public String doAdd(Room room){
        room.setRoomId(UUIDUtil.getUUID());
        roomService.save(room);
        return "redirect:/mt/notifications.html";
    }
}
