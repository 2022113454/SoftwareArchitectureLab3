package org.example.mygo.mycat;

import jakarta.annotation.sql.DataSourceDefinitions;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.example.mygo.course_application;
import org.springframework.beans.factory.annotation.Qualifier;


public interface MycatMapper {
    @Insert("INSERT INTO applicationdbm(company,applicant,topic,time,date,content,scale,remarks,fee,status) VALUES (#{company},#{applicant},#{topic},#{time},#{date},#{content},#{scale},#{remarks},'0.00','待处理')")
    void saveCourseApplicationInMycat(course_application course_application);


}
