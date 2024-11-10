<template>
  <div>
    <el-table :data="applications" style="width: 100%">
      <el-table-column prop="applicationID" label="申请ID" width="180" />
      <el-table-column prop="applicant" label="申请人姓名" />
      <el-table-column prop="company" label="公司" />
      <el-table-column prop="date" label="申请日期" />
      <el-table-column prop="status" label="状态">
        <template slot-scope="scope">
          <el-tag :type="getStatusType(scope.row.status)">
            {{ scope.row.status }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作">
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
    <el-dialog
      title="申请详情"
      :visible.sync="dialogVisible"
      width="50%"
      :before-close="handleClose"
    >
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
        label-width="120px"
        class="demo-ruleForm"
      >
        <el-form-item label="公司名称" prop="company">
          <el-input v-model="form.company" disabled></el-input>
        </el-form-item>
        <el-form-item label="申请人姓名" prop="applicant">
          <el-input v-model="form.applicant" disabled></el-input>
        </el-form-item>
        <el-form-item label="培训主题" prop="topic">
          <el-input v-model="form.topic" disabled></el-input>
        </el-form-item>
        <el-form-item label="培训时间" prop="date">
          <el-input v-model="form.time" disabled></el-input>
        </el-form-item>
        <el-form-item label="培训内容" prop="content">
          <el-input type="textarea" v-model="form.content" disabled></el-input>
        </el-form-item>
        <el-form-item label="培训规模(人数)" prop="scale">
          <el-input v-model="form.scale" disabled></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input type="textarea" v-model="form.remarks" disabled></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">关闭</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
 import { getApplication, approveApp, rejectApp} from "../api";
export default {
  data() {
    return {
      applications: [], // 申请列表数据
      dialogVisible: false, // 控制对话框的显示
      form: {
        company: "",
        applicant: "",
        topic: "",
        time: "",
        content: "",
        scale: "",
        remarks: "",
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
    // 获取申请列表
    fetchApplications() {
      getApplication({ params: { ...this.userForm, ...this.pageData } }).then(
        ({ data }) => {
          console.log(data,"88888888")
          this.applications = data.list;
          this.total = data.count || 0;
        }
      );
    },
    // 查看
    viewApplication(row) {
      this.dialogVisible = true;
       // 注意需要对当前行数据进行深拷贝，否则会出错
       this.form = this.applications.find((item) => item.applicant === row.applicant);
    },
    // 批准申请·
    approveApplication(row) {
      if (row.status === "已批准") {
        this.$message.error("该课程已批准，无法再次批准");
        return;
      }
      if (row.status === "已拒绝") {
        this.$message.error("该课程已被拒绝");
        return;
      }
      this.$confirm("此操作将批准该申请, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        approveApp({ id: row.applicationID } )
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
        this.$message.error("该课程已批准，无法被拒绝");
        return;
      }
      if (row.status === "已拒绝") {
        this.$message.error("该课程已被拒绝");
        return;
      }
      this.$confirm("此操作将拒绝该课程, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        rejectApp({ id: row.applicationID} )
          .then(() => {
            this.$message({
              type: "success",
              message: "已拒绝",
            });
            this.fetchApplications(); // 在成功启动课程后重新获取列表
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