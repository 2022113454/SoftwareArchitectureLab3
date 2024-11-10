<template>
    <div class="manage">
      <el-dialog
        title="新增讲师"
        :visible.sync="dialogVisible"
        width="50%"
        :before-close="handleClose"
      >
        <!-- 用户表单信息 -->
        <el-form
          ref="form"
          :rules="rules"
          :inline="true"
          :model="form"
          label-width="80px"
        >
          <el-form-item label="姓名" prop="name">
            <el-input placeholder="请输入姓名" v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="职称" prop="title">
            <el-input placeholder="请输入职称" v-model="form.title"></el-input>
          </el-form-item>
          <el-form-item label="擅长领域" prop="major">
            <el-input placeholder="请输入擅长领域" v-model="form.major"></el-input>
          </el-form-item>
          <el-form-item label="Email" prop="email">
            <el-input placeholder="请输入Email" v-model="form.email"></el-input>
          </el-form-item>
          <el-form-item label="电话" prop="number">
            <el-input placeholder="请输入电话" v-model="form.number"></el-input>
          </el-form-item>
          <el-form-item label="工作状态" prop="status">
            <el-select v-model="form.status" placeholder="请选择工作状态">
              <el-option label="工作中" value="工作中"></el-option>
              <el-option label="空闲" value="空闲"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="cancel">取 消</el-button>
          <el-button type="primary" @click="submit">确 定</el-button>
        </span>
      </el-dialog>
      <div class="manage-header">
        <el-button @click="handleAdd" type="primary" style="margin-top: -30px"> + 新增讲师 </el-button>
        <!-- form搜索区域 -->
        <el-form :inline="true" :model="userForm">
          <el-form-item>
            <el-input placeholder="请输入筛选信息" v-model="userForm.name"></el-input>
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
        <el-table :data="tableData" style="width: 100%" height="740px"  stripe>
          <el-table-column prop="name" label="姓名"> </el-table-column>
          <el-table-column prop="title" label="职称"> </el-table-column>
          <el-table-column prop="major" label="擅长领域"> </el-table-column>
          <el-table-column prop="email" label="Email"> </el-table-column>
          <el-table-column prop="number" label="电话"> </el-table-column>
          <el-table-column prop="status" label="工作状态">
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
  import { getTeacher, addTeacher, editTeacher, delTeacher } from "../api";
  export default {
    data() {
      return {
        dialogVisible: false,
        form: {
          name: "",
          title: "",
          major: "",
          email: "",
          number: "",
          status: "",
        },
        rules: {
          name: [{ required: true, message: "请输入姓名" }],
          title: [{ required: true, message: "请输入职称" }],
          major: [{ required: true, message: "请输入擅长领域" }],
          email: [{ required: true, message: "请输入Email" }],
          number: [{ required: true, message: "请输入电话" }],
          status: [{ required: true, message: "请选择工作状态" }],
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
              addTeacher(this.form).then(() => {
                //重新获取列表的接口
                this.getList();
              });
            } else {
              editTeacher(this.form).then(() => {
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
        this.$refs.form.resetFields();
        this.dialogVisible = false;
      },
      cancel() {
        this.handleClose();
      },
      handleEdit(row) {
        this.modalType = 1;
        this.dialogVisible = true;
        // 注意需要对当前行数据进行深拷贝，否则会出错
        this.form = JSON.parse(JSON.stringify(row));
      },
      handleDelete(row) {
        this.$confirm("此操作将永久删除该讲师, 是否继续?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
          .then(() => {
            delTeacher({ name: row.name }).then(() => {
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
        this.resetForm('form')
      },
      getList() {
        // 获取的列表的数据
        getTeacher({ params: { ...this.userForm, ...this.pageData } }).then(
          ({ data }) => {
            console.log("teacher",data)
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
          case '工作中':
            return 'danger';
          case '空闲':
            return 'success';
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
  