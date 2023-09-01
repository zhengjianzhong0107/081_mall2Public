<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span style="font-size: 20px;color: #42b983;">用户注册</span>
        <el-button
          style="float: right; padding: 3px 0"
          type="text"
          @click="toLogin"
          >已有账号？前去登录</el-button
        >
      </div>
      <div>
        <el-form
          :model="reg"
          :rules="rules"
          ref="reg"
          label-width="100px"
          class="demo-reg"
        >
          <el-form-item label="注册账号" prop="email">
            <el-input v-model="reg.email"></el-input>
          </el-form-item>
          <el-form-item label="注册密码" prop="pwd">
            <el-input
              type="password"
              show-password
              v-model="reg.pwd"
            ></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="pwd1">
            <el-input
              type="password"
              show-password
              v-model="reg.pwd1"
            ></el-input>
          </el-form-item>
          <el-row :gutter="10">
            <el-form-item label="验证码" prop="number">
              <el-col :span="12">
                <el-input v-model="reg.number"> </el-input>
              </el-col>
              <el-col :span="12"
                ><img :src="imgSrc" /><br />
                <el-button type="text" @click="change"
                  >看不清楚？换个图片</el-button
                >
              </el-col>
            </el-form-item>
          </el-row>

          <el-form-item style="margin-top: 5px">
            <el-button type="primary" @click="submitForm('reg')"
              >立即注册</el-button
            >
            <el-button @click="resetForm('reg')">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
import Footer from "../common/Footer";
import { mapMutations } from "vuex";
export default {
  name: "Register",
  components: { Footer },
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.reg.pwd) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      imgSrc: "http://localhost:8081/handleYam",
      val: false,
      reg: {
        email: "",
        pwd: "",
        pwd1: "",
        number: ""
      },
      rules: {
        email: [
          { required: true, message: "账号不能为空" },
          {
            type: "string",
            message: "邮箱格式不正确",
            trigger: "blur",
            transform(value) {
              if (
                !/^\w+((-\w+)|(\.\w+))*@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test(
                  value
                )
              ) {
                return true;
              } else {
              }
            }
          },
          {
            type: "string",
            message: "长度不能超过30位",
            trigger: "blur",
            max: 30
          }
        ],
        pwd: [
          { required: true, message: "输入密码", trigger: "blur" },
          {
            type: "string",
            message: "密码长度应保持在6-16之间",
            trigger: "blur",
            min: 6,
            max: 16
          }
        ],
        pwd1: [{ required: true, trigger: "blur", validator: validatePass }],
        number: [{ required: true, trigger: "blur", message: "请输入验证码" }]
      }
    };
  },
  methods: {
    ...mapMutations(["add"]),
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.axios({
            method: "get",
            url: "/user/reg",
            params: {
              email: this.reg.email,
              pwd: this.reg.pwd,
              pwd1: this.reg.pwd1,
              number: this.reg.number
            }
          }).then(resp => {
            if (resp.data == "success") {
              // this.$store.commit('add',1);
              this.add(1);
              alert("注册成功");
              this.$store.commit("emailChange", this.reg.email);
              this.$router.push("/reg_active");
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    //切换验证码
    change() {
      this.imgSrc = "http://localhost:8081/handleYam?a=" + Math.random();
    },

    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    toLogin() {
      this.$router.push("/login");
    }
  },
  created() {
    window.sessionStorage.setItem("myActive", 0);
  }
};
</script>

<style scoped>
.el-main {
  /*#E9EEF3*/
  background-color: #e9eef3;
  color: #333;
  line-height: 20px;
}
</style>
