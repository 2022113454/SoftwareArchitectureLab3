<template>
  <div class="manage">
    <div class="manage-header">
      <el-select v-model="value" placeholder="课程筛选" style="margin-top: -30px">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
        ></el-option>
      </el-select>
      <!-- form搜索区域 -->
      <el-form :inline="true" :model="userForm">
        <el-form-item>
          <el-input
              placeholder="请输入筛选信息"
              v-model="userForm.name"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-refresh" @click="refresh"
          >刷新</el-button
          >
        </el-form-item>
      </el-form>
    </div>
    <div class="common-table">
      <el-table :data="tableData" style="width: 100%" height="740px"  stripe>
        <el-table-column prop="attendanceStudent" label="姓名"></el-table-column>
        <el-table-column prop="attendanceStudentSex" label="性别"></el-table-column>
        <el-table-column prop="attendanceStudentCompany" label="公司名称"></el-table-column>
        <el-table-column prop="attendanceStudentJob" label="工作岗位"></el-table-column>
        <el-table-column prop="attendanceStudentEmail" label="Email" width="280"></el-table-column>
        <el-table-column prop="attendanceStatus" label="状态">
          <template slot-scope="scope">
            <el-tag :type="getStatusType(scope.row.attendanceStatus)">
              {{ scope.row.attendanceStatus }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="control" label="操作">
          <template slot-scope="scope">
            <el-button
                size="mini"
                type="primary"
                @click="handleSignIn(scope.row)"
            >签到</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <div class="pager">
        <el-pagination
            layout="prev, pager, next"
            :total="total"
            @current-change="handlePage"
        >
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import {getCourseOfStaff, signUp} from "../api";
export default {
  data() {
    return {
      options: [],
      value: "",
      dialogVisible: false,
      tableData: [],
      courseData: [],
      total: 0, // 当前总条数
      pageData: {
        page: 1,
        limit: 10,
      },
      userForm: {
        name: "",
      },
    };
  },
  watch: {
    value(newValue) {
      this.getList();
    },
  },
  methods: {
    // 弹窗关闭时
    handleClose() {
      this.$refs.form.resetFields();
      this.dialogVisible = false;
    },
    cancel() {
      this.handleClose();
    },
    handleSignIn(row) {
      signUp({course_name: this.value, student_name: row.attendanceStudent}).then(() => {
        this.$message.success("签到成功");
        this.getList();
      });
    },
    getList() {
      // 获取课程数据
      getCourseOfStaff({
        params: {
          ...this.userForm,
          ...this.pageData,
          role: this.$store.state.tab.role,
        },
      }).then(({ data }) => {
        this.courseData = data.courses;
        this.updateOptions();
      });
      setTimeout(() => {
        getCourseOfStaff({
          params: {
            ...this.userForm,
            ...this.pageData,
            role: this.$store.state.tab.role,
            course: this.value,
          },
        }).then(({ data }) => {
          console.log("0000", data);
          this.tableData = data.list;

          // 注意：这里你可能想更新 item.attendanceStudentSex 的显示，但你的原始代码实际上是在尝试赋值，
          // 这会改变原始数据。如果你只是想在展示时改变显示，可以考虑在模板中处理或使用计算属性。
          // 但如果你确实需要更新数据，这里应使用 === 进行比较（更安全的比较），并且不要直接修改列表，因为这会改变引用。
          // 这里仅作为演示，展示如何安全地更新列表（虽然通常不建议直接修改 API 返回的数据）：
          this.tableData = data.list.map(item => ({
            ...item,
            attendanceStudentSexDisplay: item.attendanceStudentSex === "男" ? "男" : "女"
          }));

          // 注意：我现在添加了一个新属性 attendanceStudentSexDisplay 来避免修改原始数据

          this.total = data.count || 0;
        }).catch(error => {
          console.error("Error fetching data:", error);
        });
      }, 100);
      // 获取用户数据

    },
    refresh() {
      this.userForm.name = "";
      this.getList();
    },
    updateOptions() {
      this.options = this.courseData.map((course) => ({
        value: course.name,
      }));
    },
    // 选择页码的回调函数
    handlePage(val) {
      this.pageData.page = val;
      this.getList();
    },
    // 列表的查询
    onSubmit() {
      this.getList();
    },
    getStatusType(status) {
      switch (status) {
        case "未签到":
          return "danger";
        case "已签到":
          return "success";
        default:
          return "";
      }
    },
  },
  mounted() {
    this.getList();
    this.updateOptions();
  },
};
</script>

<style lang="less" scoped>
.manage {
  height: 90%;
  .manage-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .common-table {
    position: relative;
    height: calc(100% - 62px);
    .pager {
      bottom: 0;
      position: absolute;
      right: 20px;
    }
  }
}
</style>
