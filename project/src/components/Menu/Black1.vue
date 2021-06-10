<template>
  <el-table
      :data="tableData"
      style="width: 100%">
    <el-table-column
        prop="name"
        label="供应商全称"
        width="420">
    </el-table-column>
    <el-table-column
        prop="market"
        label="申请采购人员"
        width="420">
    </el-table-column>
    <el-table-column
        prop="manage"
        label="审批管理人员">
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: "Black1",
  data () {
    return{
      tableData:[
        {
          username:"",
          name:"",
          market:"",
          manage:""
        },
      ],
    }
  },
  methods:{
    async getblack(){
      const {data:res} = await this.$http.post("getblack");
      this.tableData=res;
    }
  },
  created() {
    this.getblack();
  }
}
</script>

<style scoped>

</style>
