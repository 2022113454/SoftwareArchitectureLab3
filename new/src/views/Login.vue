<template>
  <el-form
    label-width="70px"
    :inline="true"
    class="login-container"
    :model="form"
    :rules="rules"
    ref="form"
  >
    <h3 class="login_title">HQ技术培训管理系统</h3>
    <el-form-item label="用户名" prop="username">
      <el-input v-model="form.username" placeholder="请输入账号"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input
        type="password"
        v-model="form.password"
        placeholder="请输入密码"
      ></el-input>
    </el-form-item>
    <el-form-item label="身份" prop="role" >
      <el-select v-model="form.role" placeholder="请选择身份">
        <el-option label="经理" value="manager"></el-option>
        <el-option label="执行人" value="executor"></el-option>
        <el-option label="学员" value="student"></el-option>
        <el-option label="工作人员" value="staff"></el-option>
        <el-option label="软件公司" value="company"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item>
      <el-button
        @click="submit"
        style="margin-left: 120px; margin-top: 10px"
        type="primary"
        >登录</el-button
      >
    </el-form-item>
  </el-form>
</template>
<script>
import Mock from "mockjs";
import Cookie from "js-cookie";
import { getMenu } from "../api";
export default {
  data() {
    return {
      form: {
        username: "",
        password: "",
        role: "", // 身份字段
      },
      rules: {
        username: [
          { required: true, trigger: "blur", message: "请输入用户名" },
        ],
        password: [{ required: true, trigger: "blur", message: "密码" }],
        role: [{ required: true, trigger: "change", message: "请选择身份" }], // 添加身份验证规则
      },
    };
  },
  methods: {
    //登录
    submit() {
      // 校验通过
      this.$refs.form.validate((valid) => {
        if (valid) {
          getMenu(this.form).then(({ data }) => {
            console.log(data);
            if (data.code === 20000) {
              // token信息存入cookie用于不同页面间的通信
              Cookie.set("token", data.token);
              // 获取菜单的数据，存入store中
              this.$store.commit("setUserName", this.form.username);
              this.$store.commit("setUserType", this.form.role);
              this.$store.commit('setMenu',this.form.role)
              this.$store.commit('addMenu', this.$router)
              this.$store.commit('setRole',data.name)
              console.log("用户姓名",this.$store.state.tab.role)
              // 跳转到首页
              this.$router.push("/home");
            } else {
              this.$message.error(data.message);
            }
          });
        }
      });
    },
  },
};
</script>
<style lang="less" scoped>
.login-container {
  width: 400px;
  border: 1px solid #eaeaea;
  margin: 180px auto;
  padding: 35px 35px 15px 35px;
  background-color: #fff;
  border-radius: 15px;
  box-shadow: 0 0 15px #cac6c6;
  box-sizing: border-box;
  .login_title {
    text-align: center;
    margin-bottom: 40px;
    color: #505458;
  }
  .el-input {
    width: 221px;
  }
}
</style>
