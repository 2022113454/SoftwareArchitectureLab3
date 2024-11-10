<template>
  <div class="radar-chart" ref="radarChart" style="width: 600px; height: 290px;margin-top: 30px;"></div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  name: 'RadarChart',
  mounted() {
    this.initChart();
  },
  methods: {
    initChart() {
      const chart = echarts.init(this.$refs.radarChart);

      const option = {
        tooltip: {},
        legend: {
          bottom: 270,  // 将图例放置在底部
          data: ['预算分配', '实际开销']
        },
        radar: {
          center: ['50%', '50%'],  // 中心位置
          radius: '60%',  // 半径大小
          indicator: [
            { name: '销售', max: 6500 },
            { name: '管理', max: 16000 },
            { name: '信息技术', max: 30000 },
            { name: '客服', max: 38000 },
            { name: '研发', max: 52000 },
            { name: '市场', max: 25000 }
          ]
        },
        series: [{
          name: '预算 vs 开销',
          type: 'radar',
          data: [
            {
              value: [4300, 10000, 28000, 35000, 50000, 19000],
              name: '预算分配'
            },
            {
              value: [5000, 14000, 28000, 31000, 42000, 21000],
              name: '实际开销'
            }
          ]
        }]
      };

      chart.setOption(option);
    }
  }
};
</script>

<style scoped>
.radar-chart {
  width: 100%;
  height: 100%;
}
</style>
