<template>
  <el-container class="home-container">
    <el-header>
      <div>
        <img src="../assets/yiyong.png" class="logo_img" />
        <span>供应商管理平台</span>
      </div>
      <el-button type="info" @click="logout">供应商安全退出</el-button>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <el-menu background-color="#545c64" text-color="#fff" active-text-color="#409eff" :router="true">
          <el-submenu index="1">
            <template slot="title">
              <span>功能菜单</span>
            </template>
            <el-menu-item index="infomation">
              <template slot="title">
                <span>个人信息</span>
              </template>
            </el-menu-item>
            <el-menu-item index="message3">
              <template slot="title">
                <span>系统通知</span>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: "ManageHome",
  data() {
    return {
    }
  },
  methods:{
    logout(){
      this.$router.push("/login");
      window.sessionStorage.clear();
    },
  },
}
</script>

<style lang="less" scoped>

.home-container{
  height: 100%;
}
.el-header {
  background-color: #373d41;
  display: flex;
  justify-content: space-between;
  padding-left: 0%;
  color: #fff;
  font-size: 20px;
  align-items: center;
  div{
    align-items: center;
    display: flex;
    span{
      margin-left: 15px;
    }
  }
}
.el-aside {
  background-color: #333744;
  .el-menu{
    border-right: none;// 对其右边框
  }
}
.el-main {
  background-color: #eaedf1;
}
.logo_img{
  width: 55px;
}
</style>
