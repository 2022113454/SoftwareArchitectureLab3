<template>
  <div class="evaluationSummary" style="margin-top: -10px">
    <div class="manage-header">
      <el-select
          v-model="selectedCourse"
          placeholder="课程筛选"
          @change="handleCourseChange"
      >
        <el-option
            v-for="course in courses"
            :key="course.name"
            :label="course.name"
            :value="course"
        ></el-option>
      </el-select>
    </div>

    <el-form
        label-width="70px"
        :inline="true"
        class="summary-form"
        :model="form"
        ref="form"
    >
      <h3 class="title">课程学员评价汇总</h3>
      <el-divider>评分信息</el-divider>
      <el-row>
        <el-col :span="24">
          <el-form-item label="课程名称" class="full-width">
            <el-input v-model="form.courseName" disabled></el-input>
          </el-form-item>
          <el-form-item label="课程均分" class="full-width">
            <el-rate
                v-model="form.aveCourseScore"
                disabled
                show-score
                text-color="#ff9900"
                score-template="{value}"
            >
            </el-rate>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="24">
          <el-form-item label="讲师姓名" class="full-width">
            <el-input v-model="form.teacherName" disabled></el-input>
          </el-form-item>
          <el-form-item label="讲师均分" class="full-width">
            <el-rate
                v-model="form.aveTeacherScore"
                disabled
                show-score
                text-color="#ff9900"
                score-template="{value}"
            >
            </el-rate>
          </el-form-item>
        </el-col>
      </el-row>

      <el-divider>学员意见与建议</el-divider>
      <div id="wordCloud" class="word-cloud"></div>
    </el-form>
  </div>
</template>

<script>
import WordCloud from "wordcloud";
import { getEvaSummary } from "../api";
export default {
  data() {
    return {
      // 课程名
      courses: [],
      // 课程具体信息
      courseData:[],
      selectedCourse: null,
      form: {
        courseName: "",
        aveCourseScore: 0,
        teacherName: "",
        aveTeacherScore: 0,
        opinions: [],
      },
      rules: {}, // 确保规则对象存在
    };
  },
  methods: {
    handleCourseChange() {
      this.fetchCourseEvaluations();
    },
    fetchCourseEvaluations() {
      if (!this.selectedCourse) return;
      const data = this.courseData.find(course => course.courseName === this.selectedCourse);
      this.form.courseName = data.courseName;
      this.form.teacherName = data.teacherName;
      this.form.aveCourseScore = data.aveCourseScore;
      this.form.aveTeacherScore = data.aveTeacherScore;
      this.form.opinions = data.opinions;
      this.generateWordCloud(data.opinions);
    },
    generateWordCloud(opinions) {
      const wordList = opinions.map((word) => [word, Math.random() * 50 + 10]);
      WordCloud(document.getElementById("wordCloud"), { list: wordList });
    },
    getList() {
      getEvaSummary({
        role: this.$store.state.tab.role,
      }).then(({ data }) => {
        console.log("555555",data)
        this.courseData = data;
        this.courses = this.courseData.map(course => course.courseName);
        console.log("kkkk",this.courseData);
        console.log("kkkk",this.courses);
      });
    },
  },
  mounted() {
    this.getList();
    this.selectedCourse = this.courses[0];
    this.fetchCourseEvaluations();
  },
};
</script>

<style scoped>
.evaluationSummary {
  width: 80%;
  margin: 20px auto;
  padding: 35px;
  background-color: #fff;
  border-radius: 15px;
  box-shadow: 0 0 15px #cac6c6;
  position: relative;
}

.manage-header {
  position: absolute;
  top: 20px;
  left: 20px;
}

.summary-form {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.title {
  text-align: center;
}

.word-cloud {
  width: 100%;
  height: 300px;
  margin: 20px 0;
}
</style>
<!-- 使禁用的input颜色与不禁用一致 -->
<style lang="less" scoped>
.el-input.is-disabled /deep/ .el-input__inner {
  color: #606266;
  background-color: #f8f8f8;
}
</style>
