package top.zhz.week1;

import java.time.LocalDate;
import java.time.LocalTime;
public class Meeting {
    private Long id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public boolean isOverlapping(Meeting other) {
        return this.date.equals(other.date) && (this.startTime.isBefore(other.endTime) && this.endTime.isAfter(other.startTime));
    }
}
