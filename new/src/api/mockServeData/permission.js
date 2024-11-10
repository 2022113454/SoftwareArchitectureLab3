import Mock from 'mockjs'
export default {
  getMenu: config => {
    console.log(config,"登录")
    const { username, password, role} = JSON.parse(config.body)
    // 先判断用户是否存在
    // 判断账号和密码是否对应
    if (username === 'm' && password === 'm' && role === 'manager') {
      return {
        code: 20000,
        data: {
          token: Mock.Random.guid(),
          message: '获取成功'
        }
      }
    } else if (username === 'e' && password === 'e' && role === 'executor') {
      return {
        code: 20000,
        data: {
          token: Mock.Random.guid(),
          message: '获取成功'
        }
      }
    } else if (username === 'c' && password === 'c' && role === 'company') {
      return {
        code: 20000,
        data: {
          token: Mock.Random.guid(),
          message: '获取成功'
        }
      }} else if (username === 's' && password === 's' && role === 'student') {
        return {
          code: 20000,
          data: {
            token: Mock.Random.guid(),
            message: '获取成功'
          }
        }
      } else if (username === 'worker' && password === 'worker' && role === 'staff') {
        return {
          code: 20000,
          data: {
            token: Mock.Random.guid(),
            message: '获取成功'
          }
        }
      } else {
      return {
        code: -999,
        data: {
          message: '密码错误'
        }
      }
    }

  }
}


