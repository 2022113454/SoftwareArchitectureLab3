package org.example.mygo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageSelectCourse {
    private int code;
    private int countOne;
    private int countTwo;
    private Set<Course> unselect;
    private Set<Course> selected;
}
