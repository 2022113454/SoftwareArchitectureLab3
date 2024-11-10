package org.example.mygo.news;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.mygo.course_application;

import java.util.List;

@Mapper
public interface NewMapper {
    @Select("SELECT * FROM applicationdbm_3")
    List<course_application> findCourseApplicationByIDDirect();
}
