<template>
  <div class="block">
    <el-timeline>
      <el-timeline-item
        v-for="notice in notices"
        :key="notice.timestamp"
        :timestamp="formatDate(notice.timestamp)"
        placement="top"
      >
        <el-card class="notice-card">
          <div class="card-header">
            <h4>{{ notice.courseName }}</h4>
          </div>
          <div class="card-body">
            <p><strong>讲师:</strong> {{ notice.teacherName }}</p>
            <p><strong>计划招生数:</strong> {{ notice.amount }}</p>
            <p><strong>课程费用:</strong> {{ notice.cost }}</p>
            <p><strong>培训日期:</strong> {{ notice.date }}</p>
            <p><strong>培训时间:</strong> {{ notice.startTime }}</p>
            <p><strong>课程地点:</strong> {{ notice.location }}</p>
            <p><strong>报名截止日期:</strong> {{ notice.deadLine }}</p>
            <p><strong>备注:</strong> {{ notice.note }}</p>
          </div>
        </el-card>
      </el-timeline-item>
    </el-timeline>
  </div>
</template>
<script>
import { getNotices } from "../api";
export default {
  data() {
    return {
      notices: [],
      notice: {
        courseName: '',
        teacherName: '',
        amount: '',
        cost: '',
        date: '',
        startTime: '',
        location: '',
        deadLine: '',
        note: '',
        timestamp: ''
      }
    };
  },
  created() {
    this.fetchNotices();
  },
  methods: {
    fetchNotices() {
      // 获取的列表的数据
      getNotices().then(
          ({ data }) => {
            this.notices = data;
          }
        );
    },
    formatDate(dateString) {
      return dateString.replace(/-/g, '/');
    }
  }
};
</script>

<style scoped>
.block {
  max-width: 800px;
  margin:  auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  font-family: 'Arial', sans-serif;
}

.el-timeline-item {
  margin-bottom: 20px;
}

.notice-card {
  background: linear-gradient(135deg, #f0f0f0, #ffffff);
  border-radius: 8px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  transition: transform 0.3s;
}

.notice-card:hover {
  transform: scale(1.02);
}

.card-header {
  background-color: #3eaf7c;
  padding: 15px;
  border-radius: 8px 8px 0 0;
  color: white;
  text-align: center;
  font-size: 1.2em;
  font-weight: bold;
}

.card-body {
  padding: 20px;
  background-color: #ffffff;
}

.card-body p {
  margin: 10px 0;
  font-size: 14px;
  color: #333;
}

.card-body p strong {
  color: #3eaf7c;
  font-weight: bold;
}
</style>
