<template>
  <div class="manage">
    <el-dialog
      title="新建课程"
      :visible.sync="dialogVisible"
      width="50%"
      :before-close="handleClose"
    >
      <!-- 课程表单信息 -->
      <el-form
        ref="form"
        :rules="rules"
        :inline="true"
        :model="form"
        label-width="100px"
      >
        <el-form-item label="课程名" prop="name" class="full-width">
          <el-input
            placeholder="请输入课程名"
            v-model="form.name"
            disabled
          ></el-input>
        </el-form-item>
        <el-form-item label="软件公司" prop="company" class="full-width">
          <el-input
            placeholder="请输入软件公司"
            v-model="form.company"
            disabled
          ></el-input>
        </el-form-item>
        <el-form-item label="执行人" prop="executor" class="full-width">
          <el-input
            placeholder="请输入执行人姓名"
            v-model="form.executor"
            disabled
          ></el-input>
        </el-form-item>
        <el-form-item label="培训费用" prop="cost" class="full-width">
          <el-input
            placeholder="请输入培训费用"
            v-model="form.cost"
            disabled
          ></el-input>
        </el-form-item>
        <el-form-item label="培训计划" prop="plan" class="large-width">
          <el-input
            type="textarea"
            :rows="8"
            style="width: 525px; height: 200px"
            placeholder="请输入培训计划"
            v-model="form.plan"
            disabled
          ></el-input>
        </el-form-item>

        <!-- 讲师资料 -->
        <el-divider>讲师资料</el-divider>
        <el-form-item label="姓名" prop="instructorName" class="full-width">
          <el-input
            placeholder="请输入讲师姓名"
            v-model="form.instructorName"
            disabled
          ></el-input>
        </el-form-item>
        <el-form-item label="职称" prop="instructorTitle" class="full-width">
          <el-input
            placeholder="请输入职称"
            v-model="form.instructorTitle"
            disabled
          ></el-input>
        </el-form-item>
        <el-form-item
          label="擅长领域"
          prop="instructorField"
          class="full-width"
        >
          <el-input
            placeholder="请输入擅长领域"
            v-model="form.instructorField"
            disabled
          ></el-input>
        </el-form-item>
        <el-form-item label="Email" prop="instructorEmail" class="full-width">
          <el-input
            placeholder="请输入Email"
            v-model="form.instructorEmail"
            disabled
          ></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="instructorPhone" class="full-width">
          <el-input
            placeholder="请输入电话"
            v-model="form.instructorPhone"
            disabled
          ></el-input>
        </el-form-item>

        <!-- 执行人负责内容 -->
        <el-divider>执行人负责内容</el-divider>
        <el-form-item
          label="培训内容"
          prop="trainingContent"
          class="large-width"
        >
          <el-input
            type="textarea"
            :rows="4"
            style="width: 525px; height: 100px"
            placeholder="请输入培训内容"
            v-model="form.trainingContent"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="开始时间"
          prop="trainingStartTime"
          class="full-width"
        >
          <el-date-picker
            v-model="form.trainingStartTime"
            type="datetime"
            placeholder="选择日期和时间"
            style="width: 525px"
          ></el-date-picker>
        </el-form-item>
        <el-form-item
          label="结束时间"
          prop="trainingEndTime"
          class="full-width"
        >
          <el-date-picker
            v-model="form.trainingEndTime"
            type="datetime"
            placeholder="选择日期和时间"
            style="width: 525px"
          ></el-date-picker>
        </el-form-item>
        <el-form-item
          label="培训地点"
          prop="trainingLocation"
          class="full-width"
        >
          <el-input
            placeholder="请输入培训地点"
            v-model="form.trainingLocation"
          ></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="cancel">取消</el-button>
        <el-button type="primary" @click="submit">确定</el-button>
      </span>
    </el-dialog>
    <div class="manage-header">
      <!-- form搜索区域 -->
      <el-form :inline="true" :model="userForm">
        <el-form-item>
          <el-input
            placeholder="请输入课程筛选信息"
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
        <el-table-column prop="id" label="课程ID"> </el-table-column>
        <el-table-column prop="name" label="课程名称"> </el-table-column>
        <el-table-column prop="instructorName" label="讲师"> </el-table-column>
        <el-table-column prop="executor" label="执行人"> </el-table-column>
        <el-table-column prop="status" label="课程状态">
          <template slot-scope="scope">
            <el-tag :type="getStatusType(scope.row.status)">
              {{ scope.row.status }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="control" label="操作">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.row)"
              >编辑</el-button
            >
            <el-button
              type="success"
              size="mini"
              @click="handleStart(scope.row)"
              >启动</el-button
            >
            <el-button
              type="danger"
              size="mini"
              @click="handleFinish(scope.row)"
              >完成</el-button
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
import { getCourseOfExe, editCourse, startCourse, endCourse } from "../api";
export default {
  data() {
    return {
      dialogVisible: false,
      form: {
        name: "",
        company: "",
        executor: "",
        cost: "",
        plan: "",
        instructorName: "",
        instructorTitle: "",
        instructorField: "",
        instructorEmail: "",
        instructorPhone: "",
        trainingContent: "",
        trainingStartTime: "",
        trainingEndTime: "",
        trainingLocation: "",
      },
      rules: {
        name: [{ required: true, message: "请输入课程名" }],
        company: [{ required: true, message: "请输入软件公司" }],
        executor: [{ required: true, message: "请输入执行人姓名" }],
        cost: [{ required: true, message: "请输入培训费用" }],
        plan: [{ required: true, message: "请输入培训计划" }],
        instructorName: [{ required: true, message: "请输入讲师姓名" }],
        instructorTitle: [{ required: true, message: "请输入职称" }],
        instructorField: [{ required: true, message: "请输入擅长领域" }],
        instructorEmail: [{ required: true, message: "请输入Email" }],
        instructorPhone: [{ required: true, message: "请输入电话" }],
        trainingContent: [{ required: true, message: "请输入培训内容" }],
        trainingStartTime: [{ required: true, message: "请选择培训开始时间" }],
        trainingEndTime: [{ required: true, message: "请选择培训结束时间" }],
        trainingLocation: [{ required: true, message: "请输入培训地点" }],
      },
      tableData: [],
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
  methods: {
    //提交用户表单
    submit() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          editCourse(this.form).then(() => {
            //重新获取列表的接口

            this.getList();
          });
          // 清空表单的数据
          this.$refs.form.resetFields();
          // 关闭弹窗
          this.dialogVisible = false;
        }
      });
    },
    handleEdit(row) {
      this.dialogVisible = true;
      // 注意需要对当前行数据进行深拷贝，否则会出错
      this.form = JSON.parse(JSON.stringify(row));
    },
    handleStart(row) {
      if (row.status === "已结束") {
        this.$message.error("该课程已结束，无法启动");
        return;
      }
      if (row.status === "进行中") {
        this.$message.error("该课程已在进行中");
        return;
      }
      this.$confirm("此操作将启动该课程, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        startCourse({ params: { id: row.id } })
          .then(() => {
            this.$message({
              type: "success",
              message: "启动成功",
            });
            this.getList(); // 在成功启动课程后重新获取列表
          })
          .catch((error) => {
            this.$message({
              type: "error",
              message: "启动失败: " + error.message,
            });
          });
      });
    },
    handleFinish(row) {
      console.log(row.id);
      if (row.status === "已结束") {
        this.$message.error("该课程已结束，无法再次完成");
        return;
      }
      if (row.status === "未开始") {
        this.$message.error("该课程未开始，无法完成");
        return;
      }
      this.$confirm("此操作将设置该课程为已完成状态, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        endCourse({ params: { id: row.id } })
          .then(() => {
            this.$message({
              type: "success",
              message: "成功完成",
            });
            this.getList(); // 在成功启动课程后重新获取列表
          })
          .catch((error) => {
            this.$message({
              type: "error",
              message: "完成失败: " + error.message,
            });
          });
      });
    },
    getList() {
      // 获取的列表的数据
      console.log(this.$store.state.tab.role, "当前角色");
      getCourseOfExe({
        params: {
          ...this.userForm,
          ...this.pageData,
          role: this.$store.state.tab.role,
        },
      }).then(({ data }) => {
        this.tableData = data.list;
        this.total = data.count || 0;
      });
    },
    refresh() {
      this.userForm.name = "";
      this.getList();
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
        case "未开始":
          return "danger";
        case "进行中":
          return "success";
        case "已结束":
          return "info";
        default:
          return "";
      }
    },
  },
  mounted() {
    this.getList();
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
