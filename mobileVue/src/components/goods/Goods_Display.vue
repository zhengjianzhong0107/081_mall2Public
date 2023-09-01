<template>
  <el-container style="height: 100vh;">
    <el-header>
      <Header/>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <el-menu
          :unique-opened="true"
          :default-active="onRoutes"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          router
          background-color="#d3dce6">
          <el-menu-item v-for="cate in cates" :index="'/mobile/display?cid='+cate.id.toString()"  :key="cate.id">
            <i class="el-icon-mobile"></i>
            <span slot="title">{{cate.name}}</span>
          </el-menu-item>

        </el-menu>
      </el-aside>
      <el-container>
        <el-main>
          <el-row :gutter="10">
            <el-col :span="24">
              <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item :to="'/mobile/display?cid='+this.$route.query.cid.toString()">{{cateName}}</el-breadcrumb-item>
              </el-breadcrumb>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="4" v-for="(r, index) in products.list" :key="index" :offset="index%5!=0 ? 1 : 0">
              <div @click="detail(r.id)">
                <el-card :body-style="{ padding: '0px' }" shadow="hover"  style="cursor:pointer;margin-bottom: 15px;" >
                  <el-image
                    style="width: 258px; height: 150px;"
                    :src="r.image"
                    fit="contain"></el-image>
                  <!--                      <img :src="[r.image]" class="image">-->
                  <div style="padding: 14px;">
                    <div align="center">
                      <p>{{r.proName}}</p>
                      <span style="color: #d3dce6">{{r.message|ellipsis}}</span>
                    </div>

                    <div class="bottom clearfix">
                      <span style="color: red;float:right">￥{{r.price}}</span>
                    </div>
                  </div>
                </el-card>

              </div>
            </el-col>
          </el-row>
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
        </el-main>
        <el-footer>
          <Footer/>
        </el-footer>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>

    import Header from "../common/Header";
    import Footer from "../common/Footer";

    export default {
        name: "Goods_Display",
        components: {Footer, Header},
        data() {
            return {
                cates: [],
                products:{},
                cateName:'',
                total: 0,
                cid: '',
                currentPage: 1,
                pageSize: 10,
            }
        },
        methods: {
            // 分页相关
            //跳转或当前页改变
            handleCurrentChange(current) {
                this.show(current, this.pageSize);
            },
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            },
            detail(i){
                this.$router.push({path: '/mobile/detail', query:{ id: i }})
            },
            show(pageNo,pageSize){
                 this.cid=this.$route.query.cid;
                this.axios({
                    method:"get",
                    url:"/product/cate",
                    params:{
                        pageNo,pageSize,cid:this.cid
                    }
                }).then(resp=>{
                    this.products=resp.data;
                    this.total = resp.data.total;
                });
                //加载面包屑分类名
                this.axios({
                    method:"get",
                    url:"/cate/one",
                    params:{
                        id:this.$route.query.cid
                    }
                }).then(resp=>{
                    this.cateName=resp.data;

                })
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
           $route(to,from){
               this.show(1,10)
           }
        },
        computed:{
            onRoutes(i){
                return this.$route.fullPath;
            }
        },
        created() {
            //加载分类
            this.axios({
                method:"get",
                url:"/cate",
            }).then(resp=>{
                this.cates=resp.data;
            });
          this.show(1,10)
        }
    }
</script>

<style scoped>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    padding:0px;
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


  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 400px;
    height: 172px;
  }

  /*网站公告*/

  .el-row {
    margin-bottom: 20px;
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
