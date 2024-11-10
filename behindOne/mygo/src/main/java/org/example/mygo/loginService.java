package org.example.mygo;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface loginService {
    List<course_application> getAPPlicationBYID(long ID);
    List<course_application> getApplicationDirect();
    User findName(String name);
    void register(String name,String password);

    void addCourse(String name,String company,String executor,int cost,String plan, String instructorname,
                   String instructortitle,String instructorfield,String instructoremail,String instructorphone
            ,String trainingcontent,String trainingstarttime,String trainingendtime,String traininglocation);
    void addStudent(String name,String sex,String company,String duty,String level,String email);

    Page getCourse(String message,int page,int limit);
    Page getCourseDuty(String message,int page,int limit,String executorName);
    PageTeacher getTeacher(String message,int page,int limit);
    PageExecutor getExecutor(String message,int page,int limit);

    Result getMennu(String username,String password,String role);

    void updateCourse(String name,String company,String executor,int cost,String plan, String instructorname,
                      String instructortitle,String instructorfield,String instructoremail,String instructorphone
            ,String trainingcontent,String trainingstarttime,String trainingendtime,String traininglocation);

    void removeCourse(BigInteger id);
    void removeExecutor(BigInteger id);
    void removeTeacher(String name);
    void removeStudent(String courseName,String studentName);

    PageStudent getStudent(String message,int page,int limit,String managerName,String courseName);
    void addTeacher(Teacher teacher);
    void addNotice(String courseName,String courseContent,String teacherName,
                   int amount,int cost,String date, String startTime,
                   String location,String deadLine,String note);
    List<Notice> getNotice();
    Result join(String courseName,String studentName,String reason);
    void addExecutor(BigInteger id,String name,String title,String experience,String email,String status);
    void updateTeacher(Teacher teacher);
    void updateExecutor(BigInteger id,String name,String title,String experience,String email,String status);
    void updateStudent(String name,String sex,String company,String duty,String level,String email);
    Result startCourse(BigInteger id);
    Result endCourse(BigInteger id);

    //开始000000000000000000000000000000000000000000000000000000000000
    PageAllApplication getSelectAll(int pageNum, int pageSize);
    List<course_application> getSelectPart(int pageNum, int pageSize);
    void saveCourseApplication(course_application course_application);
    //使用mycat再度分库分表保存
    void saveCourseApplicationInMycat(course_application course_application);
    void approveApplication(BigInteger id);
    void rejectApplication(BigInteger id);
    Optional<course_application> findByID(int course_application_id);


    void saveCourseEvaluation(course_evaluation course_evaluation);
    void approveJoin(Re0 re0);
    void rejectJoin(Re0 re0);
    PageEnrolling getenrolling(int page,int limit,String executor);
    PageSelectCourse getSelectCourse(int page,int limit,String student);

    Person getPerson(String name);
    void editPerson(Person person);
    void InsertCourseEnrolling(FirstEnrolling courseEnrolling);
    public void saveCoursePayment(Re0 re0);
    List<attendanceRecording> getRecording(int pageNum,int pageSize,String staff);
    List<attendanceRecording> getSomeRecording(int pageNum,int pageSize,String staff,String course_name);
    void setAttendanceStatus(String studentName,String courseName);
    List<Summary> Summary(String staff);
    Report report();
    //结束000000000000000000000000000000000000000000000000000000000000

}
