<template>
  <el-card class="box-card">
    <div style="font-size: 30px;color: #df5000" align="center">点击按钮发送注册激活邮件</div>
    <div style="margin-top: 50px" align="center">
      <el-button type="primary" @click="activation">点击发送</el-button>
    </div>

  </el-card>
</template>

<script>
    export default {
        name: "Register_Active",
        methods: {
            activation() {
                this.axios({
                    method: "get",
                    url: "/mail",
                    params:{email:this.$store.state.email}
                }).then(resp => {
                    if (resp.data == "success") {

                      this.$store.commit('add',1);
                        this.$message.success("邮件发送成功");
                    }else {
                        this.$message.error("邮件发送失败");
                    }
                })
            }
        },

    }
</script>

<style scoped>

</style>
