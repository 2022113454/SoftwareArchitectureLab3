package org.example.mygo.mysql;

import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;
import org.apache.ibatis.annotations.Result;
import org.example.mygo.*;
import org.springframework.beans.factory.annotation.Qualifier;

@org.apache.ibatis.annotations.Mapper
public interface MysqlMapper {


    @Select("SELECT * FROM applicationdb WHERE applicationID % 4 = #{ID} % 4 ")
    List<course_application> findCourseApplication_ByID(long ID);


    @Select("SELECT username,password FROM userdb WHERE username = #{name}")
    User findName(@Param(value = "name") String name);

    @Select("insert into userdb(username,password) values(#{name},#{password})")
    void addUser(String name,String password);

    @Select("insert into coursedb(name,company,executor,cost,plan, instructorname,instructortitle,instructorfield,instructoremail,instructorphone,status,staff) values(#{name},#{company},#{executor},#{cost},#{plan}," +
            " #{instructorname},#{instructortitle},#{instructorfield},#{instructoremail},#{instructorphone},#{status},'sta')")
    void addCourse(String name,String company,String executor,int cost,String plan,
                   String instructorname,String instructortitle,String instructorfield,
                   String instructoremail,String instructorphone
            ,String trainingcontent,String trainingstarttime,String trainingendtime,String traininglocation
    ,String status);

    @Select("SELECT * FROM coursedb")
    List<Course> getCourse(String message);
    @Select("SELECT * FROM teacherdb")
    List<Teacher> getTeacher(String message);
    @Select("SELECT * FROM executordb")
    List<Executor> getExecutor(String message);


    @Select("SELECT * FROM userdb")
    List<User> getUser();

    @Update("UPDATE coursedb SET company = #{company},executor = #{executor}," +
            "cost = #{cost},plan = #{plan},instructorname = #{instructorname}," +
            "instructortitle = #{instructortitle},instructorfield = #{instructorfield}," +
            "instructoremail = #{instructoremail},instructorphone = #{instructorphone}," +
            "trainingcontent=#{trainingcontent},trainingstarttime=#{trainingstarttime},trainingendtime=#{trainingendtime}," +
            "traininglocation=#{traininglocation}"+
            " WHERE name = #{name}")
    void updateCourse(String name, String company, String executor, int cost, String plan, String instructorname,
                             String instructortitle, String instructorfield, String instructoremail, String instructorphone
            ,String trainingcontent,String trainingstarttime,String trainingendtime,String traininglocation);
    @Update("UPDATE teacherdb SET title = #{title},major = #{major}," +
            "email = #{email},status = #{status},number=#{number} WHERE name = #{name}")
    void updateTeacher(Teacher teacher);
    @Update("UPDATE executordb SET name = #{name},title = #{title},experience = #{experience}," +
            "email = #{email},status = #{status} WHERE id = #{id}")
    void updateExecutor(BigInteger id,String name,String title,String experience,String email,String status);
    @Update("UPDATE studentdb SET sex= #{sex},company = #{company},duty = #{duty}," +
            "level = #{level},email = #{email} WHERE name = #{name}")
    void updateStudent(String name,String sex,String company,String duty,String level,String email);

    @Update("UPDATE coursedb SET status= #{status} WHERE id = #{id}")
    void setCourseStatus(BigInteger id,String status);

    @Delete("DELETE FROM coursedb WHERE id = #{id}")
    void removeCourse(BigInteger id);
    @Delete("DELETE FROM executordb WHERE id = #{id}")
    void removeExecutor(BigInteger id);
    @Delete("DELETE FROM teacherdb WHERE name = #{name}")
    void removeTeacher(String name);
    @Delete("DELETE FROM course_evaluation_list WHERE student_name = #{studentName} AND course_name=#{courseName}")
    void removeStudent(String courseName,String studentName);

    @Select("insert into teacherdb(name,title,major,email,status,number) values(#{name},#{title},#{major},#{email},#{status},#{number})")
    void addTeacher(Teacher teacher);
    @Select("insert into studentdb(name,sex,company,duty,level,email) values(#{name},#{sex},#{company},#{duty},#{level},#{email})")
    void addStudent(String name, String sex, String company, String duty, String level, String email);
    @Select("insert into noticedb(courseName,courseContent,teacherName,amount,cost,date," +
            "startTime,timestamp,location,deadLine,note)" +
            " values(#{coursename},#{coursecontent},#{teachername},#{amount},#{cost}" +
            ",#{date},#{starttime},#{noticedate},#{location},#{deadline},#{note})")
    void addNotice(String coursename,String coursecontent,String teachername,
                   int amount,int cost,String date, String starttime,
                   String noticedate,String location,String deadline,String note);
    @Select("select * from noticedb")
    List<Notice> getNotice();

    @Select("insert into course_evaluation_list(course_name,student_name,suggestion,tutor_evaluation,course_evaluation) values(#{course_name},#{student_name},#{suggestion},#{tutor_evaluation},#{course_evaluation})")
    void addRelation(String course_name,String student_name,String suggestion,String tutor_evaluation,String course_evaluation);

    @Select("insert into course_evaluation_list(course_name,student_name,reason,evaluation_status) values(#{course_name},#{student_name},#{reason},'YES')")
    void join(String course_name,String student_name,String reason);
    @Select("select * from course_evaluation_list")
    List<Relation> getRelation();
    @Select("select * from studentdb")
    List<Student> getStudent();

    @Select("insert into executordb(id,name,title,experience,email,status) values(#{id},#{name},#{title},#{experience},#{email},#{status})")
    void addExecutor(BigInteger id,String name,String title,String experience,String email,String status);

    //开始000000000000000000000000000000000000000000000000000000000000000
    @Select("Select * from applicationDB LIMIT #{offset}, #{limit}")
    List<course_application> findApplicationAll(@Param("offset") int offset, @Param("limit") int limit);

    @Select("Select * from applicationDB LIMIT #{offset}, #{limit}")
    List<course_application> selectPart(@Param("offset") int offset, @Param("limit") int limit);

    @Insert("INSERT INTO applicationDB(company,applicant,topic,time,date,content,scale,remarks,fee,status) VALUES (#{company},#{applicant},#{topic},#{time},#{date},#{content},#{scale},#{remarks},'0.00','待处理')")
    void addCourse_application(course_application course_application);

    @Select("SELECT * FROM applicationDB WHERE applicationID= #{applicationID}")
    course_application findCourseApplicationByID(int applicationID);

    @Update("UPDATE applicationDB SET status = '已批准' where applicationID=#{id}")
    void approveApplication(BigInteger id);
    @Update("UPDATE applicationDB SET status = '已拒绝' where applicationID=#{id}")
    void rejectApplication(BigInteger id);

    @Update("update course_evaluation_list SET evaluation_status='已评价',tutor_evaluation=#{tutor_evaluation},course_evaluation=#{course_evaluation},suggestion=#{suggestion} where course_name=#{course_name} and student_name=#{student_name}")
    void evaluate(course_evaluation evaluation);

    @Insert("INSERT INTO course_evaluation_list(course_name,student_name,evaluation_status,pay_status) VALUES (#{course_name},#{student_name},'未评价','未缴费')")
    void addToRelation(Re0 re0);


    @Select("select * from course_enrolling_list")
    List<Enrolling> getEnrolling();
    @Delete("delete  from course_enrolling_list where course_name=#{course_name} and student_name=#{student_name}")
    void deleteEnroll(Re0 re0);//改的是报名数据库

    @Update("update course_enrolling_list set status='已批准' where course_name=#{course_name} and student_name=#{student_name}")
    void approveEnroll(Re0 re0);
    @Update("update course_enrolling_list set status='已拒绝' where course_name=#{course_name} and student_name=#{student_name}")
    void rejectEnroll(Re0 re0);

    @Update("update studentdb set sex=#{gender},company=#{company},age=#{age},phone=#{phone},email=#{email},duty=#{position}," +
            "level=#{level} where name=#{name}")
    void editStudent(Person person);

    @Insert("INSERT INTO course_enrolling_list(course_name,student_name,enrolling_message,status,date,sex,company,duty,level,email,phone,age) VALUES (#{courseName},#{name},#{reason},'待处理',CURRENT_DATE,#{gender},#{company},#{position},#{level},#{email},#{phone},#{age})")
    void addEnrolling(FirstEnrolling course_enrolling);

    @Update("UPDATE course_evaluation_list SET pay_status = '已缴费'  WHERE student_name=#{student_name} AND course_name=#{course_name} ")
    void Pay(Re0 re0);


//    //结束00000000000000000000
//    // 0000000000000000000000000000000000000000000
//    //查询特定执行人要处理的签到信息
//    @Select("SELECT c.name AS course_name, c.staff, s.* " +
//            "FROM coursedb c " +
//            "JOIN course_evaluation_list e ON c.name = e.course_name " +
//            "JOIN studentdb s ON e.student_name = s.name " +
//            "WHERE c.staff = #{attendanceStaff} LIMIT #{offset}, #{limit}")
//    @Results({
//            @Result(column = "staff",property = "attendanceStaff"),
//            @Result(column = "company", property = "attendanceStudentCompany"),
//            @Result(column = "name", property = "attendanceStudent"),
//            @Result(column = "duty", property = "attendanceStudentJob"),
//            @Result(column = "sex", property = "attendanceStudentSex"),
//            @Result(column = "email", property = "attendanceStudentEmail"),
//            @Result(column = "course_name",property = "attendanceCourseName")
//    })
//    List<attendanceRecording> getRecording(@Param("attendanceStaff") String name,@Param("offset")int offset,@Param("limit")int limit);

//    @Select("SELECT c.name AS course_name, c.staff, s.* " +
//            "FROM coursedb c " +
//            "JOIN course_evaluation_list e ON c.name = e.course_name " +
//            "JOIN studentdb s ON e.student_name = s.name " +
//            "WHERE c.staff = #{attendanceStaff} AND c.name = #{attendanceCourseName}LIMIT #{offset}, #{limit}")
//    @Results({
//            @Result(column = "staff",property = "attendanceStaff"),
//            @Result(column = "company", property = "attendanceStudentCompany"),
//            @Result(column = "name", property = "attendanceStudent"),
//            @Result(column = "duty", property = "attendanceStudentJob"),
//            @Result(column = "sex", property = "attendanceStudentSex"),
//            @Result(column = "email", property = "attendanceStudentEmail"),
//            @Result(column = "course_name",property = "attendanceCourseName")
//    })
@Select("SELECT * FROM attendance_recording_list where attendanceStaff=#{attendanceStaff} AND attendanceCourseName=#{attendanceCourseName}LIMIT #{offset},#{limit} ")
    List<attendanceRecording> getSomeRecording(@Param("attendanceStaff")String name,@Param("offset")int offset,@Param("limit")int limit,@Param("attendanceCourseName")String course_name);

    //自动写入数据库中
    @Select("SELECT c.name AS course_name, c.staff, s.* " +
            "FROM coursedb c " +
            "JOIN course_evaluation_list e ON c.name = e.course_name " +
            "JOIN studentdb s ON e.student_name = s.name " +
            "WHERE c.staff = #{attendanceStaff}")
    @Results({
            @Result(column = "staff",property = "attendanceStaff"),
            @Result(column = "company", property = "attendanceStudentCompany"),
            @Result(column = "name", property = "attendanceStudent"),
            @Result(column = "duty", property = "attendanceStudentJob"),
            @Result(column = "sex", property = "attendanceStudentSex"),
            @Result(column = "email", property = "attendanceStudentEmail"),
            @Result(column = "course_name",property = "attendanceCourseName")
    })
    List<attendanceRecording> saveRecording(@Param("attendanceStaff") String name);
    //更改处理签到信息
    @Update("UPDATE attendance_recording_list SET attendanceStatus='已签到' WHERE attendanceStudent=#{attendanceStudent} AND attendanceCourseName=#{attendanceCourseName}")
    void setAttendingStatus(@Param("attendanceStudent") String attendanceStudent,@Param("attendanceCourseName")String course_name);

    @Insert("INSERT INTO attendance_recording_list(attendanceStudent, attendanceStaff, attendanceStudentSex, attendanceStudentCompany, attendanceStudentJob, attendanceStudentEmail, attendanceStatus, attendanceCourseName) VALUES (#{attendanceStudent}, #{attendanceStaff}, #{attendanceStudentSex}, #{attendanceStudentCompany}, #{attendanceStudentJob}, #{attendanceStudentEmail}, '未签到', #{attendanceCourseName})")
    void InsertToRecording(attendanceRecording record);

    @Select("SELECT * FROM attendance_recording_list where attendanceStaff=#{attendanceStaff}")
    List<attendanceRecording> getAllRecording(@Param("attendanceStaff") String name);

    @Select("SELECT * FROM attendance_recording_list where attendanceStaff=#{attendanceStaff}LIMIT #{offset},#{limit} ")
    List<attendanceRecording> getRecordingTwo(@Param("attendanceStaff") String name,@Param("offset")int offset,@Param("limit")int limit);
}
