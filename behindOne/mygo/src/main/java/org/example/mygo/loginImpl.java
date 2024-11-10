package org.example.mygo;

import org.example.mygo.mycat.MycatMapper;
import org.example.mygo.mysql.MysqlMapper;
import org.example.mygo.news.NewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class loginImpl implements loginService{

    @Autowired
    @Qualifier("newMapper")
    private NewMapper newMapper;

    @Autowired
    @Qualifier("mysqlMapper")
    private MysqlMapper mp;

    @Autowired
    @Qualifier("mycatMapper")
    private MycatMapper mycatMapper;



    //Redis 缓存部分
//    @Autowired
//    private RedisTemplate<String, List<User>> redisTemplate; // RedisTemplate注入
//    private static final String USER_CACHE_KEY = "user_list";


    @Override
    public List<course_application> getAPPlicationBYID(long ID) {
        return mp.findCourseApplication_ByID(ID);
    }
    @Override
    public List<course_application> getApplicationDirect(){
        return newMapper.findCourseApplicationByIDDirect();
    }


    @Override
    public User findName(String name) {
        return mp.findName(name);
    }

    @Override
    public void register(String name, String password) {
        mp.addUser(name,password);
    }


    @Override
    public void addCourse(String name,String company,String executor,int cost,String plan, String instructorname,
                          String instructortitle,String instructorfield,String instructoremail,String instructorphone
            ,String trainingcontent,String trainingstarttime,String trainingendtime,String traininglocation) {
         mp.addCourse(name,company,executor,cost,plan, instructorname,instructortitle,instructorfield,instructoremail,instructorphone
        ,trainingcontent,trainingstarttime,trainingendtime,traininglocation
         ,"未开始");
    }

    @Override
    public void addStudent(String name, String sex, String company, String duty, String level, String email) {
        mp.addStudent(name,sex,company,duty,level,email);
    }

    @Override
    public Page getCourse(String message,int page,int limit) {
        //获取数据库信息(mp的getCourse参数是没用的)
        List<Course> lst=mp.getCourse(null);
        List<Course> lst3=new ArrayList<>();
        System.out.println(lst.toString());
        List<Course> lst2=new ArrayList<>();
        if(message==null|| message.isEmpty()){
            lst2=lst;
        }
        else {
            for(Course c:lst) if(c.getStatus().equals(message)||c.getName().equals(message)) lst2.add(c);
        }
        //根据message分支
        System.out.println(lst2.toString()+"dayin");
        for(int ii=(page-1)*limit;ii<page*limit;ii++){
            if(ii>=lst2.size()) break;
            lst3.add(lst2.get(ii));
        }
        return new Page(20000,lst2.size(),lst3);
    }

    @Override
    public Page getCourseDuty(String message, int page, int limit, String executorName) {
        //获取数据库信息(mp的getCourse参数是没用的)
        List<Course> lst0=mp.getCourse(null);
        List<Course> lst=new ArrayList<>();
        for(Course c:lst0){
            if(c.getExecutor().equals(executorName)) lst.add(c);
        }
        List<Course> lst3=new ArrayList<>();
        System.out.println(lst.toString());
        List<Course> lst2=new ArrayList<>();
        if(message==null|| message.isEmpty()){
            lst2=lst;
        }
        else {
            for(Course c:lst) if(c.getStatus().equals(message)||c.getName().equals(message)) lst2.add(c);
        }
        //根据message分支
        System.out.println(lst2.toString()+"dayin");
        for(int ii=(page-1)*limit;ii<page*limit;ii++){
            if(ii>=lst2.size()) break;
            lst3.add(lst2.get(ii));
        }
        return new Page(20000,lst2.size(),lst3);
    }


    @Override
    public PageTeacher getTeacher(String message, int page, int limit) {

        //获取数据库信息(mp的getCourse参数是没用的)
        List<Teacher> lst=mp.getTeacher(null);
        List<Teacher> lst3=new ArrayList<>();
        List<Teacher> lst2=new ArrayList<>();
        if(message==null|| message.isEmpty()){
            lst2=lst;
        }
        else {
            for(Teacher c:lst) if(c.getStatus().equals(message)||c.getName().equals(message)) lst2.add(c);
        }
        //根据message分支
        System.out.println("getTeacher\n"+lst2.toString());
        for(int ii=(page-1)*limit;ii<page*limit;ii++){
            if(ii>=lst2.size()) break;
            lst3.add(lst2.get(ii));
        }
        return new PageTeacher(20000,lst2.size(),lst3);
    }

    @Override
    public PageExecutor getExecutor(String message, int page, int limit) {
        //获取数据库信息(mp的getCourse参数是没用的)
        List<Executor> lst=mp.getExecutor(null);
        List<Executor> lst3=new ArrayList<>();
        List<Executor> lst2=new ArrayList<>();
        if(message==null|| message.isEmpty()){
            lst2=lst;
        }
        else {
            for(Executor c:lst) if(c.getStatus().equals(message)||c.getName().equals(message)) lst2.add(c);
        }
        //根据message分支
        System.out.println("getExecutor\n"+lst2.toString());
        for(int ii=(page-1)*limit;ii<page*limit;ii++){
            if(ii>=lst2.size()) break;
            lst3.add(lst2.get(ii));
        }
        return new PageExecutor(20000,lst2.size(),lst3);
    }


    @Override
    public Result getMennu(String username, String password, String role) {
        List<User> lst=mp.getUser();
        System.out.println(username+password+role);
        for(User us:lst){
            System.out.println(us.getUsername()+us.getPassword()+us.getRole());
            if(us.getUsername().equals(username)&&us.getPassword().equals(password)&&
            us.getRole().equals(role)) {
                System.out.println(true);
                return new Result<>(20000, "登录成功", "token=1",username);
            }
        }
        System.out.println(false);
        return new Result<>(-1,"密码错误","token=2",username);
    }



    @Override
    public void updateCourse(String name,String company,String executor,int cost,String plan, String instructorname,
                             String instructortitle,String instructorfield,String instructoremail,String instructorphone
            ,String trainingcontent,String trainingstarttime,String trainingendtime,String traininglocation) {
        System.out.println(trainingstarttime);
        System.out.println(trainingendtime);
        System.out.println(trainingcontent);
        System.out.println(traininglocation);
        mp.updateCourse(name,company,executor,cost,plan, instructorname,instructortitle,instructorfield,instructoremail,instructorphone
                ,trainingcontent,trainingstarttime,trainingendtime,traininglocation);
    }



    @Override
    public void removeCourse(BigInteger id) {
        mp.removeCourse(id);
    }

    @Override
    public void removeExecutor(BigInteger id) {
        mp.removeExecutor(id);
    }

    @Override
    public void removeTeacher(String name) {
        mp.removeTeacher(name);
    }

    @Override
    public void removeStudent(String courseName,String studentName) {
        mp.removeStudent(courseName,studentName);
    }

    @Override
    public PageStudent getStudent(String message, int page, int limit, String managerName, String courseName) {
        List<Relation> lstB=mp.getRelation();
        List<Student> lst=mp.getStudent();
        List<Student> lst2=new ArrayList<>();
        List<Student> lst3=new ArrayList<>();
        Set<String> nameSet=new HashSet<>();

        if(courseName.isEmpty()||courseName==null){//courseName为空
            for(Relation r:lstB){
                nameSet.add(r.getStudent_name());
            }
            for(String name:nameSet){
                for(Student s:lst){
                    if(name.equals(s.getName())){
                        lst2.add(s);
                        break;
                    }
                }
            }
        }
        else {
            //courseName不为空
            for (Relation r:lstB){
                if(r.getCourse_name().equals(courseName)) {
                    for(Student s:lst){
                        if(r.getStudent_name().equals(s.getName())){
                            lst2.add(s);
                            break;
                        }
                    }
                }
            }
        }

        System.out.println("getStudent\n"+lst2.toString());
        for(int ii=(page-1)*limit;ii<page*limit;ii++){
            if(ii>=lst2.size()) break;
            lst3.add(lst2.get(ii));
        }
        return new PageStudent(20000,lst2.size(),lst3);
    }


    @Override
    public void addTeacher(Teacher teacher) {
        mp.addTeacher(teacher);
    }

    @Override
    public void addNotice(String courseName, String courseContent, String teacherName, int amount, int cost, String date, String startTime, String location, String deadLine, String note) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String noticedate=formatter.format(calendar.getTime());
        System.out.println();
        mp.addNotice(courseName,courseContent,teacherName,amount,cost,date,
                startTime,noticedate,location,deadLine,note);
    }

    @Override
    public List<Notice> getNotice() {
        return mp.getNotice();
    }

    @Override
    public Result join(String courseName, String studentName, String reason) {
        mp.join(courseName,studentName,reason);
        return Result.success();
    }

    @Override
    public void addExecutor(BigInteger id, String name, String title,String experience, String email, String status) {
        mp.addExecutor(id,name,title,experience,email,status);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        mp.updateTeacher(teacher);
    }

    @Override
    public void updateExecutor(BigInteger id, String name, String title, String experience, String email, String status) {
        mp.updateExecutor(id,name,title,experience,email,status);
    }

    @Override
    public void updateStudent(String name, String sex, String company, String duty, String level, String email) {
        mp.updateStudent(name,sex,company,duty,level,email);
    }

    @Override
    public Result startCourse(BigInteger id) {
        mp.setCourseStatus(id,"进行中");
        return Result.success();
    }
    @Override
    public Result endCourse(BigInteger id) {
        mp.setCourseStatus(id,"已结束");
        return Result.success();
    }

    //开始0000000000000000000000000000000000000000000000000000000
    @Override
    public PageAllApplication getSelectAll(int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        List<course_application> list = mp.findApplicationAll(offset,pageSize);
        return new PageAllApplication(20000,list.size(),list);
    }

    @Override
    public List<course_application> getSelectPart(int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        List<course_application> list = mp.selectPart(offset,pageSize);
        return list;
    }

    @Override
    public void saveCourseApplication(course_application course_application) {
        mp.addCourse_application(course_application);
        mycatMapper.saveCourseApplicationInMycat(course_application);
    }

    @Override
    public void approveApplication(BigInteger id) {
        mp.approveApplication(id);
    }

    @Override
    public void rejectApplication(BigInteger id) {
        mp.rejectApplication(id);
    }

    @Override
    public Optional<course_application> findByID(int course_application_id) {
        return Optional.ofNullable(mp.findCourseApplicationByID(course_application_id));
    }

    @Override
    public void saveCourseEvaluation(course_evaluation course_evaluation) {
        mp.evaluate(course_evaluation);
    }

    @Override
    public void saveCourseApplicationInMycat(course_application course_application){
         mycatMapper.saveCourseApplicationInMycat(course_application);
    }
    @Override
    public void approveJoin(Re0 re0) {
        mp.addToRelation(re0);
        mp.approveEnroll(re0);
    }

    @Override
    public void rejectJoin(Re0 re0) {
        mp.rejectEnroll(re0);
    }

    @Override
    public PageEnrolling getenrolling(int page,int limit,String executor) {
        System.out.println(executor);
        List<Enrolling> lst=mp.getEnrolling();
        List<Enrolling> lst2=new ArrayList<>();
        List<String> course_name=new ArrayList<>();
        List<Course> lstCourse=mp.getCourse(null);
        for(Course c:lstCourse){
            if(c.getExecutor().equals(executor)) course_name.add(c.getName());
        }
        System.out.println("course_name"+course_name);
        List<Enrolling> lst3=new ArrayList<>();
        for(Enrolling e:lst){
            if(course_name.contains(e.getCourse_name())) lst2.add(e);
        }
        //根据message分支
        for(int ii=(page-1)*limit;ii<page*limit;ii++){
            if(ii>=lst2.size()) break;
            lst3.add(lst2.get(ii));
        }
        return new PageEnrolling(20000,lst2.size(),lst3);
    }

    @Override
    public PageSelectCourse getSelectCourse(int page, int limit, String student) {
        List<Course> lst=mp.getCourse(null);
        List<Relation> lstRelation=mp.getRelation();
        Set<Course> unselect=new HashSet<>();
        Set<Course> selected=new HashSet<>();
        for(Course c:lst){
            for (Relation r:lstRelation){
                if(r.getStudent_name().equals(student)&&r.getCourse_name().equals(c.getName())) {
                    c.setEvaluation_status(r.getEvaluation_status());
                    c.setPay_status(r.getPay_status());
                    selected.add(c);
                    break;
                }
            }
            if(!selected.contains(c)) unselect.add(c);
        }
        System.out.println("unselect:\n"+unselect);
        System.out.println("selected:\n"+selected);

        return new PageSelectCourse(20000,unselect.size(),selected.size(),unselect,selected);
    }

    @Override
    public Person getPerson(String name) {
        List<Student> lst=mp.getStudent();
        for(Student s:lst){
            if(s.getName().equals(name)) return new Person(s.getName(),s.getSex(),s.getAge(),
                    s.getPhone(),s.getEmail(),s.getCompany(),s.getDuty(),s.getLevel());
        }
        return null;
    }

    @Override
    public void editPerson(Person person) {
        mp.editStudent(person);
    }
    @Override
    public void InsertCourseEnrolling(FirstEnrolling courseEnrolling) {
        List<Enrolling> lst=mp.getEnrolling();
        for(Enrolling e:lst){
            if(e.getCourse_name().equals(courseEnrolling.getCourseName())&&e.getStudent_name().equals(courseEnrolling.getName())){
                return;
            }
        }
        mp.addEnrolling(courseEnrolling);
    }

    @Override
    public void saveCoursePayment(Re0 re0) {
        mp.Pay(re0);
    }
    //结束0000000000000000000000000000000000000000000000000000000

    @Override
    public List<attendanceRecording> getRecording(int pageNum,int pageSize,String staff) {
        int offset = (pageNum - 1) * pageSize;
        System.out.println("diaoyongl######ci");
        saveIt(staff);   //存到数据库中
        return mp.getRecordingTwo(staff,offset,pageSize);
    }

    @Override
    public List<attendanceRecording> getSomeRecording(int pageNum,int pageSize,String staff,String course_name) {
        int offset = (pageNum - 1) * pageSize;
        return mp.getSomeRecording(staff,offset,pageSize,course_name);
    }


    private void saveIt(String name){
        int tag = 0;
        List<attendanceRecording> lst1=mp.saveRecording(name);
        System.out.println("mp.saveRecording(name):"+lst1);
        for(attendanceRecording attendanceRecording1:lst1){
            List<attendanceRecording> list1 = mp.getAllRecording(name);
            if(!list1.isEmpty()){
                for(attendanceRecording attendanceRecording2:list1){
                    if(attendanceRecording1.getAttendanceCourseName().equals(attendanceRecording2.getAttendanceCourseName())&&
                            attendanceRecording1.getAttendanceStudent().equals(attendanceRecording2.getAttendanceStudent())){
                        tag=1;
                    }
                }
            }

            if(tag==0){ mp.InsertToRecording(attendanceRecording1);
                System.out.println("看看");
            }
            tag=0;

        }
    }

    @Override
    public void setAttendanceStatus(String studentName,String courseName) {
        mp.setAttendingStatus(studentName,courseName);
    }

    @Override
    public List<Summary> Summary(String staff) {
        System.out.println(staff);
        List<Summary> summaries=new ArrayList<>();
        List<Course> courses=mp.getCourse(null);
        System.out.println("courses:"+courses);
        Set<Course> courses1=new HashSet<>();
        for(Course c:courses){
            if(c.getStaff().equals(staff)||c.getExecutor().equals(staff)) courses1.add(c);
            System.out.println("courses1:"+courses1);
        }
        System.out.println("courses1:"+courses1);
        List<Relation> relations=mp.getRelation();
        for (Course c:courses1){
            List<Double> tutor_evaluations=new ArrayList<>();
            List<Double> course_evaluations=new ArrayList<>();
            for (Relation r:relations){
                if(r.getCourse_name().equals(c.getName())){
                    tutor_evaluations.add(r.getTutor_evaluation());
                    course_evaluations.add(r.getCourse_evaluation());
                }
            }
            double tutor_sum=0;
            double course_sum=0;
            for (int i=0;i<tutor_evaluations.size();i++) tutor_sum+=tutor_evaluations.get(i);
            tutor_sum/=tutor_evaluations.size();
            for (int i=0;i<course_evaluations.size();i++) course_sum+=course_evaluations.get(i);
            course_sum/=course_evaluations.size();
            Summary summary=new Summary();
            summary.setCourseName(c.getName());
            summary.setTeacherName(c.getInstructorName());
            summary.setAveCourseScore(Double.parseDouble(String.format("%.2f",course_sum)));
            summary.setAveTeacherScore(Double.parseDouble(String.format("%.2f",tutor_sum)));
            summaries.add(summary);
        }
        System.out.println(summaries);
        return summaries;
    }

    @Override
    public Report report() {
        List<Relation> relations=mp.getRelation();
        List<Course> courses=mp.getCourse(null);
        List<Double> fee=new ArrayList<>();
        int end=0,doing=0,toStart=0,man=0,woman=0,age1=0,age2=0,age3=0;
        for (Course c:courses){
            int count=0;
            for(Relation r:relations){
                if(r.getCourse_name().equals(c.getName())){
                    count++;
                }
            }
            if(c.getStatus().equals("已结束")) end++;
            else if(c.getStatus().equals("进行中")) doing++;
            else toStart++;
            fee.add(c.getCost()*count*1.0);
        }
        int sum=0;
        for(int i=0;i<fee.size();i++){
            sum+=fee.get(i);
        }
        List<Student> students=mp.getStudent();
        Set<String> company=new HashSet<>();
        for(Student s:students){
            if(s.getSex().equals("男")) man++;
            else woman++;
            if(s.getAge()<20) age1++;
            else if(s.getAge()<=40) age2++;
            else age3++;
            company.add(s.getCompany());
        }
        List<Teacher> teachers=mp.getTeacher(null);
        List<Executor> executors=mp.getExecutor(null);
        double other=10000.0;
        return new Report(other+sum,sum,other,relations.size(),students.size(),
                end,doing,toStart,courses.size(),man,woman,age1,age2,age3,company.size(),
                teachers.size(),executors.size());
    }


}
