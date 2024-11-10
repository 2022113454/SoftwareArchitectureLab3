package org.example.mygo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class course_evaluation {
    private String course_name; //课程名
    private String student_name;//学生姓名
    private double tutor_evaluation;   //对讲师的星级评价
    private double course_evaluation;  //对课程的信息评价
    private String suggestion;  //对课程的文本建议
    private enum evaluation_status{YES,NO};
    private evaluation_status evaluation_status;//课程评价
    private String pay_status;//缴费状态
}
