import Mock from 'mockjs'

// get请求从config.url获取参数，post从config.body中获取参数
function param2Obj (url) {
  const search = url.split('?')[1]
  if (!search) {
    return {}
  }
  return JSON.parse(
    '{"' +
    decodeURIComponent(search)
      .replace(/"/g, '\\"')
      .replace(/&/g, '","')
      .replace(/=/g, '":"') +
    '"}'
  )
}

let List = []
const count = 200

for (let i = 0; i < count; i++) {
  List.push(
    Mock.mock({
        id: i, // 课程ID
        name: Mock.Random.ctitle(5, 10), // 课程名称
        teacher: Mock.Random.cname(), // 讲师
        executor: Mock.Random.cname(), // 执行人
        status: "未开始", // 课程状态
        statusOfEva: i % 2 === 0 ? "已评价" : "未评价", // 评价状态
        statusOfPay: i % 2 === 0 ? "已缴费" : "未缴费", // 支付状态
        cost: 2000,
        plan: '培训计划内容',
        teacherTitle: '',
        teacherField: '',
        teacherEmail: '',
        teacherPhone: '',
        trainingContent: '内容',
        trainingStartTime: '起始时间',
        trainingEndTime: '结束时间',
        trainingLocation: '位置',
        selected: i % 2 === 0 ? false : true
    })
  )
}

export default {
 
  getCourseList: config => {
    console.log(config, '我想看page')
    const { name, page = 1, limit = 20 } = param2Obj(config.url)
    console.log('name:' + name, 'page:' + page, '分页大小limit:' + limit)
    const mockList = List.filter(user => {
      if (name && user.name.indexOf(name) === -1 && user.teacher.indexOf(name) === -1 && user.executor.indexOf(name) === -1 && user.status.indexOf(name) === -1) return false
      return true
    })
    const pageList = mockList.filter((item, index) => index < limit * page && index >= limit * (page - 1))
    console.log(pageList, 'pageList')
    return {
      code: 20000,
      count: mockList.length,
      list: pageList
    }
  },
  /**
   * 增加用户
   * @param name, addr, age, birth, sex
   * @return {{code: number, data: {message: string}}}
   */
  createCourse: config => {
    const { name, company, executor, cost, plan, instructorName, instructorTitle, instructorField, instructorEmail, instructorPhone } = JSON.parse(config.body);
    console.log(JSON.parse(config.body))
    List.unshift({
      id: Mock.Random.guid(),
      name: name,
      teacher: instructorName,
      executor: executor,
      status: '未开始',
    })
    return {
      code: 20000,
      data: {
        message: '添加成功'
      }
    }
  },
  /**
   * 删除用户
   * @param id
   * @return {*}
   */
  deleteCourse: config => {
    const { id } = JSON.parse(config.body)
    if (!id) {
      return {
        code: -999,
        message: '参数不正确'
      }
    } else {
      List = List.filter(u => u.id !== id)
      return {
        code: 20000,
        message: '删除成功'
      }
    }
  },
  /**
   * 批量删除
   * @param config
   * @return {{code: number, data: {message: string}}}
   */
  batchremove: config => {
    let { ids } = param2Obj(config.url)
    ids = ids.split(',')
    List = List.filter(u => !ids.includes(u.id))
    return {
      code: 20000,
      data: {
        message: '批量删除成功'
      }
    }
  },
  /**
   * 修改用户
   * @param id, name, addr, age, birth, sex
   * @return {{code: number, data: {message: string}}}
   */
  updateCourse: config => {
    const { name, company, executor, cost, plan, instructorName, instructorTitle, instructorField, instructorEmail, instructorPhone } = JSON.parse(config.body);
    console.log(JSON.parse(config.body))
    List.some(u => {
      if (u.name === name) {
        u.name = name
        u.executor = executor
        u.teacher = instructorName
        return true
      }
    })
    return {
      code: 20000,
      data: {
        message: '编辑成功'
      }
    }
  }
}