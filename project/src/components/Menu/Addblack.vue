<template>
 <div>
  <el-form ref="addBlackForm" :model="addBlackForm" :rules="AddRules" label-width="80px" style="width: 65%">
    <el-form-item label="供应商全称" label-width="100px" prop="supply">
      <el-input v-model="addBlackForm.supply"></el-input>
    </el-form-item>
    <el-form-item label="申请理由" label-width="100px" prop="reason">
      <el-input v-model="addBlackForm.reason" type="textarea" :autosize="{ minRows: 3, maxRows: 10}"></el-input>
    </el-form-item>
  </el-form>
  <div>
    <el-button type="primary" @click="submitblack()">提交</el-button>
  </div>
 </div>
</template>

<script>
export default {
  name: "Addblack",
  data() {
    return{
      addBlackForm:{
        supply:"",//该supply是填的供应商全称
        reason:"",
      },
      nowUser:"",
      realBlackForm:{
        supply:"",//该supply是经过函数转化为供应商用户名
        market:"",
        reason:"",
      },
      AddRules:{
        supply: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
        ],
        reason: [
          { required: true, message: '请输入申请理由', trigger: 'blur' },
        ],
      },
    }
  },
  methods:{
    getnowuser(){
      this.nowUser=JSON.parse(window.sessionStorage.getItem("user"));
    },
    submitblack(){
      this.$refs.addBlackForm.validate(async valid => {
        if (!valid) return;
        const {data:res} = await this.$http.post("nametousername",this.addBlackForm);
        if(res=="error"){
          this.$message.error("无此供应商！");
          return;
        }
        this.realBlackForm.supply=res;
        this.realBlackForm.market=this.nowUser.name;
        this.realBlackForm.reason=this.addBlackForm.reason;
        const {data:res2} = await this.$http.post("insertblackprocess",this.realBlackForm);
        if(res2=="success"){
          this.$message.success("申请成功！");
          this.$refs.addBlackForm.resetFields();
        }
        else{
          this.$message.error("请勿重复申请！");
        }
      })
    }
  },
  created() {
    this.getnowuser();
  }
}
</script>

<style scoped>

</style>
