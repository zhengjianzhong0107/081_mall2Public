<template>
  <div>
    <el-container style="height: 97vh">
      <el-header><Header /></el-header>
      <el-main>
        <el-row :gutter="10">
          <el-col :span="12" align="right">
            <img
              src="http://localhost:8081/assets/upload/paysuccess.png"
              alt="支付成功"
              style="width: 400px;height: 530px"
            />
          </el-col>
          <el-col :span="12">
            <div
              style="padding-top: 200px;margin-bottom:60px;font-size: 80px;font-family: 华文楷体;color: #67c23a"
            >
              支付成功
            </div>
            <div>
              <router-link to="/">
                <el-button type="success" style="margin-left: 20px"
                  >继续购物</el-button
                >
              </router-link>
              <router-link to="/user">
                <el-button style="margin-left: 60px">返回个人中心</el-button>
              </router-link>
            </div>
          </el-col>
        </el-row>
      </el-main>
      <el-footer><Footer /></el-footer>
    </el-container>
  </div>
</template>

<script>
import Header from "../common/Header";
import Footer from "../common/Footer";
export default {
  name: "Pay_Ok",
  components: { Footer, Header }
};
</script>

<style scoped>
.el-header {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  padding: 0px;
}
.el-main {
  /*#E9EEF3*/
  background-color: #e9eef3;
  color: #333;
  line-height: 20px;
}

.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
  font-size: 10px;
}
</style>
