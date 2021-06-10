<template>
  <el-table
      :data="tableData"
      style="width: 100%">
    <el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
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
</template>

<script>
export default {
  name: "Allsup2",
  data () {
    return{
      tableData:[
      {
          name:"",
          username:"",
          classify:"",
          brand:"",
          range:"",
          production:"",
          person:"",
          tel:"",
          email:"",
          money:"",
        },
      ],
    }
  },
  methods:{
    async getallfiles(){
      const {data:res} = await this.$http.post("allfile");
      this.tableData=res;
    }
  },
  created() {
    this.getallfiles();
  }
}
</script>

<style>
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 100px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 90%;
}
</style>

