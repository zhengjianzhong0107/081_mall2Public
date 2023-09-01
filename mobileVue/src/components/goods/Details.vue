<template>
  <el-container>
    <el-header>
      <Header />
    </el-header>
    <el-main>
      <el-card class="box-card">
        <el-page-header @back="goBack" content="详情页面"> </el-page-header>
        <el-row :gutter="10">
          <el-col :span="24">
            <div align="center"><h2 v-text="product.proName"></h2></div>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="12">
            <div align="center">
              <el-image
                :src="src"
                style="height: 160px;width: 160px"
                :preview-src-list="[product.image]"
              >
                <div slot="placeholder" class="image-slot">
                  加载中<span class="dot">...</span>
                </div>
              </el-image>
            </div>
            <div align="center" style="margin-top: 10px">
              <el-row :gutter="10">
                <el-col :span="10" align="right"
                  ><span>综合评分:</span>
                </el-col>
                <el-col :span="14" align="left"
                  ><span
                    ><el-rate
                      v-model="product.rate"
                      disabled
                      show-score
                      text-color="#ff9900"
                      score-template="{value}"
                    >
                    </el-rate></span
                ></el-col>
              </el-row>
            </div>
          </el-col>
          <el-col :span="12">
            <el-row :gutter="10">
              <el-col :span="8">
                颜色:
                <span class="size-border"
                  >&nbsp;&nbsp;{{ product.color }}&nbsp;&nbsp;</span
                >
              </el-col>
            </el-row>
            <el-row :gutter="10">
              <el-col :span="8">
                内存:
                <span class="size-border"
                  >&nbsp;&nbsp;{{ product.ram }}G&nbsp;&nbsp;</span
                >
              </el-col>
            </el-row>
            <el-row :gutter="10">
              <el-col :span="8">
                库存:
                <span class="size-border"
                  >&nbsp;&nbsp;{{ product.inventory }}&nbsp;&nbsp;</span
                >
              </el-col>
            </el-row>
            <el-row :gutter="10">
              <el-col :span="12">
                商品单价:
                <span class="size-border"
                  >&nbsp;&nbsp;{{ product.price }}元&nbsp;&nbsp;</span
                >
              </el-col>
            </el-row>
            <el-row :gutter="10">
              <el-col :span="12">
                销售量:
                <span class="size-border"
                  >&nbsp;&nbsp;{{ product.saleNum }}&nbsp;&nbsp;</span
                >
              </el-col>
            </el-row>
            <el-row :gutter="10">
              <el-col :span="16">
                描述:
                <span class="size-border"
                  >&nbsp;&nbsp;{{ product.message }}&nbsp;&nbsp;</span
                >
              </el-col>
              <el-col :span="8"> </el-col>
            </el-row>
            <div style="margin-top: 50px">
              <el-row :gutter="10">
                <el-col :span="6">
                  <span v-if="ifCol && this.$store.state.userName != null">
                    <el-button
                      type="danger"
                      @click="delCollect"
                      icon="el-icon-star-off"
                      >取消收藏</el-button
                    >
                  </span>
                  <span v-else>
                    <el-button
                      type="success"
                      @click="addCollect"
                      icon="el-icon-star-off"
                      >添加收藏</el-button
                    >
                  </span>
                </el-col>
                <el-col :span="6">
                  <el-button
                    type="primary"
                    @click="add"
                    icon="el-icon-edit-outline"
                    >我要评论</el-button
                  >
                </el-col>
                <el-col :span="6">
                  <el-button
                    type="primary"
                    icon="el-icon-circle-plus-outline"
                    @click="addCart"
                    >加入购物车</el-button
                  >
                </el-col>
                <el-col :span="6">
                  <el-button type="primary" @click="cartGo"
                    ><i
                      style="font-size:16px;color: gold"
                      class="el-icon-shopping-cart-1"
                    ></i
                    >去购物车</el-button
                  >
                </el-col>
              </el-row>
              <el-dialog
                title="添加评论"
                :visible.sync="addDialogVisible"
                width="30%"
                center
              >
                <div>
                  <el-form
                    label-position="left"
                    label-width="80px"
                    :model="rate"
                  >
                    <el-form-item label="商品评分">
                      <el-rate
                        style="margin-top: 10px"
                        v-model="rate.rate"
                        show-text
                      >
                      </el-rate>
                    </el-form-item>
                    <el-form-item label="评论内容">
                      <el-input
                        type="textarea"
                        :rows="2"
                        placeholder="请输入内容"
                        v-model="rate.content"
                      ></el-input>
                    </el-form-item>
                  </el-form>
                </div>
                <span slot="footer" class="dialog-footer">
                  <el-button @click="addDialogVisible = false">取 消</el-button>
                  <el-button type="primary" @click="addRate">确 定</el-button>
                </span>
              </el-dialog>
            </div>
          </el-col>
        </el-row>
      </el-card>
      <!--          评论-->
      <el-card class="box-card" style="margin-top: 10px">
        <el-row :gutter="10">
          <el-col :span="24"><h2>商品评价</h2></el-col>
        </el-row>
        <el-divider></el-divider>
        <div v-for="com in comment">
          <el-row>
            <el-col :span="1">
              <el-avatar :src="com.user.avatar"></el-avatar>
            </el-col>
            <el-col :span="3" align="left">
              <div class="font">{{ com.user.nickName }}</div>
              <div style="color: #ea6f5a">{{ com.dateComment }}</div>
            </el-col>
            <el-col :span="20" align="right">
              <div style="padding-top: 15px">
                <el-rate v-model="com.rate" disabled show-text> </el-rate>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24">
              <div>
                {{ com.content }}
              </div>
            </el-col>
          </el-row>
          <el-divider></el-divider>
        </div>
      </el-card>
      <!--          热卖商品卡片-->
      <el-card class="box-card card" style="margin-top: 10px">
        <el-row>
          <h2>热卖推荐</h2>
        </el-row>
        <el-row>
          <el-col
            :span="4"
            v-for="(r, index) in pops"
            :key="index"
            :offset="index % 5 != 0 ? 1 : 0"
            v-if="index < 5"
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
                    <span style="color: red;float:right">￥{{ r.price }}</span>
                  </div>
                </div>
              </el-card>
            </div>
          </el-col>
        </el-row>
      </el-card>
    </el-main>
    <el-footer>
      <Footer />
    </el-footer>
  </el-container>
</template>

<script>
import Footer from "../common/Footer";
import Header from "../common/Header";
import { mapActions } from "vuex"; //引入方法
export default {
  name: "Details",
  components: { Header, Footer },
  data() {
    return {
      //添加评论
      addDialogVisible: false,
      rate: {},
      //展示评论
      comment: [],
      src: "",
      product: {},
      pops: [],
      cart: {},
      //判断是否被收藏
      ifCol: "",
      //收藏
      collect: {
        pid: "",
        uid: ""
      }
    };
  },
  methods: {
    // 返回上一页
    goBack() {
      window.location.href = "javascript:history.go(-1)";
      console.log("go back");
    },
    //去购物车
    cartGo() {
      if (
        window.localStorage.getItem("userName") === null ||
        window.localStorage.getItem("userName") === ""
      ) {
        this.$message({
          type: "warning",
          message: "您还未登录，请先去登录",
          onClose: this.$router.push("/login")
        });
      } else {
        this.$router.push("/user/cart");
      }
    },
    // 加入购物车
    addCart() {
      if (
        window.localStorage.getItem("userName") === null ||
        window.localStorage.getItem("userName") === ""
      ) {
        this.$message({
          type: "warning",
          message: "您还未登录，请先去登录",
          onClose: this.$router.push("/login")
        });
      } else {
        this.cart.pid = this.$route.query.id;
        this.cart.uid = JSON.parse(this.$store.state.userName).id;
        this.cart.count = 1;
        this.cart.totalPrice = this.product.price;
        this.axios.post("/cart", this.cart).then(resp => {
          if (resp.data === "success") {
            this.$message({
              type: "success",
              message: "添加成功!"
            });
          } else {
            this.$message({
              type: "success",
              message: "添加成功！+1"
            });
          }
        });
      }

      // this.addGoods(this.product)
    },
    add() {
      if (
        this.$store.state.userName != null ||
        this.$store.state.userName !== ""
      ) {
        this.axios
          .get("/comment/if", {
            params: {
              id: JSON.parse(this.$store.state.userName).id,
              pid: this.product.id
            }
          })
          .then(resp => {
            console.log("状态" + resp.data);
            if (resp.data == "200") {
              this.addDialogVisible = true;
            } else if (resp.data == "400") {
              this.$message({
                type: "warning",
                message: "您还未购买此商品，暂不能评价"
              });
            } else if (resp.data == "300") {
              this.$message({
                type: "warning",
                message: "您已经评价过该商品啦！"
              });
            }
          });
      } else {
        this.$message({
          type: "warning",
          message: "您还未登录，请先去登录",
          onClose: this.$router.push("/login")
        });
      }
    },
    addRate() {
      this.rate.uid = JSON.parse(this.$store.state.userName).id;
      this.rate.pid = this.product.id;
      console.log(this.rate);
      this.axios.post("/comment", this.rate).then(resp => {
        if (resp.data === "success") {
          this.$message({
            type: "success",
            message: "评论成功"
          });
          this.addDialogVisible = false;
          this.loadComment();
        } else {
          this.$message({
            type: "error",
            message: "评论失败"
          });
        }
      });
    },
    loadComment() {
      this.axios({
        method: "get",
        url: "/product/detail",
        params: {
          id: this.$route.query.id
        }
      }).then(resp => {
        this.product = resp.data;
        this.src = resp.data.image;
        this.ifCollect();
        //加载评论
        this.axios({
          method: "get",
          url: "/comment/com",
          params: {
            pid: this.product.id
          }
        }).then(resp => {
          this.comment = resp.data;
        });
      });
    },
    hot() {
      //加载热卖商品
      this.axios({
        method: "get",
        url: "/product/pop"
      }).then(resp => {
        this.pops = resp.data;
      });
    },
    ifCollect() {
      this.collect.pid = this.product.id;
      this.collect.uid = JSON.parse(window.localStorage.getItem("userName")).id;
      this.axios({
        method: "get",
        url: "/collect/if?uid=" + this.collect.uid + "&pid=" + this.collect.pid
      }).then(resp => {
        console.log(resp.data);
        this.ifCol = resp.data;
      });
    },
    //添加收藏
    addCollect() {
      if (
        window.localStorage.getItem("userName") === null ||
        window.localStorage.getItem("userName") === ""
      ) {
        this.$message({
          type: "warning",
          message: "您还未登录，请先去登录",
          onClose: this.$router.push("/login")
        });
      } else {
        this.collect.uid = JSON.parse(this.$store.state.userName).id;
        this.collect.pid = this.product.id;
        this.axios.post("/collect", this.collect).then(resp => {
          if (resp.data === "success") {
            this.$message({
              type: "success",
              message: "收藏成功"
            });
            this.ifCollect();
          } else {
            this.$message({
              type: "error",
              message: "收藏失败"
            });
          }
        });
      }
    },
    //取消收藏
    delCollect() {
      console.log(this.product.id);
      this.collect.uid = JSON.parse(this.$store.state.userName).id;
      this.collect.pid = this.product.id;
      this.axios.delete("/collect", this.collect).then(resp => {
        if (resp.data === "success") {
          this.$message({
            type: "success",
            message: "取消成功"
          });
          this.ifCollect();
        } else {
          this.$message({
            type: "error",
            message: "取消失败"
          });
        }
      });
    },
    detail(i) {
      this.$router.push({ path: "/mobile/detail", query: { id: i } });
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
    this.loadComment();
    this.hot();
  }
};
</script>

<style scoped>
/*布局*/
.el-header {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  padding: 0px;
}

.el-header img {
  height: 50px;
  padding-top: 30px;
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

/*文字框*/
.size-border {
  border: solid 1px aquamarine;
  border-radius: 20px;
  background-color: #009688;
  color: #e9eef3;
}

/*行间距*/
.el-row {
  margin-bottom: 20px;
}

/*热卖推荐卡片*/
.card {
}

/*字体*/
.font {
  font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB",
    "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
}
</style>
