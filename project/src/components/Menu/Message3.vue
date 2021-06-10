<template>
  <div>
    <el-card v-for="o in m" :key="o.message" style="width: 65%;margin-top: 10px">
      <div slot="header" class="clearfix">
        <span>系统通知</span>
        <el-button style="float: right; padding: 3px 0" type="text" @click="readmark(o)">已读</el-button>
      </div>
      <div class="text item">
        {{o.message}}
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Message3",
  inject: ['reload'],
  data() {
    return {
      nowUser:"",
      m:[],
    }
  },
  methods:{
    async getmessages(){
      this.nowUser=JSON.parse(window.sessionStorage.getItem("user"));
      const {data:res} = await this.$http.post("message",this.nowUser);
      this.m=res;
    },
    async readmark(a){
      console.log(a);
      const {data:res} = await this.$http.post("messagedelete",a);
      this.reload();
    }
  },
  created() {
    this.getmessages();
  }
}
</script>

<style scoped>

</style>
