import Executor from '@/views/Executor.vue'
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
        id: Mock.Random.guid(), // 老师ID
        name: Mock.Random.cname(), // 老师名称
        title: Mock.Random.pick(['高级工程师', '讲师', '教授', '副教授', '院士']), // 职称
        major: Mock.Random.pick(['前端', '后端', '数据库', '架构设计', '算法']), // 擅长领域
        email: Mock.Random.email(), // 邮箱
        number: Mock.Random.integer(100000, 999999), // 电话
        status: Mock.Random.pick(['空闲', '工作中']), // 工作状态
    })
  )
}

export default {
 
  getTeacherList: config => {
    const { name, page = 1, limit = 20 } = param2Obj(config.url)
    console.log('name:' + name, 'page:' + page, '分页大小limit:' + limit)
    const mockList = List.filter(user => {
      if (name && user.name.indexOf(name) === -1 && user.name.indexOf(name) === -1 && user.title.indexOf(name) === -1 && user.major.indexOf(name) === -1 && user.status.indexOf(name) === -1) return false
      return true
    })
    const pageList = mockList.filter((item, index) => index < limit * page && index >= limit * (page - 1))
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
  createTeacher: config => {
    const { name, title, major, email, number, status} = JSON.parse(config.body);
    console.log(JSON.parse(config.body))
    List.unshift({
      id: Mock.Random.guid(),
      name: name,
      title: title,
      major: major,
      email: email,
      number: number,
      status: status,
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
  deleteTeacher: config => {
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
  updateTeacher: config => {
    const { name, title, major, email, number, status} = JSON.parse(config.body);
    console.log(JSON.parse(config.body))
    List.some(u => {
      if (u.name === name) {
        u.name = name
        u.title = title
        u.major = major
        u.email = email
        u.number = number
        u.status = status
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