package org.example.mygo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Executor {
    private BigInteger id;
    private String name;
    private String title;
    private String experience;
    private String email;
    private String status;
}
