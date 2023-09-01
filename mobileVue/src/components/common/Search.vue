<template>
  <div>
    <el-container>
      <el-header>
        <Header />
      </el-header>
      <el-main>
        <div style="padding-left:50px;padding-right: 50px;">
          <div style="margin-bottom: 20px">
            <el-row :gutter="10">
              <el-col :span="6"
                ><span style="color:red">{{ this.$route.query.keyWord }}</span
                >搜索到以下结果>></el-col
              >
            </el-row>
          </div>
          <div align="center">
            <el-row>
              <el-col
                :span="4"
                v-for="(r, index) in products.list"
                :key="index"
                :offset="index % 5 != 0 ? 1 : 0"
              >
                <div @click="detail(r.id)">
                  <el-card
                    :body-style="{ padding: '0px' }"
                    shadow="hover"
                    style="cursor:pointer;margin-bottom: 20px;"
                  >
                    <el-image
                      style="width: 258px; height: 150px;"
                      :src="r.image"
                      fit="contain"
                    ></el-image>
                    <!--                      <img :src="[r.image]" class="image">-->
                    <div style="padding: 20px;">
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
            </el-row>
          </div>
          <div align="center">
            <el-pagination
              background
              :page-size.sync="pageSize"
              :current-page.sync="currentPage"
              layout="prev, pager, next,total"
              @current-change="handleCurrentChange"
              :page-count="5"
              :total="total"
            >
            </el-pagination>
          </div>
        </div>
      </el-main>
      <el-footer>
        <div align="center">
          <span style="font-family:arial;"></span>
        </div>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
import Header from "./Header";
import Footer from "./Footer";

export default {
  name: "Search",
  components: { Footer, Header },
  data() {
    return {
      products: {},
      total: 0,
      keyWord: "",
      currentPage: 1,
      pageSize: 10
    };
  },
  methods: {
    // 分页相关
    //跳转或当前页改变
    handleCurrentChange(current) {
      this.selectAll(current, this.pageSize);
    },
    detail(i) {
      this.$router.push({ path: "mobile/detail", query: { id: i } });
    },
    selectAll(pageNo, pageSize) {
      this.keyWord = this.$route.query.keyWord;
      this.axios({
        method: "get",
        url: "/product/dim",
        params: {
          pageNo,
          pageSize,
          keyWord: this.keyWord
        }
      }).then(resp => {
        this.products = resp.data;
        this.total = resp.data.total;
      });
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
  watch: {
    $route(to, from) {
      this.selectAll();
    }
  },
  created() {
    this.selectAll(1, 10);
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
