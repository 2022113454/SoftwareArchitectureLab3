// mock数据模拟
import Mock from 'mockjs'

// 图表数据
let List = []
export default {
  getStatisticalData: () => {
    //Mock.Random.float 产生随机数100到8000之间 保留小数 最小0位 最大0位
    for (let i = 0; i < 7; i++) {
      List.push(
          Mock.mock({
            语言类: Mock.Random.float(1000, 20000, 0, 0),
            算法类: Mock.Random.float(1000, 20000, 0, 0),
            开发类: Mock.Random.float(1000, 20000, 0, 0),
            AI类: Mock.Random.float(1000, 20000, 0, 0),
          })
      )
    }
    return {
      code: 20000,
      data: {
        // 饼图
        videoData: [
          {
            name: '20岁以下',
            value: 2999
          },
          {
            name: '20-40岁',
            value: 5999
          },
          {
            name: '40岁以上',
            value: 1500
          }
        ],
        // 柱状图
        userData: [
          {
            date: '周一',
            new: 5,
            active: 200
          },
          {
            date: '周二',
            new: 10,
            active: 500
          },
          {
            date: '周三',
            new: 12,
            active: 550
          },
          {
            date: '周四',
            new: 60,
            active: 800
          },
          {
            date: '周五',
            new: 65,
            active: 550
          },
          {
            date: '周六',
            new: 53,
            active: 770
          },
          {
            date: '周日',
            new: 33,
            active: 170
          }
        ],
        // 折线图
        orderData: {
          date: ['2024-01', '2024-02', '2024-03', '2024-04', '2024-05', '2024-06', '2024-07'],
          data: List
        },
      }
    }
  }
}