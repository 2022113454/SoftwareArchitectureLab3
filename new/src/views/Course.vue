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
          <el-input placeholder="请输入课程名" v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="软件公司" prop="company" class="full-width">
          <el-input placeholder="请输入软件公司" v-model="form.company"></el-input>
        </el-form-item>
        <el-form-item label="执行人" prop="executor" class="full-width">
          <el-input placeholder="请输入执行人姓名" v-model="form.executor"></el-input>
        </el-form-item>
        <el-form-item label="培训费用" prop="cost" class="full-width">
          <el-input placeholder="请输入培训费用" v-model="form.cost"></el-input>
        </el-form-item>
        <el-form-item label="培训计划" prop="plan" class="large-width">
          <el-input
            type="textarea"
            :rows="8"
            style="width: 525px; height: 200px;"
            placeholder="请输入培训计划"
            v-model="form.plan"
          ></el-input>
        </el-form-item>

        <!-- 讲师资料 -->
        <el-divider>讲师资料</el-divider>
        <el-form-item label="姓名" prop="instructorName" class="full-width">
          <el-input placeholder="请输入讲师姓名" v-model="form.instructorName"></el-input>
        </el-form-item>
        <el-form-item label="职称" prop="instructorTitle" class="full-width">
          <el-input placeholder="请输入职称" v-model="form.instructorTitle"></el-input>
        </el-form-item>
        <el-form-item label="擅长领域" prop="instructorField" class="full-width">
          <el-input placeholder="请输入擅长领域" v-model="form.instructorField"></el-input>
        </el-form-item>
        <el-form-item label="Email" prop="instructorEmail" class="full-width">
          <el-input placeholder="请输入Email" v-model="form.instructorEmail"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="instructorPhone" class="full-width">
          <el-input placeholder="请输入电话" v-model="form.instructorPhone"></el-input>
        </el-form-item>

         <!-- 执行人负责内容 -->
         <el-divider>执行人负责内容</el-divider>
        <el-form-item label="培训内容" prop="trainingContent" class="large-width">
          <el-input
            type="textarea"
            :rows="4"
            style="width: 525px; height: 100px;"
            placeholder="请输入培训内容"
            v-model="form.trainingContent"
            disabled
          ></el-input>
        </el-form-item>
        <el-form-item label="开始时间" prop="trainingStartTime" class="full-width">
          <el-date-picker
            v-model="form.trainingStartTime"
            type="datetime"
            placeholder="选择日期和时间"
            style="width: 525px;"
            disabled
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="trainingEndTime" class="full-width">
          <el-date-picker
            v-model="form.trainingEndTime"
            type="datetime"
            placeholder="选择日期和时间"
            style="width: 525px;"
            disabled
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="培训地点" prop="trainingLocation" class="full-width">
          <el-input placeholder="请输入培训地点" v-model="form.trainingLocation" disabled></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="cancel">取消</el-button>
        <el-button type="primary" @click="submit">确定</el-button>
      </span>
    </el-dialog>
      <div class="manage-header">
        <el-button @click="handleAdd" type="primary" style="margin-top: -30px"> + 新建课程 </el-button>
        <!-- form搜索区域 -->
        <el-form :inline="true" :model="userForm">
          <el-form-item>
            <el-input placeholder="请输入课程筛选信息" v-model="userForm.name"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">查询</el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-refresh" @click="refresh">刷新</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div class="common-table">
        <el-table :data="tableData" style="width: 100%" height="740px" stripe>
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
                type="danger"
                size="mini"
                @click="handleDelete(scope.row)"
                >删除</el-button
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
  import { getCourse, addCourse, editCourse, delCourse } from "../api";
  export default {
    data() {
      return {
        dialogVisible: false,
        form: {
          name: '',
          company: '',
          executor: '',
          cost: '',
          plan: '',
          instructorName: '',
          instructorTitle: '',
          instructorField: '',
          instructorEmail: '',
          instructorPhone: '',
          trainingContent: '',
          trainingStartTime: '',
          trainingEndTime: '',
          trainingLocation: '',
  
        },
        rules: {
          name: [{ required: true, message: '请输入课程名'}],
          company: [{ required: true, message: '请输入软件公司'}],
          executor: [{ required: true, message: '请输入执行人姓名'}],
          cost: [{ required: true, message: '请输入培训费用'}],
          plan: [{ required: true, message: '请输入培训计划'}],
          instructorName: [{ required: true, message: '请输入讲师姓名'}],
          instructorTitle: [{ required: true, message: '请输入职称'}],
          instructorField: [{ required: true, message: '请输入擅长领域'}],
          instructorEmail: [{ required: true, message: '请输入Email'}],
          instructorPhone: [{ required: true, message: '请输入电话'}],
          trainingContent: [{ required: false, message: '请输入培训内容' }],
          trainingStartTime: [{ required: false, message: '请选择培训开始时间' }],
          trainingEndTime: [{ required: false, message: '请选择培训结束时间' }],
          trainingLocation: [{ required: false, message: '请输入培训地点' }],
        },
        tableData: [],
        modalType: 0, //0表示新增的弹窗，1表示编辑
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
            // 后续对表单数据的处理
            if (this.modalType === 0) {
              addCourse(this.form).then(() => {
                //重新获取列表的接口
                this.getList();
              });
            } else {
              editCourse(this.form).then(() => {
                //重新获取列表的接口
                this.getList();
              });
            }
            // 清空表单的数据
            this.$refs.form.resetFields();
            // 关闭弹窗
            this.dialogVisible = false;
          }
        });
      },
      // 弹窗关闭时
      handleClose() {
        //this.$refs.form.resetFields();
        this.dialogVisible = false;
      },
      cancel() {
        this.handleClose();
      },
      handleEdit(row) {
        this.modalType = 1;
        this.dialogVisible = true;
        // 注意需要对当前行数据进行深拷贝，否则会出错
        this.form = this.tableData.find((item) => item.id === row.id);
      },
      handleDelete(row) {
        this.$confirm("此操作将永久删除该课程, 是否继续?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
          .then(() => {
            delCourse({ id: row.id }).then(() => {
              this.$message({
                type: "success",
                message: "删除成功!",
              });
              // 重新获取列表的接口
              this.getList();
            });
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消删除",
            });
          });
      },
      handleAdd() {
        this.modalType = 0;
        this.dialogVisible = true;
        this.form = {}
      },
      getList() {
        // 获取的列表的数据
        getCourse({ params: { ...this.userForm, ...this.pageData } }).then(
          ({ data }) => {
            console.log("courseData",data)
            this.tableData = data.list;
            this.total = data.count || 0;
          }
        );
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
        this.getList()
      },
      getStatusType(status) {
        switch (status) {
          case '未开始':
            return 'danger';
          case '进行中':
            return 'success';
          case '已结束':
            return 'info';
          default:
            return '';
        }
    }
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
<!-- 使禁用的input颜色与不禁用一致 -->
<style lang="less" scoped>
.el-input.is-disabled /deep/ .el-input__inner {
  color: #606266;
  background-color: #f8f8f8;
}
</style>