<template>
  <el-container style="height: 98vh">
    <el-header><Header/></el-header>
    <el-main >

      <div style="margin-top: 20px;">
        <el-row :gutter="10">
          <el-col :span="6">&nbsp;</el-col>
            <el-col :span="6">
              <el-card class="box-card">
                <div style="height: 410px;">
                  <el-steps :active="this.active" direction="vertical" finish-status="success">
                    <el-step title="注册账号" description="请输入信息注册账号"></el-step>
                    <el-step title="激活账号" description="点击按钮发送邮件，邮件里的超链接进行激活"></el-step>
                    <el-step title="完成注册" description="注册完成，前去登陆吧"></el-step>
                  </el-steps>
                </div>
              </el-card>

            </el-col>

            <el-col :span="8">
              <router-view></router-view>
            </el-col>
        </el-row>
      </div>

    </el-main>
    <el-footer><Footer/></el-footer>
  </el-container>
</template>

<script>
    import Header from "../common/Header";
    import Footer from "../common/Footer";
    import {mapState} from 'vuex';
    export default {
        name: "Common",
        components: {Footer, Header},
        methods: {
            // 返回上一页
            goBack() {
                window.location.href = "javascript:history.go(-1)";
                console.log('go back');
            },

            },
        computed:{
           ...mapState(['active']),
        },

    }
</script>

<style scoped>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    padding:0px;
  }
  .el-main {
    /*#E9EEF3*/
    background-color: #E9EEF3;
    color: #333;
    line-height: 20px;
  }

  .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
    font-size: 10px;
  }
</style>
