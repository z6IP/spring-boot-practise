package top.zhz.week1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meetings")
public class MeetingController {
    private final MeetingService meetingService;
    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }
    @PostMapping("/check")
    public ResponseEntity<String> checkAvailability(@RequestBody Meeting meeting) {
        if (meetingService.isRoomAvailable(meeting)) {
            meetingService.addMeeting(meeting);
            return ResponseEntity.ok(" 会议室可⽤，会议已预订！");
        } else {
            return ResponseEntity.status(409).body(" 会议室不可⽤！");
        }
    }
}
