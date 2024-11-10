package org.example.mygo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class course_application {
    private BigInteger applicationID; //申请ID
    private String company;    //公司名称
    private String applicant;   //申请人姓名
    private String topic; //培训主题

    private String time;  //培训申请提交时间

    private String date;    //培训时间

    private String content; //培训内容
    private int scale;    //培训规模(多少人)
    private String remarks;   //培训备注
    private String email;//邮件

    private double fee; //培训费用


    private String status;

}