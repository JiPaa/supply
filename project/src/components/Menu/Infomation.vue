<template>
<div>
  <el-form ref="infomationForm" :model="infomationForm" label-width="80px" style="width: 65%">
    <el-form-item label="用户名" label-width="100px">
      <el-input v-model="infomationForm.username" :disabled="true"></el-input>
    </el-form-item>
    <el-form-item label="供应商全称" label-width="100px">
      <el-input v-model="infomationForm.name"></el-input>
    </el-form-item>
    <el-form-item label="供应商类别" label-width="100px">
      <el-input v-model="infomationForm.classify"></el-input>
    </el-form-item>
    <el-form-item label="经营品牌" label-width="100px">
      <el-input v-model="infomationForm.brand"></el-input>
    </el-form-item>
    <el-form-item label="经营范围" label-width="100px">
      <el-input v-model="infomationForm.range"></el-input>
    </el-form-item>
    <el-form-item label="经营产品" label-width="100px">
      <el-input v-model="infomationForm.production"></el-input>
    </el-form-item>
    <el-form-item label="注册资本" label-width="100px">
      <el-input v-model="infomationForm.money"></el-input>
    </el-form-item>
    <el-form-item label="联系人姓名" label-width="100px">
      <el-input v-model="infomationForm.person"></el-input>
    </el-form-item>
    <el-form-item label="联系电话" label-width="100px">
      <el-input v-model="infomationForm.tel"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" label-width="100px">
      <el-input v-model="infomationForm.email"></el-input>
    </el-form-item>
  </el-form>
  <div>
      <el-button type="primary" @click="formalregister()">{{buttonText}}</el-button>
  </div>
</div>
</template>

<script>
export default {
  name: "Infomation",
  data() {
    return {
      buttonText:"",
      nowUser:{},
      infomationForm:{
        name:"",
        username:"",
        classify:"",
        brand:"",
        range:"",
        production:"",
        person:"",
        tel:"",
        email:"",
        money:""
      },
    }
  },
  methods:{
    async getInfomation(){
      this.nowUser=JSON.parse(window.sessionStorage.getItem("user"));
      const {data:res} = await this.$http.post("getuser",this.nowUser);
      this.nowUser=res;
      if(this.nowUser.state=="3"){
        this.infomationForm.username=this.nowUser.username;
        this.infomationForm.email=this.nowUser.email;
        this.buttonText="正式注册";
      }
      else if(this.nowUser.state=="4"){
        const {data:res2} = await this.$http.post("file",this.nowUser);
        this.infomationForm=res2.file;
        this.buttonText="修改信息";
      }
    },
    async formalregister(){
      const {data:res} = await this.$http.post("fomal",this.infomationForm);
      if(res=="success"){
        this.$message.success("提交成功，请等待审核。");
      }
      else if(res=="error"){
        this.$message.error("提交失败，请等待上一次审核结束。");
      }
    }
  },
  created() {
    this.getInfomation();
  }
}
</script>

<style scoped>

</style>
