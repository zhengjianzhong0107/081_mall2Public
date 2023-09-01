<template>
  <el-card class="box-card">
    <div style="font-size: 30px;color: #55a532" align="center">注册成功，快去登录吧！</div>
    <div style="margin-top: 50px" align="center">
    <router-link to="/login">
    <el-button type="primary">前去登录</el-button>
    </router-link>
    </div>
  </el-card>
</template>

<script>
    export default {

        name: "Register_Ok",
        created() {
            this.axios({
                method: "put",
                url: "/state",
            }).then(resp => {
                if (resp.data == "success") {
               this.$store.commit('add',3);
                    this.$message.success("注册激活成功");
                }else {
                    this.$message.error("注册激活失败，请重新注册");
                }
            })
        }
    }
</script>

<style scoped>

</style>
