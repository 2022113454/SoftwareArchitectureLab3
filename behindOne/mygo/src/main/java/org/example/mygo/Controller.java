package org.example.mygo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    loginService ls;


    @PostMapping("/permission/login")
    Result login(String name){
        System.out.println("start to find name");
        User us=ls.findName(name);
        if(us!=null){
            return Result.success(us);
        }else {
            return Result.error();
        }
    }
    @PostMapping("/permission/register")
    Result register(String username,String password){
        ls.register(username,password);
        return Result.success();
    }
    @PostMapping("/course/add")
    Result addCourse(@RequestBody OfJson ojs){

         String name=ojs.getName();
         String company=ojs.getCompany();
         String executor= ojs.getExecutor();
         Integer cost=ojs.getCost();
         String plan= ojs.getPlan();
         String instructorname=ojs.getInstructorName();
         String instructortitle= ojs.getInstructorTitle();
         String instructorfield=ojs.getInstructorField();
         String instructoremail=ojs.getInstructorEmail();
         String instructorphone= ojs.getInstructorPhone();


         String trainingcontent= ojs.getTrainingContent();
         String trainingstarttime= ojs.getTrainingStartTime();
         String trainingendtime= ojs.getTrainingEndTime();
         String traininglocation= ojs.getTrainingLocation();

        ls.addCourse(name,company,executor,cost,plan, instructorname,instructortitle,instructorfield,instructoremail,instructorphone
                ,trainingcontent,trainingstarttime,trainingendtime,traininglocation);
        return Result.success();
    }



    @GetMapping("/application/getApplicationbyIDmod")
    public List<course_application> getAPPlicationBYID(@RequestParam(value="ID")long ID){
        return ls.getAPPlicationBYID(ID);
    }

    @GetMapping("/application/getApplicationbyIDDIrect")
    public List<course_application> getAPPlicationBYIDDIrect(){
        return ls.getApplicationDirect();
    }

    @GetMapping("/course/getCourse")
    public Page getCourse(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit
    ) {
        // 调用服务层的方法来获取课程数据
        return ls.getCourse(name,page,limit);
    }

    @GetMapping("/course/getCourseofExe")
    public Page getCourseDuty(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "role",defaultValue = "none") String exedutorName
    ) {
        // 调用服务层的方法来获取课程数据
        return ls.getCourseDuty(name,page,limit,exedutorName);
    }

    @PostMapping("/permission/getMenu")
    public Result getMennu(@RequestBody User us){
        System.out.println(us.getUsername()+us.getPassword()+us.getRole());
        return ls.getMennu(us.getUsername(), us.getPassword(),us.getRole());
    }
    @PostMapping("/course/edit")
    public void editCourse(@RequestBody OfJson ojs){
        String name=ojs.getName();
        System.out.println("editCourse:\n"+ojs);
        String company=ojs.getCompany();
        String executor= ojs.getExecutor();
        Integer cost=ojs.getCost();
        String plan= ojs.getPlan();
        String instructorname=ojs.getInstructorName();
        String instructortitle= ojs.getInstructorTitle();
        String instructorfield=ojs.getInstructorField();
        String instructoremail=ojs.getInstructorEmail();
        String instructorphone= ojs.getInstructorPhone();


        String trainingcontent= ojs.getTrainingContent();
        String trainingstarttime= ojs.getTrainingStartTime();
        String trainingendtime= ojs.getTrainingEndTime();
        String traininglocation= ojs.getTrainingLocation();
        ls.updateCourse(name,company,executor,cost,plan, instructorname,instructortitle,instructorfield,instructoremail,instructorphone
                ,trainingcontent,trainingstarttime,trainingendtime,traininglocation);
    }
    @PostMapping("/course/start")
    Result startCourse(@RequestBody String ss){
        System.out.println(ss);
        //字符串的时候要正则
        Pattern pattern = Pattern.compile("\"id\":(\\d+)");
        Matcher matcher = pattern.matcher(ss);

        String id_s=null;
        if (matcher.find()) {
            id_s = matcher.group(1); // 捕获组1就是括号中的内容
            System.out.println(id_s); // 输出: 102
        }
        return ls.startCourse(new BigInteger(id_s));
    }
    @PostMapping("/course/end")
    Result endCourse(@RequestBody String ss){
        System.out.println(ss);
        //字符串的时候要正则
        Pattern pattern = Pattern.compile("\"id\":(\\d+)");
        Matcher matcher = pattern.matcher(ss);

        String id_s=null;
        if (matcher.find()) {
            id_s = matcher.group(1); // 捕获组1就是括号中的内容
            System.out.println(id_s); // 输出: 102
        }
        return ls.endCourse(new BigInteger(id_s));
    }


    @PostMapping("/teacher/edit")
    public void editTeacher(@RequestBody Teacher teacher){
        //System.out.println(teacher);
        ls.updateTeacher(teacher);
    }
    @PostMapping("/user/edit")
    public void editStudent(@RequestBody Student student){
        System.out.println(student);
        //信息传递
        String name=student.getName();//姓名
        String sex=student.getSex();//性别
        String company=student.getCompany();//所属公司，可填“无”
        String duty=student.getDuty();//工作岗位
        String level=student.getLevel();//技术水平
        String email=student.getEmail();//邮箱
        ls.updateStudent(name,sex,company,duty,level,email);
    }
    @PostMapping("/executor/edit")
    public void editExecutor(@RequestBody Executor executor){
        //System.out.println(teacher);
        //信息传递
        BigInteger id=executor.getId();
        String name= executor.getName();
        String title= executor.getTitle();
        String experience=executor.getExperience();
        String email= executor.getEmail();
        String status= executor.getStatus();
        ls.updateExecutor(id,name,title,experience,email,status);
    }
    /*@PostMapping("/student/edit")
    public void editStudent(@RequestBody Student student){

        //信息传递
        String name=student.getName();//姓名
        String sex=student.getSex();//性别
        String company=student.getCompany();//所属公司，可填“无”
        String duty;//工作岗位
        String level;//技术水平
        String email;//邮箱
        ls.updateExecutor(id,name,title,experience,email,status);
    }*/

    @PostMapping("/course/del")
    void removeCourse(@RequestBody String id){
        System.out.println(id);
        //字符串的时候要正则
        Pattern pattern = Pattern.compile("\"id\":(\\d+)");
        Matcher matcher = pattern.matcher(id);

        String id_s=null;
        if (matcher.find()) {
            id_s = matcher.group(1); // 捕获组1就是括号中的内容
            System.out.println(id_s); // 输出: 102
        }
        ls.removeCourse(new BigInteger(id_s));
    }
    @PostMapping("/executor/del")
    void removeExecutor(@RequestBody String id){
        System.out.println(id);
        //字符串的时候要正则
        Pattern pattern = Pattern.compile("\"id\":(\\d+)");
        Matcher matcher = pattern.matcher(id);

        String id_s=null;
        if (matcher.find()) {
            id_s = matcher.group(1); // 捕获组1就是括号中的内容
            System.out.println(id_s); // 输出: 102
        }
        ls.removeExecutor(new BigInteger(id_s));
    }

    @PostMapping("/teacher/del")
    void removeTeacher(@RequestBody String ss){
        System.out.println(ss+"\nenddddd\nremoveteacher");
        //字符串的时候要正则
        Pattern pattern = Pattern.compile("\"name\":\"(.+)\"");
        Matcher matcher = pattern.matcher(ss);

        String name=null;
        if (matcher.find()) {
            name = matcher.group(1); // 捕获组1就是括号中的内容
            //System.out.println(name); // 输出:
        }
        ls.removeTeacher(name);
    }
    @PostMapping("/student/del")
    void removeStudent(@RequestBody Re0 re0){

        String courseName=re0.getCourse_name();
        String studentName=re0.getStudent_name();
        System.out.println(courseName+studentName);
        ls.removeStudent(courseName,studentName);
    }

    @PostMapping("/teacher/add")
    Result addTeacher(@RequestBody Teacher teacher){
        System.out.println("\n\niafjaiofjhaf");
        System.out.println(teacher);
        ls.addTeacher(teacher);
        return Result.success();
    }

    @PostMapping("/notice/add")
    Result addNotice(@RequestBody Notice notice){
        System.out.println("\n\naddNotice");
        System.out.println(notice);
        //信息传递
        String courseName= notice.getCourseName();//课程名
        String courseContent= notice.getCourseContent();//课程内容
        String teacherName= notice.getTeacherName();//讲师姓名
        int amount= notice.getAmount();//计划招生数量
        int cost= notice.getCost();//课程费用
        String date= notice.getDate();//培训日期
        String startTime= notice.getStartTime();//培训开始时间
        String location=notice.getLocation();//培训地点
        String deadLine= notice.getDeadLine();//报名截止日期
        String note= notice.getNote();//备注信息
        ls.addNotice(courseName,courseContent,teacherName,amount,cost,date,
                startTime,location,deadLine,note);
        return Result.success();
    }

    @PostMapping("/user/add")
    Result addStudent(@RequestBody Student student){
        System.out.println("\n\naddStudent");
        System.out.println(student);
        //信息传递
        String name=student.getName();//姓名
        String sex=student.getSex();//性别
        String company=student.getCompany();//所属公司，可填“无”
        String duty=student.getDuty();//工作岗位
        String level=student.getLevel();//技术水平
        String email=student.getEmail();//邮箱
        ls.addStudent(name,sex,company,duty,level,email);
        return Result.success();
    }

    @PostMapping("/executor/add")
    Result addExecutor(@RequestBody Executor executor){
        System.out.println("ExecutorAdd\n\n");
        System.out.println(executor);
        //信息传递
        BigInteger id=executor.getId();
        String name= executor.getName();
        String title=executor.getTitle();
        String experience= executor.getExperience();
        String email=executor.getEmail();
        String status=executor.getStatus();
        ls.addExecutor(id,name,title,experience,email,status);
        return Result.success();
    }

    @GetMapping("/teacher/getTeacher")
    public PageTeacher getTeacher(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit
    ){
        // 调用服务层的方法来获取课程数据
        return ls.getTeacher(name,page,limit);
    }
    @GetMapping("/notice/getNotice")
    public List<Notice> getNotice(){
        // 调用服务层的方法来获取课程数据
        return ls.getNotice();
    }
    @GetMapping("/user/getUser")
    public PageStudent getStudent(
            @RequestParam(value = "name", required = false) String message,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "role", required = false) String managerName,
            @RequestParam(value = "course", required = false) String courseName
            ){
        // 调用服务层的方法来获取学员信息
        return ls.getStudent(message,page,limit,managerName,courseName);
    }
    @PostMapping("/student/join")
    public Result join(){
        return Result.success();
    }
    /**
     * 学员报名操作，即向数据库中插入一条
     */
    @PostMapping("/course/enroll")
    public String EnrollingInsert(@RequestBody FirstEnrolling courseEnrolling) {
        System.out.println("FirstEnrolling:\n"+courseEnrolling);
        ls.InsertCourseEnrolling(courseEnrolling);
        return "Success to insert courseEnrolling";
    }

    @GetMapping("/executor/getExecutor")
    public PageExecutor getExecutor(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit
    ){
        // 调用服务层的方法来获取课程数据
        return ls.getExecutor(name,page,limit);
    }

    //开始0000000000000000000000000000000000000000000000000

    //获取全部课程申请（经理审批）
    @GetMapping("/application/selectAll")
    public PageAllApplication selectAll(@RequestParam(defaultValue = "1") int pageNum,
                                        @RequestParam(defaultValue = "10") int pageSize) {
        System.out.println("/application/selectAll");
        System.out.println("获取课程申请：\n\t\t"+ls.getSelectAll(pageNum,pageSize));
        return ls.getSelectAll(pageNum,pageSize);
    }

    //获取特定状态的课程申请（经理审批）
    @GetMapping("/application/selectStatus")
    public List<course_application> selectStatus(@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "10")int pageSize){
        return ls.getSelectPart(pageNum,pageSize);
    }

    //更改课程申请状态（经理审批）
    @PostMapping("/application/approve")
    public String approveApplication(@RequestBody String ss) {
        System.out.println(ss);
        //字符串的时候要正则
        Pattern pattern = Pattern.compile("\"id\":(\\d+)");
        Matcher matcher = pattern.matcher(ss);

        String id_s=null;
        if (matcher.find()) {
            id_s = matcher.group(1); // 捕获组1就是括号中的内容
            System.out.println(id_s); // 输出: 102
        }
        ls.approveApplication(new BigInteger(id_s));
        return "success";
    }
    @PostMapping("/application/reject")
    public String rejectApplication(@RequestBody String ss) {
        System.out.println(ss);
        //字符串的时候要正则
        Pattern pattern = Pattern.compile("\"id\":(\\d+)");
        Matcher matcher = pattern.matcher(ss);

        String id_s=null;
        if (matcher.find()) {
            id_s = matcher.group(1); // 捕获组1就是括号中的内容
            System.out.println(id_s); // 输出: 102
        }
        ls.rejectApplication(new BigInteger(id_s));
        return "success";
    }

    //软件公司提交课程申请
    @PostMapping("/application/submission")
    public String applicationSubmission(@RequestBody course_application courseApplication) {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String noticedate=formatter.format(calendar.getTime());
        noticedate=noticedate.substring(0,10);
        System.out.println("/application/submission");
        System.out.println(courseApplication);
        courseApplication.setTime(noticedate);
        String ss=courseApplication.getDate();
        ss=ss.substring(0,10);
        courseApplication.setDate(ss);
        ls.saveCourseApplication(courseApplication);


        return "success to submit courseApplication";
    }




    //学员提交课程评价
    @PostMapping("/evaluation/submission")
    public String evaluationSubmission(@RequestBody course_evaluation evaluation) {
        ls.saveCourseEvaluation(evaluation);
        System.out.println("\n\n/evaluation/submission\n\n");
        System.out.println(evaluation);
        return "success to submit evaluation";
    }

    @PostMapping("/approve")
    public String approveJoin(@RequestBody Re0 re0) {
        System.out.println("/approveJoin\n\n");
        System.out.println(re0);
        ls.approveJoin(re0);
        return "success to submit evaluation";
    }
    @PostMapping("/reject")
    public String rejectJoin(@RequestBody Re0 re0) {
        System.out.println("/rejectJoin\n\n");
        System.out.println(re0);
        ls.rejectJoin(re0);
        return "success to submit evaluation";
    }
    @GetMapping("/getEnroll")
    public PageEnrolling getEnrolling(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "executor", required = false) String executor
    ) {
        return ls.getenrolling(page,limit,executor);
    }

    @GetMapping("/getSelectCourse")
    public PageSelectCourse getSelectCourse(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "student", required = false) String student
    ) {
        System.out.println("传入参数："+name+page+limit+student);
        return ls.getSelectCourse(page,limit,student);
    }

    @PostMapping("/course/getPersonalCenter")
    public Person getPerson(@RequestBody String ss){
        Pattern pattern = Pattern.compile("\"name\":\"(.+)\"");
        Matcher matcher = pattern.matcher(ss);

        String name=null;
        if (matcher.find()) {
            name = matcher.group(1); // 捕获组1就是括号中的内容
            //System.out.println(name); // 输出:
        }
        System.out.println("获取个人中心\n"+name);
        System.out.println(ls.getPerson(name));
        return  ls.getPerson(name);
    }
    @PostMapping("/course/editPersonnalCenter")
    public Result editPerson(@RequestBody Person person){
        System.out.println("修改个人信息\n"+person);
        ls.editPerson(person);
        return Result.success();
    }
    @PostMapping("/payment")
    public String payment(@RequestBody Re0 re0) {
        ls.saveCoursePayment(re0);
        return "success to payment";
    }

    /**
     * 此函数用于返回特定执行人对应的签到列表（数据库自动添加）
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/GetAllecording")
    public PageAttendance GetRecording(@RequestParam(value="name",required = false) String name,
                                                  @RequestParam(value="page",defaultValue="1") int pageNum,
                                                  @RequestParam(value="limit",defaultValue="10")int pageSize,
                                                  @RequestParam(value = "role", required = false) String staff,
                                                  @RequestParam(value = "course", required = false) String courseName
    ) {
        List<attendanceRecording> lst=new ArrayList<>();
        System.out.println(name+"\t"+pageNum+"\t"+pageSize+"\t"+staff+"\t"+courseName);
        if(courseName==null||courseName.isEmpty()){
            System.out.println("fjaopfjapfjap");
            lst=ls.getRecording(pageNum,pageSize,staff);
        }
        else lst=ls.getSomeRecording(pageNum,pageSize,staff,courseName);
        System.out.println(lst);
        Set<String> course=new HashSet<>();
        for(attendanceRecording a:lst){
            course.add(a.getAttendanceCourseName());
        }
        List<Course> lst2=new ArrayList<>();
        for(String s:course){
            Course c=new Course();
            c.setName(s);
            lst2.add(c);
        }
        return new PageAttendance(20000,lst.size(),lst,lst2);
    }
    /**
     * 签到
     * @return
     */

    @PostMapping("/signUp")
    public String SetStatus(@RequestBody Re0 re0) {
        System.out.println("re0\n\n:"+re0);
        ls.setAttendanceStatus(re0.getStudent_name(),re0.getCourse_name());
        return "Success to set status!";
    }
    @PostMapping("/summary")
    public List<Summary> summary(@RequestBody String ss){
        Pattern pattern = Pattern.compile("\"role\":\"(.+)\"");
        Matcher matcher = pattern.matcher(ss);

        String name=null;
        if (matcher.find()) {
            name = matcher.group(1); // 捕获组1就是括号中的内容
            System.out.println(name); // 输出:
        }
        System.out.println(ls.Summary(name));
        return ls.Summary(name);
    }
    @PostMapping("/report")
    public Report report(){
        return ls.report();
    }



    /*@GetMapping("/getAllEvaluation")
    public  getAllEvaluation(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "student", required = false) String student
    ) {
        return ls.getSelectCourse(page,limit,student);
    }*/



    //结束00000000000000000000000000000000000000000000000000000000
}
