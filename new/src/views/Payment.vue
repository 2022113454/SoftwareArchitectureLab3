<!-- 学生选课界面 -->
<template>
  <div class="manage">
    <!-- 查询课程信息对话框 -->
    <el-dialog
      title="课程信息"
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
          <el-input v-model="form.name" disabled></el-input>
        </el-form-item>
        <el-form-item label="软件公司" prop="company" class="full-width">
          <el-input v-model="form.company" disabled></el-input>
        </el-form-item>
        <el-form-item label="执行人" prop="executor" class="full-width">
          <el-input v-model="form.executor" disabled></el-input>
        </el-form-item>
        <el-form-item label="培训费用" prop="cost" class="full-width">
          <el-input v-model="form.cost" disabled></el-input>
        </el-form-item>
        <el-form-item label="培训计划" prop="plan" class="large-width">
          <el-input
            type="textarea"
            :rows="8"
            style="width: 525px; height: 200px"
            v-model="form.plan"
            disabled
          ></el-input>
        </el-form-item>

        <!-- 讲师资料 -->
        <el-divider>讲师资料</el-divider>
        <el-form-item label="姓名" prop="instructorName" class="full-width">
          <el-input v-model="form.instructorName" disabled></el-input>
        </el-form-item>
        <el-form-item label="职称" prop="instructorTitle" class="full-width">
          <el-input v-model="form.instructorTitle" disabled></el-input>
        </el-form-item>
        <el-form-item
          label="擅长领域"
          prop="instructorField"
          class="full-width"
        >
          <el-input v-model="form.instructorField" disabled></el-input>
        </el-form-item>
        <el-form-item label="Email" prop="instructorEmail" class="full-width">
          <el-input v-model="form.instructorEmail" disabled></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="instructorPhone" class="full-width">
          <el-input v-model="form.instructorPhone" disabled></el-input>
        </el-form-item>
        <el-form-item
          label="培训内容"
          prop="trainingContent"
          class="large-width"
        >
          <el-input
            type="textarea"
            :rows="4"
            style="width: 525px; height: 100px"
            disabled
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
            style="width: 525px"
            disabled
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
            disabled
            style="width: 525px"
          ></el-date-picker>
        </el-form-item>
        <el-form-item
          label="培训地点"
          prop="trainingLocation"
          class="full-width"
        >
          <el-input v-model="form.trainingLocation" disabled></el-input>
        </el-form-item>
      </el-form>
    </el-dialog>

    <!-- 课程缴费对话框 -->
    <el-dialog
      title="课程缴费"
      :visible.sync="paymentDialogVisible"
      width="50%"
      :before-close="handleCloseOfPayment"
    >
      <el-form
        ref="paymentForm"
        :rules="paymentRules"
        :inline="false"
        :model="paymentForm"
        label-width="120px"
      >
        <!-- 课程信息 -->
        <el-divider>课程信息</el-divider>
        <el-form-item label="课程名称" prop="courseName" class="full-width">
          <el-input v-model="paymentForm.courseName" disabled></el-input>
        </el-form-item>
        <el-form-item label="课程费用" prop="courseFee" class="full-width">
          <el-input v-model="paymentForm.courseFee" disabled></el-input>
        </el-form-item>

        <!-- 学员信息 -->
        <el-divider>学员信息</el-divider>
        <el-form-item label="学员姓名" prop="studentName" class="full-width">
          <el-input v-model="paymentForm.studentName" ></el-input>
        </el-form-item>
        <el-form-item label="电子邮箱" prop="email" class="full-width">
          <el-input v-model="paymentForm.email" ></el-input>
        </el-form-item>

        <!-- 支付方式 -->
        <el-divider>支付方式</el-divider>
        <el-radio-group
          v-model="paymentForm.paymentMethod"
          @change="openQRCodeDialog"
          class="centered-radio-group"
        >
          <el-radio label="支付宝">支付宝</el-radio>
          <el-radio label="微信支付">微信支付</el-radio>
        </el-radio-group>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="handleCloseOfPayment">取消</el-button>
        <el-button type="primary" @click="submitPayment">提交</el-button>
      </span>
    </el-dialog>

    <!-- 支付二维码对话框 -->
    <el-dialog
      title="扫描二维码进行支付"
      :visible.sync="qrcodeDialogVisible"
      width="30%"
      :before-close="handleCloseQRCodeDialog"
    >
      <div class="qrcode-container">
        <img v-if="paymentForm.paymentMethod === '支付宝'" src="../assets/images/zhifubao.jpg" alt="支付宝二维码" class="qrcode-image" />
        <img v-else-if="paymentForm.paymentMethod === '微信支付'" src="../assets/images/weixin.jpg" alt="微信支付二维码" class="qrcode-image" />
      </div>
    </el-dialog>

    <div class="manage-header">
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

    <!-- 表格内容 -->
    <div class="common-table">
      <el-table :data="courses" style="width: 100%" height="740px"  stripe>
        <el-table-column prop="name" label="课程名称"></el-table-column>
        <el-table-column prop="instructorName" label="讲师"></el-table-column>
        <el-table-column
          prop="trainingStartTime"
          label="起始时间"
        ></el-table-column>
        <el-table-column
          prop="trainingEndTime"
          label="结束时间"
        ></el-table-column>
        <el-table-column prop="trainingLocation" label="地点"></el-table-column>
        <el-table-column prop="cost" label="培训费用(￥)"></el-table-column>
        <el-table-column prop="pay_status" label="课程状态">
          <template slot-scope="scope">
            <el-tag :type="getStatusType(scope.row.pay_status)">
              {{ scope.row.pay_status }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="300">
          <template slot-scope="scope">
            <el-button
              @click="selectCourse(scope.row)"
              type="primary"
              size="mini"
              >缴费</el-button
            >
            <el-button @click="viewCourse(scope.row)" type="success" size="mini"
              >查看</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <div class="pager">
        <el-pagination
          layout="prev, pager, next"
          :total="total"
          @current-change="handlePage"
        ></el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import { pay, getCourses, getPersonalCenter } from "../api";
export default {
  data() {
    return {
      studentInfo: {
        userName: "",
        userType: "",
        name: "",
        gender: "",
        age: "",
        phone: "",
        email: "",
        company: "",
        position: "",
        level: "",
      },
      // 课程对话框的显示与否
      dialogVisible: false,
      // 课程的属性
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
      // 课程的rules
      rules: {
        name: [{ required: false, message: "请输入课程名" }],
        company: [{ required: false, message: "请输入软件公司" }],
        executor: [{ required: false, message: "请输入执行人姓名" }],
        cost: [{ required: false, message: "请输入培训费用" }],
        plan: [{ required: false, message: "请输入培训计划" }],
        instructorName: [{ required: false, message: "请输入讲师姓名" }],
        instructorTitle: [{ required: false, message: "请输入职称" }],
        instructorField: [{ required: false, message: "请输入擅长领域" }],
        instructorEmail: [{ required: false, message: "请输入Email" }],
        instructorPhone: [{ required: false, message: "请输入电话" }],
        trainingContent: [{ required: false, message: "请输入培训内容" }],
        trainingTime: [{ required: false, message: "请选择培训时间" }],
        trainingLocation: [{ required: false, message: "请输入培训地点" }],
      },
      paymentDialogVisible: false,
      qrcodeDialogVisible: false,
      paymentQRCode: "",
      paymentForm: {
        courseName: "示例课程",
        courseFee: "1000",
        studentName: "张三",
        email: "zhangsan@example.com",
        paymentMethod: "",
      },
      paymentRules: {
        courseName: [{ required: true, message: "请输入课程名称" }],
        courseFee: [{ required: true, message: "请输入课程费用" }],
        studentName: [{ required: true, message: "请输入学员姓名" }],
        email: [{ required: true, message: "请输入电子邮箱" }],
        paymentMethod: [{ required: true, message: "请选择支付方式" }],
      },
      total: 0, // 当前总条数
      pageData: {
        page: 1,
        limit: 10,
      },
      userForm: {
        name: "",
      },
      filter: {
        showSelected: false,
      },
      courses: [],
    };
  },
  methods: {
    // 获取学生信息
    fetchUserInfo() {
      getPersonalCenter({ params: { name: this.$store.state.tab.role } }).then(
        ({ data }) => {
          this.studentInfo = data;
        }
      );
    },
    // 二维码表单
    openQRCodeDialog() {
      this.qrcodeDialogVisible = true;
    },
    // 查看课程信息
    viewCourse(course) {
      this.dialogVisible = true;
      // 注意需要对当前行数据进行深拷贝，否则会出错
      console.log("ooooo",this.courses)
      this.form = this.courses.find((item) => item.id === course.id);
      console.log("iiiiii",this.form)
    },
    // 课程评价
    selectCourse(course) {
      this.paymentDialogVisible = true;
      this.paymentForm.courseName = course.name;
      this.paymentForm.courseFee = course.cost;
      this.paymentForm.studentName = this.studentInfo.name;
      this.paymentForm.email = this.studentInfo.email;
    },
    // 提交评价
    submitPayment() {
      this.$refs.paymentForm.validate((valid) => {
        if (valid) {
          pay({course_name: this.paymentForm.courseName, student_name: this.paymentForm.studentName}).then(() => {
            this.$message.success("缴费成功");
            this.fetchSelectedCourses();
          });
          this.handleCloseOfPayment();
        } else {
          console.log("表单验证失败");
          return false;
        }
      });
    },

    getStatusType(status) {
      switch (status) {
        case "已缴费":
          return "success";
        case "未缴费":
          return "danger";
        default:
          return "";
      }
    },

    // 弹窗关闭时
    handleClose() {
      this.$refs.form.resetFields();
      this.dialogVisible = false;
    },
    handleCloseOfPayment() {
      this.$refs.paymentForm.resetFields();
      this.paymentDialogVisible = false;
    },
    handleCloseQRCodeDialog() {
      this.qrcodeDialogVisible = false;
    },

    // 选择页码的回调函数
    handlePage(val) {
      this.pageData.page = val;
      this.getList();
    },

    onSubmit() {
      // TODO: 处理查询逻辑
      console.log("查询:", this.userForm.name);
    },

    // 刷新
    refresh() {
      location.reload(); // 此为刷新指令
      console.log("刷新");
    },

     // 获取课程列表
     fetchSelectedCourses() {
      getCourses({
        params: {
          ...this.userForm,
          ...this.pageData,
          student: this.$store.state.tab.role,
        },
      }).then(({ data }) => {
        console.log("aaaaa",data)
        this.courses = data.selected;
        this.total = data.countTwo || 0;
      });
    },
  },
  mounted() {
    this.fetchSelectedCourses();
    this.fetchUserInfo();
  },
};
</script>

<style lang="less" scoped>
.course-selection {
  padding: 20px;
}
.centered-radio-group {
  display: flex;
  justify-content: center;
}
.qrcode-image {
  max-width: 400px; /* 控制图片最大宽度 */
  height: auto; /* 自动计算高度 */
  display: block; /* 块级显示，以便使用 margin 居中 */
  margin: 0 auto; /* 居中 */
}
.qrcode-container {
  text-align: center;
  margin-top: 20px;
}

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
<!-- 使禁用的input颜色与不禁用一致 -->
<style lang="less" scoped>
.el-input.is-disabled /deep/ .el-input__inner {
  color: #606266;
  background-color: #f8f8f8;
}
</style>
