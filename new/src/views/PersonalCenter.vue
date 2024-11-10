<template>
  <div class="center-form">
    <el-form
        ref="form"
        :rules="rules"
        :inline="false"
        :model="form"
        label-width="120px"
    >
      <h3 class="title">个人中心</h3>

      <el-form-item label="姓名" prop="name" class="full-width">
        <el-input
            placeholder="请填写姓名"
            v-model="form.name"
            disabled
        ></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="gender" class="full-width">
        <el-radio-group v-model="form.gender" disabled>
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
        <el-input placeholder="请填写联系电话" v-model="form.phone"></el-input>
      </el-form-item>
      <el-form-item label="电子邮箱" prop="email" class="full-width">
        <el-input placeholder="请填写电子邮箱" v-model="form.email"></el-input>
      </el-form-item>
      <el-form-item label="公司名称" prop="company" class="full-width">
        <el-input
            placeholder="请填写公司名称"
            v-model="form.company"
        ></el-input>
      </el-form-item>
      <el-form-item label="工作岗位" prop="position" class="full-width">
        <el-select v-model="form.position" placeholder="请选择工作岗位">
          <el-option label="软件工程师" value="软件工程师"></el-option>
          <el-option label="数据分析师" value="数据分析师"></el-option>
          <el-option label="质量保证工程师" value="质量保证工程师"></el-option>
          <el-option label="产品经理" value="产品经理"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="技术水平" prop="level" class="full-width">
        <el-select v-model="form.level" placeholder="请选择技术水平">
          <el-option label="萌新" value="萌新"></el-option>
          <el-option label="小成" value="小成"></el-option>
          <el-option label="高手" value="高手"></el-option>
          <el-option label="神" value="神"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item class="full-width">
        <el-button type="primary" style="margin-left: 230px" @click="onSubmit"
        >提交更改</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import { getPersonalCenter, editPersonnalCenter } from "../api";

export default {
  data() {
    return {
      form: {
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
      rules: {
        userType: [
          { required: true, message: "请输入用户类别", trigger: "blur" },
        ],
        name: [{ required: true, message: "请输入姓名", trigger: "blur" }],
        gender: [{ required: true, message: "请选择性别", trigger: "change" }],
        age: [{ required: true, message: "请输入年龄", trigger: "blur" }],
        phone: [{ required: true, message: "请输入联系电话", trigger: "blur" }],
        email: [
          { required: true, message: "请输入电子邮箱", trigger: "blur" },
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"],
          },
        ],
        company: [
          { required: true, message: "请输入公司名称", trigger: "blur" },
        ],
        position: [
          { required: true, message: "请选择工作岗位", trigger: "change" },
        ],
        level: [
          { required: true, message: "请选择技术水平", trigger: "change" },
        ],
      },
    };
  },
  methods: {
    fetchUserInfo() {
      this.form.userType = this.$store.state.tab.userType;
      getPersonalCenter({ params: { name: this.$store.state.tab.role } }).then(
          ({ data }) => {
            console.log(data,"dfgfgfgrgrgr")
            this.form = data;
          }
      );
    },
    onSubmit() {
      const {userType, ...filteredForm } = this.form;
      editPersonnalCenter(filteredForm).then(() => {
        this.fetchUserInfo;
        this.$message.success("信息更新成功");
      });
    },
  },
  mounted() {
    this.fetchUserInfo();
  },
};
</script>
<style lang="less" scoped>
.center-form {
  width: 100%;
  max-width: 800px;
  margin: -10px auto;
  padding: 30px;
  background-color: #ffffff;
  border-radius: 12px;
  box-shadow: 0 2px 15px rgba(0, 0, 0, 0.1);
}

.title {
  text-align: center;
  font-size: 1.8em;
  color: #333;
  margin-bottom: 20px;
}

.el-divider {
  margin: 20px 0;
  color: #409eff;
}

.el-form-item {
  margin-bottom: 25px;
}

.el-input,
.el-select {
  width: 100%;
}

.el-input__inner,
.el-select .el-input__inner {
  border-radius: 5px;
  border: 1px solid #dcdfe6;
  padding: 10px 15px;
}

.el-input__inner:focus,
.el-select .el-input__inner:focus {
  border-color: #409eff;
}

.el-input__inner:hover,
.el-select .el-input__inner:hover {
  border-color: #c0c4cc;
}

.el-input__inner,
.el-select .el-input__inner {
  background-color: #fff;
  transition: border-color 0.3s, box-shadow 0.3s;
}

.el-input__inner:focus,
.el-select .el-input__inner:focus {
  box-shadow: 0 0 8px rgba(64, 158, 255, 0.3);
}

.el-input::placeholder,
.el-select::placeholder {
  color: #b3c0d1;
}
</style>

<!-- 使禁用的input颜色与不禁用一致 -->
<style lang="less" scoped>
.el-input.is-disabled /deep/ .el-input__inner {
  color: #606266;
  background-color: #f8f8f8;
}
</style>
