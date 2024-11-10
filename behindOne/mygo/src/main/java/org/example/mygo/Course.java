package org.example.mygo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private BigInteger id;
    private String name;
    private String company;
    private String executor;
    private Integer cost=0;
    private String plan;
    private String instructorName;
    private String instructorTitle;
    private String instructorField;
    private String instructorEmail;
    private String instructorPhone;
    private String status="未开始";
    private String trainingContent;
    private String trainingStartTime;
    private String trainingEndTime;
    private String trainingLocation;
    private String evaluation_status;//一般用不上，只有学生查询课程评价界面有
    private String pay_status;
    private String staff;

}
