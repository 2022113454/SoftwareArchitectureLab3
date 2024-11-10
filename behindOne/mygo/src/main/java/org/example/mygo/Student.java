package org.example.mygo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {//学生信息
    private String name;//姓名
    private String sex="男";//性别
    private String company;//所属公司，可填“无”
    private String duty;//工作岗位
    private String level;//技术水平
    private String email;//邮箱
    private int age=21;//年龄
    private String phone;//电话

}
