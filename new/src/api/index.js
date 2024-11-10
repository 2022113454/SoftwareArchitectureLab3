import http from "@/utils/request";

//请求首页数据
export const getData = () => {
    // 返回一个promise对象
    return http.get("/home/getData");
};

//执行人获取学生数据
export const getUser = (params) => {
    return http.get("/user/getUser", params);
};
//经理获取课程
export const getCourse = (params) => {
    return http.get("/course/getCourse", params);
};

export const getTeacher = (params) => {
    return http.get("/teacher/getTeacher", params);
};
export const getExecutor = (params) => {
    return http.get("/executor/getExecutor", params);
};
export const getNotices = () => {
    return http.get("/notice/getNotice");
};

//添加
export const addUser = (data) => {
    return http.post("/user/add", data);
};
//经理添加课程
export const addCourse = (data) => {
    return http.post("/course/add", data);
};
export const addTeacher = (data) => {
    return http.post("/teacher/add", data);
};
export const addExecutor = (data) => {
    return http.post("/executor/add", data);
};

//编辑
export const editUser = (data) => {
    return http.post("/user/edit", data);
};
//经理编辑课程
export const editCourse = (data) => {
    return http.post("/course/edit", data);
};
export const editTeacher = (data) => {
    return http.post("/teacher/edit", data);
};
export const editExecutor = (data) => {
    return http.post("/executor/edit", data);
};


//删除
export const delUser = (data) => {
    return http.post("/user/del", data);
};
//经理删除课程
export const delCourse = (data) => {
    return http.post("/course/del", data);
};
export const delTeacher = (data) => {
    return http.post("/teacher/del", data);
};
export const delExecutor = (data) => {
    return http.post("/executor/del", data);
};
//登录
export const getMenu = (data) => {
    return http.post("/permission/getMenu", data);
};

//执行人添加课程公告
export const createNotice = (data) => {
    return http.post("/notice/add", data);
};
//开始课程
export const startCourse = (data) => {
    return http.post("/course/start", data);
};
//结束课程
export const endCourse = (data) => {
    return http.post("/course/end", data);
};
//执行人获取课程
export const getCourseOfExe = (params) => {
    return http.get("/course/getCourseofExe", params);
};
//学生获取已选课程
export const getSelectedCourses = (params) => {
    return http.get("/course/getSelectedCourses", params);
};
//学生获取未选课程
export const getUnselectedCourses = (params) => {
    return http.get("/course/getUnselectedCourses", params);
};
//获取个人中心信息
export const getPersonalCenter = (params) => {
    return http.post("/course/getPersonalCenter", params);
};
//编辑个人中心信息
export const editPersonnalCenter = (params) => {
    return http.post("/course/editPersonnalCenter", params);
};
//学生报名
export const enroll = (params) => {
    return http.post("/course/enroll", params);
};
//学生退课
export const dropCourse = (params) => {
    return http.post("/course/dropCourse", params);
};
//学生评价课程
export const evaluate = (params) => {
    return http.post("/evaluation/submission", params);
};
//学生缴费课程
export const pay = (params) => {
    return http.post("/payment", params);
};
//执行获取申请信息
export const getEnroll = (params) => {
    return http.get("/getEnroll", params);
};
//执行人接受申请
export const approve = (params) => {
    return http.post("/approve", params);
};
// 执行人拒绝申请
export const reject = (params) => {
    return http.post("/reject", params);
};
// 软件公司提交培训申请
export const submitApplication = (params) => {
    return http.post("/application/submission", params);
};
// 学生获取课程
export const getCourses = (params) => {
    return http.get("/getSelectCourse", params);
};
// 经理获取培训申请
export const getApplication = (params) => {
    return http.get("/application/selectAll", params);
};
// 经理批准培训申请
export const approveApp = (params) => {
    return http.post("/application/approve", params);
};
// 经理拒绝培训申请
export const rejectApp = (params) => {
    return http.post("/application/reject", params);
};
// 工作人员获取课程
export const getCourseOfStaff = (params) => {
    return http.get("/GetAllecording", params);
};
// 工作人员获取课程评价
export const getEvaSummary = (params) => {
    return http.post("/summary", params);
};
// 工作人员获取课程
export const getNum = (params) => {
    return http.post("/report", params);
};
// 签到
export const signUp = (params) => {
    return http.post("/signUp", params);
};