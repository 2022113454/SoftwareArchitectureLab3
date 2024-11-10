package org.example.mygo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    private double total_income;//总收入
    private double course_income;//培训收入
    private double other_income;//其他收入
    private int student_num;//人次
    private int student_fact;//真实学员数
    private int end_course_num;//已结束课程数
    private int doing_course_num;//进行中课程数
    private int tostart_course_num;//未开始课程数
    private int course_num;//开设课程总数
    private int male;//男性数量
    private int female;//女性数量
    private int ageOne;//20岁以下学员数量
    private int ageTwo;//20~40岁学员数量
    private int ageThree;//40岁以上学员数量
    private int company_num;//涉及公司数量
    private int instructor_num;//讲师数量
    private int executor_num;//执行人数量

}
