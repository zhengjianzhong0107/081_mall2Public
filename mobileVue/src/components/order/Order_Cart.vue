<template>
  <div>
          <el-row :gutter="10">
            <el-col :span="24">
              <div align="center">
                <el-table
                  :data="tableData"
                  style="width: 601px"
                  size="mini"
                >
                  <el-table-column
                    prop="img"
                    width="120">
                    <el-image
                      style="width: 100px; height: 100px"
                      :src="img"
                      fit="scale-down"></el-image>
                  </el-table-column>
                  <el-table-column
                    prop="name"
                    label="商品名称"
                    width="180">
                  </el-table-column>
                  <el-table-column
                    prop="price"
                    label="单价"
                    width="100">
                  </el-table-column>
                  <el-table-column
                    prop="count"
                    label="数量"
                    width="100">
                  </el-table-column>
                  <el-table-column
                    prop="totalPrice"
                    label="小计"
                    width="100">
                  </el-table-column>
                </el-table>
              </div>
            </el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="12">
              <el-row>
                <el-col :span="18">
                  <div align="right">
                    总价：<span style="color: red;font-size: 18px">3364</span>￥
                  </div>
                </el-col>
              </el-row>
            </el-col>
          </el-row>

  </div>
</template>

<script>
    export default {
        name: "Order1",
        data() {
            return {
                selections: [],
                active: 0,
                tableData: [{
                    img: require('../../assets/upload/iPhone8.jpg'),
                    name: '苹果手机',
                    price: 1234,
                    count: 1,
                    totalPrice: 1234
                },
                    {
                        img: require('../../assets/upload/iPhone8.jpg'),
                        name: '苹果手机1',
                        price: 3569,
                        count: 1,
                        totalPrice: 3569
                    }],
                img: require('../../assets/upload/iPhone8.jpg'),
            }
        },
        methods: {
            // 返回上一页
            goBack() {
                window.location.href = "javascript:history.go(-1)";
                console.log('go back');
            },

        }
    }
</script>

<style scoped>
  /*布局*/
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
    font-size: 30px;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .el-header img {
    height: 50px;
    padding-top: 30px;
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
