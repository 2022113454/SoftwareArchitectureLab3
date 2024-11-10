package org.example.mygo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageExecutor {
    private int code;
    private int count;
    private List<Executor> list;
}
