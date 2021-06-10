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
          <el-form-item label="供应商类别：">
            <span>{{ props.row.classify }}</span>
          </el-form-item>
          <el-form-item label="经营品牌：">
            <span>{{ props.row.brand }}</span>
          </el-form-item>
          <el-form-item label="经营范围：">
            <span>{{ props.row.range }}</span>
          </el-form-item>
          <el-form-item label="经营产品：">
            <span>{{ props.row.production }}</span>
          </el-form-item>
          <el-form-item label="注册资本：">
            <span>{{ props.row.money }}</span>
          </el-form-item>
          <el-form-item label="联系人姓名：">
            <span>{{ props.row.person }}</span>
          </el-form-item>
          <el-form-item label="联系电话：">
            <span>{{ props.row.tel }}</span>
          </el-form-item>
          <el-form-item label="邮箱：">
            <span>{{ props.row.email }}</span>
          </el-form-item>
          <el-form-item label="审批意见：">
            <el-input v-model="props.row.marketadvice" type="textarea" :autosize="{ minRows: 3, maxRows: 10}"></el-input>
          </el-form-item>
          <el-form-item label="是否通过：">
            <el-button type="primary" @click="pass(props.row.username,props.row.marketadvice)">通过</el-button>
            <el-button type="primary" @click="refuse(props.row.username,props.row.marketadvice)">拒绝</el-button>
          </el-form-item>
        </el-form>
      </template>
    </el-table-column>
    <el-table-column
        label="供应商全称"
        prop="name">
    </el-table-column>
    <el-table-column
        label="经营品牌"
        prop="brand">
    </el-table-column>
    <el-table-column
        label="经营范围"
        prop="range">
    </el-table-column>
  </el-table>
</div>
</template>

<script>
export default {
  name: "Read2",
  inject: ['reload'],
  data (){
    return{
      supname:"",
      advice:"",
      tableData:[
      ],
      postjs:{
        username:"",
        marketadvice:"",
      }
    }
  },
  methods:{
    async getread(){
      const {data:res} = await this.$http.post("readall2");
      this.tableData=res;
    },
    async pass(a,b){
      this.postjs.username=a;
      this.postjs.marketadvice=b;
      const {data:res} = await this.$http.post("pass2",this.postjs);
      this.$message.success("审批成功！");
      this.reload();
    },
    async refuse(a,b){
      this.postjs.username=a;
      this.postjs.marketadvice=b;
      const {data:res} = await this.$http.post("refuse2",this.postjs);
      this.$message.success("审批成功！");
      this.reload();
    }
  },
  created() {
    this.getread();
  }
}
</script>

<style scoped>

</style>
