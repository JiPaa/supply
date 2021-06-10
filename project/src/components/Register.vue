<template>
  <div class="register_container">
    <!--    登录块-->
    <div class="register_box">
      <!--      头像-->
      <div class="avatar_box">
        <img src="../assets/avatar.png">
      </div>
      <!--表单区域-->
      <el-form ref="registerFormRef" :model="registerForm" :rules="registerRules" class="register_form" label-width="0">
        <el-form-item prop="username">
          <el-input v-model="registerForm.username" prefix-icon="el-icon-user" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="registerForm.password" prefix-icon="el-icon-unlock" type="password" placeholder="密码"></el-input>
        </el-form-item>
        <el-form-item prop="email">
          <el-input v-model="registerForm.email" prefix-icon="el-icon-unlock" placeholder="邮箱"></el-input>
        </el-form-item>
        <el-form-item  class="btns">
          <el-button type="primary" @click="register()">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Register",
  data() {
    return {
      registerForm:{
        username:"",
        password:"",
        email:"",
        state:"3"
      },
      registerRules:{
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 5, max: 12, message: '长度在 5 到 12 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 12, message: '长度在 6 到 12 个字符', trigger: 'blur' }
        ],
      },
    }
  },
  methods:{
    register(){
      this.$refs.registerFormRef.validate(async valid =>{
        if(!valid) return;
        const {data:res} = await this.$http.post("register",this.registerForm);
        if(res=="success"){
          this.$message.success("注册成功!");
          this.$router.push({path:"/"})
        }
        else{
          this.$message.error("该用户名已存在!");
          this.$refs.registerFormRef.resetFields();
        }
      })
    },
  }
}
</script>
<style lang="less" scoped>
.register_container{
  background-color: cadetblue;
  height: 100%;
}
.register_box{
  width: 450px;
  height: 300px;
  background-color: #fff;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%,-50%);
  .avatar_box{
    width: 130px;
    height: 130px;
    border: 1px solid #eee;
    border-radius: 50%;
    padding: 5px;
    box-shadow: 0 0 5px #ddd;
    position: absolute;
    left: 50%;
    transform: translate(-50%,-50%);
    background-color: #0ee;
    img{
      width: 100%;
      height: 100%;
      border-radius: 50%;
      background-color: #eee;
    }
  }
}
.btns{
  display: flex;
  justify-content: center;
}
.register_form{
  position: absolute;
  bottom: 0%;
  width: 100%;
  padding: 0 10px;
  box-sizing: border-box;
}
</style>
