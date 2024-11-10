<template>
  <div class="manage">
    <el-dialog
      title="学员信息"
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
        <el-form-item label="性别" prop="sex">
          <el-select v-model="form.sex" placeholder="请选择" disabled>
            <el-option label="男" value="1"></el-option>
            <el-option label="女" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="公司名称" prop="company">
          <el-input
            placeholder="请输入公司名称"
            v-model="form.company"
          ></el-input>
        </el-form-item>
        <el-form-item label="工作岗位" prop="duty">
          <el-select v-model="form.duty" placeholder="请选择工作岗位">
            <el-option label="软件工程师" value="软件工程师"></el-option>
            <el-option label="数据分析师" value="数据分析师"></el-option>
            <el-option
              label="质量保证工程师"
              value="质量保证工程师"
            ></el-option>
            <el-option label="产品经理" value="产品经理"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="技术水平" prop="level">
          <el-select v-model="form.level" placeholder="请选择技术水平">
            <el-option label="萌新" value="萌新"></el-option>
            <el-option label="小成" value="小成"></el-option>
            <el-option label="高手" value="高手"></el-option>
            <el-option label="神" value="神"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Email" prop="email">
          <el-input placeholder="请输入Email" v-model="form.email"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="submit">确 定</el-button>
      </span>
    </el-dialog>
    <div class="manage-header">
      <el-select v-model="value" placeholder="课程筛选">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.value"
          :value="item.value"
        >
        </el-option>
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
        <el-table-column prop="name" label="姓名"> </el-table-column>
        <el-table-column prop="sex" label="性别"> </el-table-column>
        <el-table-column prop="company" label="公司名称"> </el-table-column>
        <el-table-column prop="duty" label="工作岗位"> </el-table-column>
        <el-table-column prop="level" label="技术水平"></el-table-column>
        <el-table-column prop="email" label="Email"></el-table-column>
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
import { getCourseOfExe, getUser, editUser, delUser } from "../api";
export default {
  data() {
    return {
      options: [],
      value: "",
      dialogVisible: false,
      form: {
        name: "",
        sex: "",
        company: "",
        duty: "",
        level: "",
        email: "",
      },
      rules: {
        name: [{ required: true, message: "请输入姓名" }],
        sex: [{ required: true, message: "请选择性别" }],
        company: [{ required: true, message: "请输入公司名称" }],
        duty: [{ required: true, message: "请选择工作岗位" }],
        level: [{ required: true, message: "请选择技术水平" }],
        email: [{ required: true, message: "请输入Email" }],
      },
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
    //提交用户表单
    submit() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          console.log(this.form);
          editUser(this.form).then(() => {
            //重新获取列表的接口
            this.getList();
          });
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
      this.dialogVisible = true;
      // 注意需要对当前行数据进行深拷贝，否则会出错
      this.form = JSON.parse(JSON.stringify(row));
    },
    handleDelete(row) {
      this.$confirm("此操作将永久删除该学员, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          delUser({ course: this.value, name: row.name }).then(() => {
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
    getList() {
      console.log(this.$store.state.tab.role, "当前角色");
      // 获取课程数据
      getCourseOfExe({
        params: {
          ...this.userForm,
          ...this.pageData,
          role: this.$store.state.tab.role,
        },
      }).then(({ data }) => {
        this.courseData = data.list;
        this.updateOptions();
      });
      // 获取用户数据
      getUser({
        params: {
          ...this.userForm,
          ...this.pageData,
          role: this.$store.state.tab.role,
          course: this.value,
        },
      }).then(({ data }) => {
        this.tableData = data.list;
        this.tableData = data.list.filter(
          (item) => (item.sex = item.sex == 1 ? "男" : "女")
        );
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
    updateOptions() {
      this.options = this.courseData.map((course) => ({
        value: course.name,
      }));
    },
    // 列表的查询
    onSubmit() {
      this.getList();
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
