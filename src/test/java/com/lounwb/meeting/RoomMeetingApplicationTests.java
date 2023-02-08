package com.lounwb.meeting;

import com.lounwb.meeting.utils.DateTimeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RoomMeetingApplicationTests {

    @Test
    void contextLoads() {
        String s1 = DateTimeUtil.getSysTime();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String s2 = DateTimeUtil.getSysTime();
        System.out.println(s1.compareTo(s2)>0?"超过":"还没到");
    }

}
