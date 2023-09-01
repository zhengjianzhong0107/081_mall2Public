<template>
  <el-container>
    <el-header>
      <Header />
    </el-header>
    <el-container>
      <el-aside width="200px">
        <el-menu
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          router
          background-color="#d3dce6"
        >
          <el-menu-item
            v-for="cate in cates"
            :index="'mobile/display?cid=' + cate.id.toString()"
            :key="cate.id"
          >
            <i class="el-icon-mobile"></i>
            <span slot="title">{{ cate.name }}</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <el-main>
          <!--          跑马灯&&用户-->
          <el-row :gutter="20">
            <el-col :span="16">
              <div class="grid-content bg-purple">
                <el-card :body-style="{ padding: '0px' }">
                  <el-carousel height="350px">
                    <el-carousel-item v-for="lb in lbs" :key="lb.id">
                      <a :href="lb.url"><img :src="lb.img" class="image"/></a>
                    </el-carousel-item>
                  </el-carousel>
                </el-card>
              </div>
            </el-col>
            <el-col :span="8">
              <!--                左侧-->
              <!-- <div
                style="height: 175px;background-color: #E9EEF3;"
                shadow="hover"
              >
                <img :src="yc.img" style="height:170px;padding: 1px;" />
              </div> -->

              <el-card
                class="box-card"
                :body-style="{ padding: '0px' }"
                style="margin-top: 5px"
                shadow="hover"
              >
                <div slot="header">
                  <span>消息通知</span>
                </div>
                <div v-for="tz in tzs" :key="tz.id" class="text item">
                  <el-button
                    type="text"
                    @click="window.location.href(tz.url)"
                    style="padding:0px;"
                    >{{ tz.title }}</el-button
                  >
                </div>
              </el-card>
            </el-col>
          </el-row>
          <!--网站公告区-->
          <el-row
            :gutter="2"
            style="border: solid 1px aquamarine; border-radius:20px;"
          >
            <!--            <el-col :span="24">-->
            <!--              <i class="el-icon-message-solid" style="text-align: left;"></i>-->
            <!--            </el-col>-->
            <el-col :span="1">
              <img
                src="../assets/laba.png"
                style="height: 30px; margin-outside: 5px;padding-top: 1px"
              />
            </el-col>
            <el-col :span="22" style="font-size: 20px">
              <div style="padding-top: 9px;font-family: 宋体;font-size: 16px">
                {{ gg.title }}
              </div>
            </el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="24">最新上架</el-col>
          </el-row>
          <!--          商品卡片-->
          <el-row>
            <el-col
              :span="4"
              v-for="(r, index) in newest"
              :key="index"
              :offset="index % 5 != 0 ? 1 : 0"
            >
              <div @click="detail(r.id)">
                <el-card
                  :body-style="{ padding: '0px' }"
                  shadow="hover"
                  style="cursor:pointer;margin-bottom: 15px;"
                >
                  <el-image
                    style="width: 258px; height: 150px;"
                    :src="r.image"
                    fit="contain"
                  ></el-image>

                  <!--                      <img :src="[r.image]" class="image">-->
                  <div style="padding: 14px;">
                    <div align="center">
                      <p>{{ r.proName }}</p>
                      <span style="color: #d3dce6">{{
                        r.message | ellipsis
                      }}</span>
                    </div>

                    <div class="bottom clearfix">
                      <span style="color: red;float:right"
                        >￥{{ r.price }}</span
                      >
                    </div>
                  </div>
                </el-card>
              </div>
            </el-col>
            <el-col :span="4" style="margin-left: 50px">
              <div @click="more">
                <el-card
                  :body-style="{ padding: '0px' }"
                  shadow="hover"
                  style="cursor:pointer;margin-bottom: 15px;"
                >
                  <el-image
                    style="width: 258px; height: 150px;"
                    :src="'http://localhost:8081/assets/upload/more.jpg'"
                    fit="contain"
                  ></el-image>
                  <!--                      <img :src="[r.image]" class="image">-->
                  <div style="padding: 14px;">
                    <div align="center">
                      <p>更多商品</p>
                      <span style="color: #d3dce6">获取更多</span>
                    </div>

                    <div class="bottom clearfix">
                      <span style="color: red;float:right"></span>
                    </div>
                  </div>
                </el-card>
              </div>
            </el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="24">商品热卖</el-col>
          </el-row>
          <!--          热卖商品卡片-->
          <el-row>
            <el-col
              :span="4"
              v-for="(r, index) in pops"
              :key="index"
              :offset="index % 5 != 0 ? 1 : 0"
            >
              <div @click="detail(r.id)">
                <el-card
                  :body-style="{ padding: '0px' }"
                  shadow="hover"
                  style="cursor:pointer;margin-bottom: 15px;"
                >
                  <el-image
                    style="width: 258px; height: 150px;"
                    :src="r.image"
                    fit="contain"
                  ></el-image>
                  <!--                      <img :src="[r.image]" class="image">-->
                  <div style="padding: 14px;">
                    <div align="center">
                      <p>{{ r.proName }}</p>
                      <span style="color: #d3dce6">{{
                        r.message | ellipsis
                      }}</span>
                    </div>

                    <div class="bottom clearfix">
                      <span style="color: red;float:right"
                        >￥{{ r.price }}</span
                      >
                    </div>
                  </div>
                </el-card>
              </div>
            </el-col>
            <el-col :span="4" style="margin-left: 50px">
              <div @click="more">
                <el-card
                  :body-style="{ padding: '0px' }"
                  shadow="hover"
                  style="cursor:pointer;margin-bottom: 15px;"
                >
                  <el-image
                    style="width: 258px; height: 150px;"
                    :src="'http://localhost:8081/assets/upload/more.jpg'"
                    fit="contain"
                  ></el-image>
                  <!--                      <img :src="[r.image]" class="image">-->
                  <div style="padding: 14px;">
                    <div align="center">
                      <p>更多商品</p>
                      <span style="color: #d3dce6">获取更多</span>
                    </div>

                    <div class="bottom clearfix">
                      <span style="color: red;float:right"></span>
                    </div>
                  </div>
                </el-card>
              </div>
            </el-col>
          </el-row>
        </el-main>
        <el-footer>
          <Footer />
        </el-footer>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
import Footer from "./common/Footer";
import Header from "./common/Header";
export default {
  name: "Home",
  components: { Header, Footer },
  data() {
    return {
      cates: [],
      newest: [],
      pops: [],
      lbs: [],
      tzs: [],
      gg: {},
      yc: {}
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    detail(i) {
      this.$router.push({ path: "mobile/detail", query: { id: i } });
    },
    more() {
      this.$router.push("/search?keyWord=");
    }
  },
  //添加监听器
  filters: {
    ellipsis(value) {
      const len = 10;
      if (!value) return "";
      if (value.length > len) {
        return value.slice(0, len) + "...";
      }
      return value;
    }
  },
  created() {
    //加载分类
    this.axios({
      method: "get",
      url: "/cate"
    }).then(resp => {
      this.cates = resp.data;
    });
    //加载最近商品
    this.axios({
      method: "get",
      url: "/product/newest"
    }).then(resp => {
      this.newest = resp.data;
    });
    //加载热卖商品
    this.axios({
      method: "get",
      url: "/product/pop"
    }).then(resp => {
      this.pops = resp.data;
    });
    //加载轮播图
    this.axios({
      method: "get",
      url: "/adv",
      params: {
        tj: "lb"
      }
    }).then(resp => {
      this.lbs = resp.data;
    });
    //加载通知
    this.axios({
      method: "get",
      url: "/adv",
      params: {
        tj: "tz"
      }
    }).then(resp => {
      this.tzs = resp.data;
    });
    //加载公告
    this.axios({
      method: "get",
      url: "/adv/one",
      params: {
        id: 5
      }
    }).then(resp => {
      this.gg = resp.data;
    });
    //加载右侧图
    this.axios({
      method: "get",
      url: "/adv/one",
      params: {
        id: 10
      }
    }).then(resp => {
      this.yc = resp.data;
    });
  }
};
</script>

<style scoped>
.el-header {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  padding: 0px;
}

.el-aside {
  /*#D3DCE6*/
  background-color: #d3dce6;
  color: #333;
  text-align: center;
  line-height: 200px;
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

/* 跑马灯*/
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

/*  用户中心卡片*/
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.car {
  padding: 0px;
}
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}

.box-card {
  width: 400px;
  height: 172px;
}

/*网站公告*/

.el-row {
  margin-bottom: 20px;
}

.borde {
  border-bottom-color: deepskyblue;
}
/*  商品卡片*/

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}
</style>
