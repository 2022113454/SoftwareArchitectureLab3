<template>
  <el-row>
    <div class="num">
      <el-card
          v-for="item in countData"
          :key="item.name"
          :body-style="{ display: 'flex', padding: 0 }"
          class="num-card"
      >
        <i
            class="icon"
            :class="`el-icon-${item.icon}`"
            :style="{ background: item.color }"
        ></i>
        <div class="detail">
          <p class="price">{{ item.value }}</p>
          <p class="desc">{{ item.name }}</p>
        </div>
      </el-card>
    </div>
    <div class="text-container">
      <p class="left-text" style="margin-bottom: 10px">培训收入状况</p>
      <p class="right-text" style="margin-bottom: 10px">培训开销状况</p>
    </div>
    <div class="kk">
      <el-card style="height: 300px" class="card-large">
        <div ref="echarts1" style="height: 300px ;width: 100%;margin-left: 0px;"></div>
      </el-card>
      <el-card style="height: 300px" class="card-small">
        <RadarChart />
      </el-card>
    </div>
    <p class="centered-text" style="margin-bottom: 5px; margin-top: 20px">
      学员状况
    </p>
    <div class="graph">
      <el-card style="height: 260px">
        <div ref="echarts2" style="height: 260px"></div>
      </el-card>
      <el-card style="height: 260px">
        <div ref="echarts3" style="height: 260px;margin-top: -20px;"></div>
      </el-card>
    </div>
  </el-row>
</template>
<script>
import RadarChart from "../components/RadarChart.vue"; // 确保路径正确
import { getData,getNum } from "../api";
import * as echarts from "echarts";
export default {
  components: {
    RadarChart,
  },
  data() {
    return {
      tableData: [],
      tableLabel: {
        name: "课程",
        todayBuy: "今日购买",
        monthBuy: "本月购买",
        totalBuy: "总购买",
      },
      countData: [
        {
          name: "未开始课程数",
          value: 0,
          icon: "s-flag",
          color: "#2ec7c9",
        },
        {
          name: "进行中课程数",
          value: 0,
          icon: "success",
          color: "#61FA37",
        },
        {
          name: "已结束课程数",
          value: 0,
          icon: "s-claim",
          color: "#5ab1ef",
        },
        {
          name: "培训总收入(元)",
          value: 0,
          icon: "s-finance",
          color: "#FA7D41",
        },
        {
          name: "学员总数",
          value: 0,
          icon: "s-ticket",
          color: "#ffb980",
        },
        {
          name: "员工总数",
          value: 0,
          icon: "suitcase",
          color: "#FA5570",
        },
      ],
      videoData: [
        {
          name: '20岁以下',
          value: 0
        },
        {
          name: '20-40岁',
          value: 0
        },
        {
          name: '40岁以上',
          value: 0
        }
      ],
    };
  },
  mounted() {
    getNum().then(({ data }) => {
      this.countData[0].value = data.tostart_course_num;
      this.countData[1].value = data.doing_course_num;
      this.countData[2].value = data.end_course_num;
      this.countData[3].value = data.total_income;
      this.countData[4].value = data.student_fact;
      this.countData[5].value = data.instructor_num + data.executor_num;
      this.videoData[0].value = data.ageOne;
      this.videoData[1].value = data.ageTwo;
      this.videoData[2].value = data.ageThree;
    })
    getData().then(({ data }) => {
      console.log(data);
      const { tableData } = data.data;
      this.tableData = tableData;

      //折线图
      // 基于准备好的dom, 初始化echarts实例
      const echarts1 = echarts.init(this.$refs.echarts1);
      // 指定图表的配置项和数据
      var echarts1Option = {};
      // 处理数据xAxis
      const { orderData, userData } = data.data;
      const xAxis = orderData.date;
      const things = Object.keys(orderData.data[0]);
      const xAxisData = {
        data: xAxis,
      };
      const thingsData = {
        data: things,
      };
      echarts1Option.xAxis = xAxisData;
      echarts1Option.yAxis = {};
      echarts1Option.legend = thingsData;
      echarts1Option.series = [];
      things.forEach((key) => {
        echarts1Option.series.push({
          name: key,
          data: orderData.data.map((item) => item[key]),
          type: "line",
        });
      });
      // 使用刚指定的配置项和数据显示图表
      echarts1.setOption(echarts1Option);

      // 柱状图
      // 基于准备好的dom, 初始化echarts实例
      const echarts2 = echarts.init(this.$refs.echarts2);
      const echarts2Option = {
        legend: {
          // 图例文字颜色
          textStyle: {
            color: "#333",
          },
        },
        grid: {
          left: "20%",
        },
        // 提示框
        tooltip: {
          trigger: "axis",
        },
        xAxis: {
          type: "category", // 类目轴
          data: userData.map((item) => item.date),
          axisLine: {
            lineStyle: {
              color: "#17b3a3",
            },
          },
          axisLabel: {
            interval: 0,
            color: "#333",
          },
        },
        yAxis: [
          {
            type: "value",
            axisLine: {
              lineStyle: {
                color: "#17b3a3",
              },
            },
          },
        ],
        color: ["#FFE2B5", "#BEFFB5"],
        series: [
          {
            name: "新增学员",
            data: userData.map((item) => item.new),
            type: "bar",
          },
          {
            name: "活跃学员",
            data: userData.map((item) => item.active),
            type: "bar",
          },
        ],
      };
      echarts2.setOption(echarts2Option);
      //饼状图
      const echarts3 = echarts.init(this.$refs.echarts3);
      const echarts3Option = {
        tooltip: {
          trigger: "item",
        },
        color: ["#FFD700", "#8BC34A", "#FF6F61"],
        series: [
          {
            data: this.videoData,
            type: "pie",
          },
        ],
      };
      echarts3.setOption(echarts3Option);
    });
  },
};
</script>
<style lang="less" scoped>
.text-container {
  display: flex;
  justify-content: space-between;
  margin-bottom: -15px;
}
.num {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  .icon {
    width: 80px;
    height: 80px;
    font-size: 30px;
    text-align: center;
    line-height: 80px;
    color: #fff;
  }
  .detail {
    display: flex;
    flex-direction: column;
    justify-content: center;
    margin-left: 15px;
    .price {
      font-size: 30px;
      margin-bottom: 10px;
      line-height: 30px;
      height: 30px;
    }
    .desc {
      font-size: 14px;
      color: #999;
      text-align: center;
    }
  }
  .el-card {
    width: 32%;
    margin-bottom: 20px;
  }
}
.graph {
  display: flex;
  margin-top: 20px;
  justify-content: space-between;

  .el-card {
    width: 48%;
  }
}
.kk {
  display: flex;
  margin-top: 20px;
  justify-content: space-between;

  .card-large {
    flex: 3; /* 2 parts */
    height: 300px;
    margin-right: 40px; /* Add margin to create space between cards */
  }

  .card-small {
    flex: 2; /* 1 part */
    height: 300px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
}
.num-card {
  border-radius: 8px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  transition: transform 0.3s;
}

.num-card:hover {
  transform: scale(1.02);
}
</style>
<style scoped>
.centered-text {
  font-size: 18px; /* 字体大小 */
  font-weight: bold; /* 字体加粗 */
  color: #333; /* 字体颜色 */
  text-align: center; /* 文本居中 */
}
.left-text {
  font-size: 18px; /* 字体大小 */
  font-weight: bold; /* 字体加粗 */
  color: #333; /* 字体颜色 */
  margin-left: 300px;
}
.right-text {
  font-size: 18px; /* 字体大小 */
  font-weight: bold; /* 字体加粗 */
  color: #333; /* 字体颜色 */
  margin-right: 195px;
}
</style>
