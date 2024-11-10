<template>
  <div>
    <el-table :data="enroll" style="width: 100%" height="740px">
      <el-table-column prop="id" label="申请ID"  width="130px" />
      <el-table-column prop="student_name" label="报名学员姓名" />
      <el-table-column prop="course_name" label="报名课程" />
      <el-table-column prop="company" label="所属公司" />
      <el-table-column prop="date" label="申请日期" />
      <el-table-column prop="status" label="状态">
        <template slot-scope="scope" >
          <el-tag :type="getStatusType(scope.row.status)">
            {{ scope.row.status }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="250px">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="primary"
              @click="viewApplication(scope.row)"
          >查看</el-button
          >
          <el-button
              size="mini"
              type="success"
              @click="approveApplication(scope.row)"
          >批准</el-button
          >
          <el-button
              size="mini"
              type="danger"
              @click="rejectApplication(scope.row)"
          >拒绝</el-button
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

    <!-- 报名表单对话框 -->
    <el-dialog
        title="报名信息"
        :visible.sync="dialogVisible"
        width="50%"
        :before-close="handleClose"
    >
      <!-- 学生信息 -->
      <el-form
          ref="form"
          :rules="signUpRules"
          :inline="false"
          :model="form"
          label-width="120px"
      >
        <el-form-item label="学生姓名" prop="name" class="full-width">
          <el-input
              placeholder="请填写姓名"
              v-model="form.student_name"
              disabled
          ></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex" class="full-width">
          <el-radio-group v-model="form.sex" disabled>
            <el-radio label="男">男</el-radio>
            <el-radio label="女">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年龄" prop="age" class="full-width">
          <el-input
              placeholder="请填写年龄"
              v-model="form.age"
              type="number"
              disabled
          ></el-input>
        </el-form-item>
        <el-form-item label="联系电话" prop="phone" class="full-width">
          <el-input
              placeholder="请填写联系电话"
              v-model="form.phone"
              disabled
          ></el-input>
        </el-form-item>
        <el-form-item label="电子邮箱" prop="email" class="full-width">
          <el-input
              placeholder="请填写电子邮箱"
              v-model="form.email"
              disabled
          ></el-input>
        </el-form-item>
        <el-form-item label="所属公司名称" prop="company" class="full-width">
          <el-input
              placeholder="请填写公司名称"
              v-model="form.company"
              disabled
          ></el-input>
        </el-form-item>
        <el-form-item label="工作岗位" prop="position" class="full-width">
          <el-select v-model="form.duty" placeholder="请选择工作岗位" disabled>
            <el-option label="软件工程师" value="软件工程师"></el-option>
            <el-option label="数据分析师" value="数据分析师"></el-option>
            <el-option
                label="质量保证工程师"
                value="质量保证工程师"
            ></el-option>
            <el-option label="产品经理" value="产品经理"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="技术水平" prop="level" class="full-width">
          <el-select v-model="form.level" placeholder="请选择技术水平" disabled>
            <el-option label="萌新" value="萌新"></el-option>
            <el-option label="小成" value="小成"></el-option>
            <el-option label="高手" value="高手"></el-option>
            <el-option label="神" value="神"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="申请理由" prop="reason" class="full-width">
          <el-input
              type="textarea"
              placeholder="请填写申请理由"
              v-model="form.enrolling_message"
              :rows="4"
              disabled
          ></el-input>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { getEnroll, approve, reject } from "../api";
export default {
  data() {
    return {
      enroll: [], // 报名列表数据
      dialogVisible: false, // 控制对话框的显示
      form: {
        course_name: "",
        student_name: "",
        enrolling_message: "",
        id: "",
        date: "",
        sex: "",
        company: "",
        duty: "",
        level: "",
        email: "",
        phone: ""
      },
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
  created() {
    this.fetchApplications();
  },
  methods: {
    fetchApplications() {
      getEnroll({ params: { ...this.userForm, ...this.pageData,executor: this.$store.state.tab.role} }).then(
          ({ data }) => {
            console.log("报名管理",data)
            this.enroll = data.list;
            this.total = data.count || 0;
          }
      );
    },
    // 查看
    viewApplication(row) {
      this.dialogVisible = true;
      // 注意需要对当前行数据进行深拷贝，否则会出错
      this.form = this.enroll.find((item) => item.id === row.id);
    },
    // 批准申请·
    approveApplication(row) {
      if (row.status === "已批准") {
        this.$message.error("该报名已批准，无法再次批准");
        return;
      }
      if (row.status === "已拒绝") {
        this.$message.error("该报名已被拒绝");
        return;
      }
      this.$confirm("此操作将批准该报名, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        approve( { course_name: row.course_name,student_name: row.student_name } )
            .then(() => {
              this.$message({
                type: "success",
                message: "已批准",
              });
              this.fetchApplications(); // 在成功启动课程后重新获取列表
            })
            .catch((error) => {
              this.$message({
                type: "error",
                message: "批准失败: " + error.message,
              });
            });
      });
    },
    // 拒绝申请
    rejectApplication(row) {
      if (row.status === "已批准") {
        this.$message.error("该报名已批准，无法被拒绝");
        return;
      }
      if (row.status === "已拒绝") {
        this.$message.error("该报名已被拒绝");
        return;
      }
      this.$confirm("此操作将拒绝该报名, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        reject({ course_name: row.course_name,student_name: row.student_name })
            .then(() => {
              this.$message({
                type: "success",
                message: "已拒绝",
              });
              this.fetchApplications();  // 在成功启动课程后重新获取列表
            })
            .catch((error) => {
              this.$message({
                type: "error",
                message: "拒绝失败: " + error.message,
              });
            });
      });
    },
    handleClose() {
      this.dialogVisible = false;
    },
    // 选择页码的回调函数
    handlePage(val) {
      this.pageData.page = val;
      this.getList();
    },
    getStatusType(status) {
      switch (status) {
        case "待处理":
          return "danger";
        case "已批准":
          return "success";
        case "已拒绝":
          return "info";
        default:
          return "";
      }
    },
  },
};
</script>

<style scoped>
.dialog-footer {
  text-align: right;
}
.el-input__inner,
.el-textarea__inner,
.el-input-number__input {
  background-color: #f5f7fa !important; /* 背景颜色 */
  color: #333 !important; /* 文字颜色 */
  font-size: 14px !important; /* 字体大小 */
}

.el-form-item__label {
  font-weight: bold; /* 标签字体加粗 */
  color: #333; /* 标签文字颜色 */
}
</style>

<!-- 使禁用的input颜色与不禁用一致 -->
<style lang="less" scoped>
.el-input.is-disabled /deep/ .el-input__inner {
  color: #606266;
  background-color: #f8f8f8;
}
</style>