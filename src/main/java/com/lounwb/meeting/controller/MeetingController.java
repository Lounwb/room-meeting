package com.lounwb.meeting.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lounwb.meeting.pojo.Employee;
import com.lounwb.meeting.pojo.Meeting;
import com.lounwb.meeting.pojo.Room;
import com.lounwb.meeting.service.EmployeeService;
import com.lounwb.meeting.service.MeetingService;
import com.lounwb.meeting.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/mt")
public class MeetingController {
    @Autowired
    MeetingService meetingService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    RoomService roomService;
    @GetMapping("/notifications.html")
    public String notifications(HttpSession session, Model model){
        Employee user = ((Employee) session.getAttribute("user"));
        String employeeId = user.getEmployeeId();

        List<Meeting> meetingList = meetingService.getAllNoCancelMeetingsWithin7DaysByEId(employeeId);

        model.addAttribute("meetingList", meetingList);
        List<Meeting> cancelMeetingList = meetingService.getAllCancelMeetingsByEId(employeeId);
        model.addAttribute("cancelMeetingList", cancelMeetingList);

        return "meeting/notifications";
    }
//    @GetMapping("/mt")
//    public String t(Model model){
//        meetingService.list();
//        model.addAttribute("")
//        return "";
//    }

    /**
     * 获取meeting_id=id的所有
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/detail")
    public String detail(@RequestParam("id") String id, Model model){
        QueryWrapper<Meeting> queryWrapper = new QueryWrapper<>();
        QueryWrapper<Meeting> one = queryWrapper.eq("meeting_id", id);
        Meeting meeting = meetingService.getOne(one);
        model.addAttribute("meeting", meeting);
        List<Employee> employeeList = employeeService.getParticipantsByMeeting(id);
        model.addAttribute("employeeList", employeeList);
        return "meeting/meeting_details";
    }
    @GetMapping("/book")
    public String book(Model model){
        List<Room> roomList = roomService.list();
        model.addAttribute("roomList", roomList);
        return "meeting/book_meeting";
    }
    @GetMapping("/bookings")
    public String bookings(HttpSession session, Model model){
        Employee user = (Employee) session.getAttribute("user");
        String subscriberId = user.getEmployeeId();
        List<Meeting> myMeetingList = meetingService.getMeetingsBySubId(subscriberId);
        model.addAttribute("myMeetingList", myMeetingList);

        return "meeting/bookings";
    }
    @GetMapping("/sub_detail")
    public String subDetail(@RequestParam("id") String id, Model model){
        Meeting meeting = meetingService.getById(id);
        model.addAttribute("meeting", meeting);
        List<Employee> participants = employeeService.getParticipantsByMeeting(id);
        model.addAttribute("participants", participants);

        return "meeting/sub_details";
    }

    @GetMapping("/cancel")
    public String cancel(@RequestParam("meetingId") String meetingId){
        boolean flag = meetingService.cancelMeeting(meetingId);
        return "redirect:/mt/bookings";
    }
    @GetMapping("/search")
    public String getSearch(@RequestParam(value = "pn", defaultValue = "1")Integer pn, Model model){
        Page<Meeting> page = new Page<>(pn, 5);
        Page<Meeting> meetingPage = meetingService.page(page, null);
        model.addAttribute("page", meetingPage);
        return "meeting/search_meetings";
    }
    @PostMapping("/search")
    public String postSearch(){
        return "redirect:/mt/search";
    }
}
