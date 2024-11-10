package org.example.mygo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrolling {
    private String course_name;
    private String student_name;
    private String enrolling_message;
    private int ID;
    private String date;
    private String sex;
    private String company;
    private String duty;
    private String status;
    private String level;
    private String email;
    private int age;
    private String phone;
}
