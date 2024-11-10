package org.example.mygo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
    private String courseName;//课程名
    private String courseContent;//课程内容
    private String teacherName;//讲师姓名
    private int amount;//计划招生数量
    private int cost;//课程费用
    private String date;//培训日期
    private String startTime;//培训时间
    private String timestamp;//通知发布日期
    private String location;//培训地点
    private String deadLine;//报名截止日期
    private String note;//备注信息

}
