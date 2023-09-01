<template>
  <el-container>
    <el-header>
      <Header />
    </el-header>
    <el-main>
      <el-card class="box-card">
        <el-page-header @back="goBack" content="我的购物车"> </el-page-header>
        <div style="margin-top: 30px">
          <el-row :gutter="10">
            <el-col :span="24">
              <div align="center">
                <el-table
                  :data="cart"
                  style="width: 70%"
                  size="mini"
                  @selection-change="handleSelectionChange"
                >
                  <el-table-column
                    header-align="center"
                    align="center"
                    type="selection"
                  >
                  </el-table-column>
                  <el-table-column label="商品图片" width="180">
                    <template slot-scope="scope">
                      <el-image
                        style="width: 100px; height: 100px"
                        :src="scope.row.product.image"
                        fit="scale-down"
                        :preview-src-list="[scope.row.product.image]"
                      ></el-image>
                    </template>
                  </el-table-column>
                  <el-table-column
                    prop="product.proName"
                    label="商品名称"
                    width="180"
                  >
                  </el-table-column>
                  <el-table-column
                    prop="product.price"
                    label="单价"
                    width="100"
                  >
                  </el-table-column>
                  <el-table-column label="数量" width="180">
                    <template slot-scope="scope">
                      <el-input-number
                        v-model="scope.row.count"
                        style="width: 100px;"
                        @change="
                          (oldval, newval) =>
                            handleChange(oldval, newval, scope.row)
                        "
                        :min="1"
                        size="mini"
                      ></el-input-number>
                    </template>
                  </el-table-column>
                  <el-table-column prop="totalPrice" label="小计" width="100">
                  </el-table-column>
                  <el-table-column
                    header-align="center"
                    align="center"
                    label="操作"
                    width="100"
                  >
                    <template slot-scope="scope">
                      <el-button
                        type="danger"
                        icon="el-icon-delete"
                        size="mini"
                        @click="handleRemove(scope.row.id)"
                      >
                        删除
                      </el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </div>
            </el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="12">
              <el-row>
                <el-col :span="13">
                  <div align="right">
                    <el-button
                      type="primary"
                      size="mini"
                      @click="handleSelection"
                      >删除选中</el-button
                    >
                  </div>
                </el-col>
                <el-col :span="8">
                  <div align="right">
                    <div>
                      已选择：<span style="color: red;font-size: 20px">{{
                        count
                      }}</span
                      >件数
                    </div>
                  </div>
                </el-col>
              </el-row>
            </el-col>
            <el-col :span="12">
              <el-row :gutter="10">
                <el-col :span="11">
                  <div align="right">
                    总价：<span style="color: red;font-size: 18px">{{
                      totalPrice
                    }}</span
                    >￥
                  </div>
                </el-col>
                <el-col :span="13">
                  <div>
                    <el-button
                      type="primary"
                      size="mini"
                      icon="el-icon-s-goods"
                      @click="order1"
                      >去结算</el-button
                    >
                  </div>
                </el-col>
              </el-row>
            </el-col>
          </el-row>
        </div>
      </el-card>

      <!--      最新发布-->

      <el-card class="box-card card" style="margin-top: 10px">
        <el-row>
          <h2>最新推荐</h2>
          <el-col
            :span="4"
            v-for="(r, index) in newest"
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
      <div align="center">
        <span style="font-family:arial;"></span>
      </div>
    </el-footer>
  </el-container>
</template>

<script>
import Header from "./common/Header";

export default {
  name: "Cart",
  components: { Header },
  data() {
    return {
      selections: [],
      cart: [],
      count: 0,
      totalPrice: 0,
      newest: ""
    };
  },
  methods: {
    //修改数量价格
    handleChange(currentValue, oldValue, val) {
      this.axios({
        method: "put",
        url: "/cart",
        data: {
          count: currentValue,
          id: val.id,
          totalPrice: val.product.price * currentValue
        }
      }).then(resp => {
        this.show();
        this.cart = resp.data;
      });
    },
    goBack() {
      window.location.href = "javascript:history.go(-1)";
      console.log("go back");
    },
    //多条选中
    handleSelectionChange(value) {
      for (let i in value) {
        this.selections.push(value[i].id);
      }
    },
    //多条删除
    handleSelection() {
      //获取到表格对象，从表格中获取到已经选择到的行
      this.$confirm("是否删除这些购物车里的商品?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.axios({
            method: "delete",
            url: "/cart/ids?ids=" + this.selections
          }).then(resp => {
            this.show();
            if (resp.data === "success") {
              this.$message({
                type: "success",
                message: "删除成功!"
              });
            } else {
              this.$message({
                type: "error",
                message: "删除失败"
              });
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    handleRemove(id) {
      this.axios
        .delete("/cart", {
          params: { id: id }
        })
        .then(resp => {
          if (resp.data === "success") {
            this.show();
            this.$message({
              type: "success",
              message: "删除成功!"
            });
          } else {
            this.$message({
              type: "error",
              message: "删除失败"
            });
          }
        });
    },
    order1() {
      this.$router.push("/order_address");
    },
    show() {
      this.axios
        .get("/cart", {
          params: {
            uid: JSON.parse(this.$store.state.userName).id
          }
        })
        .then(resp => {
          this.cart = resp.data.carts;
          this.count = resp.data.count;
          this.totalPrice = resp.data.totalPrice;
        });
    },
    new() {
      //加载最近商品
      this.axios({
        method: "get",
        url: "/product/newest"
      }).then(resp => {
        this.newest = resp.data;
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
    this.show();
    this.new();
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

/*行间距*/
.el-row {
  margin-bottom: 20px;
}
</style>
