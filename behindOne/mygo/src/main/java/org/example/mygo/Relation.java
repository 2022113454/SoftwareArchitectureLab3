package org.example.mygo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Relation {
    private String course_name;
    private String student_name;
    private String suggestion;
    private double tutor_evaluation;
    private double course_evaluation;
    private String reason;
    private String evaluation_status;
    private String pay_status;
}
