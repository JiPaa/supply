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
            <el-form-item label="采购部门意见：">
              <span>{{ props.row.marketadvice }}</span>
            </el-form-item>
            <el-form-item label="审批意见：">
              <el-input v-model="props.row.manageadvice" type="textarea" :autosize="{ minRows: 3, maxRows: 10}"></el-input>
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
  name: "Read1",
  inject: ['reload'],
  data (){
    return{
      tableData:[
      ],
      postjs:{
        username:"",
        manageadvice:"",
        name:"",
        classify:"",
        brand:"",
        range:"",
        production:"",
        person:"",
        tel:"",
        email:"",
        money:"",
        state:"",
        marketadvice:"",
        marketpass:"",
        managepass:""
      }
    }
  },
  methods:{
    async getread(){
      const {data:res} = await this.$http.post("readall1");
      this.tableData=res;
    },
    async pass(a){
      console.log(a);
      this.postjs=a;
      const {data:res} = await this.$http.post("pass1",this.postjs);
      this.$message.success("审批成功！");
      this.reload();
    },
    async refuse(a){
      this.postjs=a;
      const {data:res} = await this.$http.post("refuse1",this.postjs);
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
