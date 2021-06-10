<template>
  <div>
    <el-table
        :data="tableData"
        style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand" label-width="150px">
            <el-form-item label="供应商全称：">
              <span>{{ props.row.name }}</span>
            </el-form-item>
            <el-form-item label="采购部门人员：">
              <span>{{ props.row.market }}</span>
            </el-form-item>
            <el-form-item label="申请理由：">
              <span>{{ props.row.reason }}</span>
            </el-form-item>
            <el-form-item label="审批意见：">
              <el-input v-model="props.row.advice" type="textarea" :autosize="{ minRows: 3, maxRows: 10}"></el-input>
            </el-form-item>
            <el-form-item label="是否通过：">
              <el-button type="primary" @click="pass(props.row)">通过</el-button>
              <el-button type="primary" @click="refuse(props.row)">拒绝</el-button>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
          label="供应商全称"
          prop="name">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "Readblack",
  inject: ['reload'],
  data (){
    return{
      tableData:[
      ],
      postjs:{
        name:"",
        market: "",
        reason:"",
        advice:"",
        manage:"",
      }
    }
  },
  methods:{
    async getblckprocess(){
      const {data:res} = await this.$http.post("getblackprocess");
      this.tableData=res;
    },
    async pass(a){
      this.postjs=a;
      this.postjs.manage=JSON.parse(window.sessionStorage.getItem("user")).name;
      const {data:res} = await this.$http.post("passblack",this.postjs);
      this.$message.success("审批成功！");
      this.reload();
    },
    async refuse(a){
      this.postjs=a;
      this.postjs.manage=JSON.parse(window.sessionStorage.getItem("user")).name;
      const {data:res} = await this.$http.post("refuseblack",this.postjs);
      this.$message.success("审批成功！");
      this.reload();
    }
  },
  created() {
    this.getblckprocess();
  }
}
</script>

<style scoped>

</style>
