package org.example.mygo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageAttendance {
    private int code;
    private int count;
    private List<attendanceRecording> list;
    private List<Course> courses;
}
