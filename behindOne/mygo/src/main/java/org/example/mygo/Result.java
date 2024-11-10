package org.example.mygo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result <T>{
    private int code;
    private String message;
    private String token="1";
    private String name;
    public static<E> Result<E> success(E data){
        return new Result<>(20000,"操作成功","1","none");
    }
    public static Result success(){
        return new Result<>(20000,"操作成功","2","none");
    }
    public static Result error(){
        return new Result<>(-1,"操作失败","3","none");
    }

    //其实没有这些函数都没关系，有构造函数就行
    public static Result success(String message){
        return new Result<>(20000,"操作成功","2","none");
    }
}
