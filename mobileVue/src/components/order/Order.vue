<template>
  <el-container>
    <el-header>
      <Header/>
    </el-header>
    <el-main>
      <el-card class="box-card">
        <el-page-header @back="goBack" content="订单">
        </el-page-header>
        <div style="margin-top: 30px">
          <el-row :gutter="10">
            <div style="width:60%; margin-left: 20%">
              <el-col :span="24">
                <el-steps :active="this.act" finish-status="success">
                  <el-step title="选择收货地址"></el-step>
                  <el-step title="订单生成"></el-step>
                </el-steps>

              </el-col>
            </div>
          </el-row>
          <router-view/>

        </div>
      </el-card>
    </el-main>
    <el-footer>
      <Footer/>
    </el-footer>
  </el-container>
</template>

<script>
    import Footer from "../common/Footer";
    import Header from "../common/Header";
    import {mapState} from "vuex";
    export default {
        name: "Order1",
        components: {Header, Footer},
        data() {
            return {
                active:0,
                selections: [],
            }
        },
        methods: {
            // 返回上一页
            goBack() {
                window.location.href = "javascript:history.go(-1)";
                console.log('go back');
            },


        },
        computed:{
            ...mapState(['act']),
        },

    }
</script>

<style scoped>
  /*布局*/
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

  /*行间距*/
  .el-row {
    margin-bottom: 20px;
  }
</style>

