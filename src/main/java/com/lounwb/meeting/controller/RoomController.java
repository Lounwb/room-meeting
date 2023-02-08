package com.lounwb.meeting.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lounwb.meeting.pojo.Room;
import com.lounwb.meeting.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/mtr")
public class RoomController {
    @Autowired
    RoomService roomService;
    @GetMapping( {"/", ""})
    public String roomList(Model model){
        List<Room> roomList = roomService.list();
        model.addAttribute("roomList", roomList);
        return "meeting/room/meeting_rooms";
    }
    @GetMapping("/detail")
    public String detail(@RequestParam("roomId") String roomId, Model model){
        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        QueryWrapper<Room> eq = queryWrapper.eq("room_id", roomId);
        Room room = roomService.getOne(eq);

        model.addAttribute("room", room);
        return "meeting/room/room_details";
    }
    @PostMapping("/update")
    public String update(Room room){
        roomService.updateById(room);
        return "redirect:/mtr";
    }

}
