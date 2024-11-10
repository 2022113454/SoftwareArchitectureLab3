package org.example.mygo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class attendanceRecording {
    private int attendanceID;   //签到ID
    private String attendanceStudent;   //签到学生
    private String attendanceCourseName;    //课程名
    private String attendanceStaff;    //签到负责现场工作人员
    private String attendanceStudentSex;    //签到学生性别
    private String attendanceStudentCompany;    //签到学生公司
    private String attendanceStudentJob;    //签到学生职位
    private String attendanceStudentEmail;  //签到学生邮箱
    private String attendanceStatus;    //签到状态（YES，NO）

}
