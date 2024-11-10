import Mock from "mockjs";
import homeApi from './mockServeData/home'

// 定义mock请求拦截
Mock.mock('http://localhost:8081/api/home/getData', homeApi.getStatisticalData)

// 用户列表的数据
//Mock.mock('/api/user/add', 'post',user.createUser)
// Mock.mock('/api/course/add', 'post',course.createCourse)
//Mock.mock('/api/teacher/add', 'post',teacher.createTeacher)
//Mock.mock('/api/executor/add', 'post',executor.createExecutor)


//Mock.mock('/api/user/edit','post',user.updateUser)
// Mock.mock('/api/course/edit','post',course.updateCourse)
//Mock.mock('/api/teacher/edit','post',teacher.updateTeacher)
//Mock.mock('/api/executor/edit','post',executor.updateExecutor)

//Mock.mock('/api/user/del','post',user.deleteUser)
// Mock.mock('/api/course/del','post',course.deleteCourse)
//Mock.mock('/api/teacher/del','post',teacher.deleteTeacher)
//Mock.mock('/api/executor/del','post',executor.deleteExecutor)

//Mock.mock(/api\/user\/getUser/,user.getUserList)
// Mock.mock(/api\/course\/getCourse/,course.getCourseList)
//Mock.mock(/api\/teacher\/getTeacher/,teacher.getTeacherList)
//Mock.mock(/api\/executor\/getExecutor/,executor.getExecutorList)

// Mock.mock(/api\/permission\/getMenu/,'post',permission.getMenu)