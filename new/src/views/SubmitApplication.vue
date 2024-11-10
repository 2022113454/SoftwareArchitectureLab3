<template>
  <div class="form-container">
    <h2 class="form-title">培训申请</h2>
    <el-form ref="form" :model="form" :rules="rules" label-width="120px" class="demo-ruleForm">
      <el-form-item label="公司名称" prop="company">
        <el-input v-model="form.company" placeholder="请输入公司名称"></el-input>
      </el-form-item>
      <el-form-item label="申请人姓名" prop="applicant">
        <el-input v-model="form.applicant" placeholder="请输入申请人姓名"></el-input>
      </el-form-item>
      <el-form-item label="Email" prop="email">
        <el-input v-model="form.email" placeholder="请输入Email"></el-input>
      </el-form-item>
      <el-form-item label="培训主题" prop="topic">
        <el-input v-model="form.topic" placeholder="请输入培训主题"></el-input>
      </el-form-item>
      <el-form-item label="培训时间" prop="date">
        <el-date-picker v-model="form.date" type="date" placeholder="选择日期"></el-date-picker>
      </el-form-item>
      <el-form-item label="培训内容" prop="content">
        <el-input type="textarea" v-model="form.content" placeholder="请输入培训内容"></el-input>
      </el-form-item>
      <el-form-item label="培训规模(人数)" prop="scale">
        <el-input-number v-model="form.scale" :min="1" :max="1000" label="人数"></el-input-number>
      </el-form-item>
      <el-form-item label="备注" prop="remarks">
        <el-input type="textarea" v-model="form.remarks" placeholder="请输入备注"></el-input>
      </el-form-item>
      <el-form-item class="form-buttons">
        <el-button type="primary" @click="submitForm('form')">提交申请</el-button>
        <el-button @click="resetForm('form')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { submitApplication } from "../api" ;
export default {
  data() {
    return {
      form: {
        company: '',
        applicant: '',
        email: '',
        topic: '',
        date: '',
        content: '',
        scale: 1,
        remarks: ''
      },
      rules: {
        company: [
          { required: true, message: '请输入公司名称', trigger: 'blur' }
        ],
        applicant: [
          { required: true, message: '请输入申请人姓名', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入Email', trigger: 'blur' },
          { type: 'email', message: '请输入有效的Email地址', trigger: ['blur', 'change'] }
        ],
        topic: [
          { required: true, message: '请输入培训主题', trigger: 'blur' }
        ],
        date: [
          { required: true, message: '请选择培训时间', trigger: 'change' }
        ],
        content: [
          { required: true, message: '请输入培训内容', trigger: 'blur' }
        ],
        scale: [
          { required: true, message: '请输入培训规模', trigger: 'blur' }
        ],
        remarks: [
          { required: false, message: '请输入备注', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          submitApplication(this.form).then(() => {
            this.$message({
              message: '申请提交成功',
              type: 'success'
            });
          });
        } else {
          console.log('提交失败');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
};
</script>

<style scoped>
.form-container {
  max-width: 700px;
  margin: -15px auto;
  padding: 20px;
  background: #f5f5f5;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  font-family: 'Arial', sans-serif;
}

.form-title {
  text-align: center;
  margin-bottom: 20px;
  font-size: 24px;
  color: #333;
}

.demo-ruleForm {
  background: #ffffff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.el-form-item {
  margin-bottom: 20px;
}

.el-input, .el-input-number, .el-date-picker, .el-time-picker {
  width: 100%;
}

.el-button {
  width: 48%;
}
</style>
