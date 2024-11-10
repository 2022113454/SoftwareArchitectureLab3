package org.example.mygo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Summary {
    private String courseName;
    private String teacherName;
    private double aveCourseScore;
    private double aveTeacherScore;
    String[] opinions = new String[]{
            "非常棒", "一般", "还行", "不错哟", "神！", "牛B", "帅！！！", "拯救了我",
            "惊艳", "完美无瑕", "超乎想象", "赞一个", "绝妙", "令人赞叹", "强烈推荐",
            "大爱", "超赞", "给力", "惊艳四座", "爱不释手", "五星级好评", "极佳体验",
            "顶呱呱", "超乎预期", "强烈推荐给朋友", "令人印象深刻", "质量上乘", "物超所值",
            "满分好评", "赞不绝口", "超乎寻常", "堪称完美", "无法用言语形容", "绝妙之作",
            "值得回购", "太棒了", "无敌好用", "眼前一亮", "无可挑剔", "简直完美",
            "太满意了", "爱不释手", "完美匹配", "性价比超高", "强烈推荐给大家"
    };
    //String[] opinions=new String[]{"非常棒","一般","还行","不错哟","神！","牛B","帅！！！","拯救了我"};

}
