<template>
  <div class="head">
    <el-row :gutter="10">
      <el-col :span="14">
        <div align="left" style="margin-left: 15px">
          <a href="/"> <img class="img" :src="logo" alt="小米商城" /> </a>
        </div>
      </el-col>
      <el-col :span="8">
        <div style="margin-right: 10px;">
          <el-row :gutter="10">
            <el-col :span="12">
              <el-input
                @keyup.enter.native="searchKey"
                placeholder="请输入内容"
                v-model="input1"
                size="medium"
              >
                <el-button
                  slot="append"
                  icon="el-icon-search"
                  @click="searchKey"
                ></el-button>
              </el-input>
            </el-col>
            <el-col :span="2">
              &nbsp;
            </el-col>
            <el-col :span="10" align="right">
              <router-link to="/user/cart">
                <el-button type="text" circle size="medium"
                  >我的购物车</el-button
                >
              </router-link>
              <router-link to="/user/my_order">
                <el-button type="text" circle size="medium">我的订单</el-button>
              </router-link>
            </el-col>
          </el-row>
        </div>
      </el-col>
      <el-col :span="2">
        <div v-if="this.$store.state.userName != ''">
          <el-dropdown @command="handleCommand">
            <!--            <span class="el-dropdown-link" v-if="fakeHead!=null&&this.ifAva">-->
            <!--                            <el-avatar size="small"-->
            <!--                                       :src="fakeHead"-->
            <!--                                       style=" vertical-align: middle;">-->
            <!--             </el-avatar>-->
            <!--               <span> {{JSON.parse($store.state.userName).nickName}}<i-->
            <!--                 class="el-icon-arrow-down el-icon&#45;&#45;right"></i></span>-->
            <!--             </span>-->
            <span class="el-dropdown-link">
              <el-avatar
                size="small"
                :src="JSON.parse(this.$store.state.userName).avatar"
                style=" vertical-align: middle;"
              >
              </el-avatar>
              <span>
                {{ JSON.parse($store.state.userName).nickName
                }}<i class="el-icon-arrow-down el-icon--right"></i
              ></span>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="handleUser">个人中心</el-dropdown-item>
              <el-dropdown-item command="loginOut">退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>

        <div v-if="this.$store.state.userName === ''">
          <el-dropdown>
            <span class="el-dropdown-link">
              <el-avatar
                size="small"
                icon="el-icon-user-solid"
                style=" vertical-align: middle;"
              ></el-avatar>
              <span
                >未登录<i class="el-icon-arrow-down el-icon--left"></i
              ></span>
            </span>
            <el-dropdown-menu slot="dropdown" style="width: 200px;">
              <div align="center">
                <router-link to="/login">
                  <el-button type="primary" size="medium">登录</el-button>
                </router-link>
                <router-link to="/register">
                  <el-button type="primary" size="medium">注册</el-button>
                </router-link>
              </div>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { mapMutations } from "vuex";

export default {
  name: "Header",
  data() {
    return {
      logo: "",
      input1: "",
      user: {}
      // fakeHead:'',//为头像
      // ifAva:''
    };
  },
  computed: {
    username() {
      //  let username =JSON.parse(localStorage.getItem('userName'));
      let username = this.$store.state.userName;

      return username;
    }
  },
  methods: {
    ...mapMutations(["loginOut"]),
    ...mapMutations(["loginChange"]),
    handleCommand(command) {
      if (command == "loginOut") {
        this.loginOut();
        this.$message.success("退出成功");
        this.$router.replace("/login").catch(err => {
          console.log(err);
        });
      } else if (command == "handleUser") {
        this.$router.push("/user").catch(err => {
          console.log(err);
        });
      }
    },
    searchKey() {
      this.$router.push("/search?keyWord=" + this.input1);
    },

    sys() {
      this.axios.get("/sys").then(resp => {
        this.logo = resp.data.logo;
      });
    }
  },
  created() {
    this.sys();

    //console.log('用户头像：'+JSON.parse(this.$store.state.userName).avatar)
    //let urlHost = process.env.API_HOST // 会自动判断当前是开发还是生产环境，然后自动匹配API_HOST
  }
};
</script>

<style scoped>
.head {
  font-size: 30px;
  line-height: 60px;
  justify-content: space-between;
  align-items: center;
}

.img {
  height: 50px;
  padding-top: 5px;
}

.el-dropdown-link {
  cursor: pointer;
  color: #409eff;
}

.el-icon-arrow-down {
  font-size: 12px;
}
</style>
