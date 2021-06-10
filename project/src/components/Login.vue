<template>
  <div class="login_container">
<!--    登录块-->
    <div class="login_box">
<!--      头像-->
      <div class="avatar_box">
        <img src="../assets/avatar.png">
      </div>
<!--表单区域-->
      <el-form ref="loginFormRef" :model="loginForm" :rules="loginRules" class="login_form" label-width="0">
        <el-form-item prop="username">
          <el-input v-model="loginForm.username" prefix-icon="el-icon-user" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="loginForm.password" prefix-icon="el-icon-unlock" type="password" placeholder="密码" @keypress.native.enter="login()"></el-input>
        </el-form-item>
        <el-form-item  class="btns">
          <el-button type="primary" @click="login()">登录</el-button>
          <el-button type="info" @click="goRegister()">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      loginForm:{
        username:"",
        password:""
      },
      loginRules:{
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
    goRegister(){
      this.$router.push({path:"/register"});
    },
    login(){
      this.$refs.loginFormRef.validate(async valid =>{
        if(!valid) return;
        const {data:res} = await this.$http.post("login",this.loginForm);
        if (res.flag!= "ok"){
          this.$message.error("用户名或密码错误!");
        }else if (res.user.state==1){
          this.$message.success("登录成功!");
          window.sessionStorage.setItem("user",JSON.stringify(res.user));
          this.$router.push({path:"/manageHome"});
        }
        else if (res.user.state==2){
          this.$message.success("登录成功!");
          window.sessionStorage.setItem("user",JSON.stringify(res.user));
          this.$router.push({path:"/marketHome"});
        }
        else if (res.user.state==3){
          this.$message.success("登录成功!");
          window.sessionStorage.setItem("user",JSON.stringify(res.user));
          this.$router.push({path:"/supplyHome"});
        }
        else if (res.user.state==4){
          this.$message.success("登录成功!");
          window.sessionStorage.setItem("user",JSON.stringify(res.user));
          this.$router.push({path:"/supplyHome"});
        }
      })
    },
  },
  created() {
    window.sessionStorage.clear();
  }
}
</script>
<style lang="less" scoped>
.login_container{
  background-color: #2b4b6b;
  height: 100%;
}
.login_box{
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
  justify-content: flex-end;
}
.login_form{
  position: absolute;
  bottom: 0%;
  width: 100%;
  padding: 0 10px;
  box-sizing: border-box;
}
</style>
