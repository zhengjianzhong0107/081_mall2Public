<template>
  <div>
  <div>
  <el-row :gutter="10">
   <el-col :span="24"><div align="center">订单生成完成，您的订单号：<span style="color: red">{{id}}</span></div></el-col>
  </el-row>
  <el-row :gutter="10">
    <el-col :span="24"><div align="center" style="color: #55a532">现在请去我的订单界面完成付款吧</div></el-col>
  </el-row>
  </div>
    <div style="margin-top: 15px">
      <el-row>
        <el-col :span="12">
          <div align="right">
            <el-button type="primary" size="mini"  @click="next">下一步</el-button>
          </div>
        </el-col>
        <el-col :span="12">
          <div align="left" style="margin-left: 15px" @click="cancel">
            <el-button type="primary" size="mini">取消</el-button>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
    import {mapMutations} from "vuex";

    export default {
        name: "Order2",
        data(){
            return{
                id:'',
                order:{},
                address:{},
                cart:{},
                a:true
            }
        },
        methods:{
            ...mapMutations(['addAc']),
            next(){
                window.sessionStorage.setItem('act', 0);
                this.$router.push("/user/my_order")
            },
            // 取消并返回首页
            cancel() {
                this.$router.push("/");
            }
        },
        created() {
            this.axios.get("/cart", {
                params: {
                    uid: JSON.parse(this.$store.state.userName).id
                }
            }).then(resp => {
                this.cart = resp.data.carts;
                //如果购物车空，则不添加订单
                if(this.cart.length===0){
                   this.a=false
                }
                this.address=JSON.parse(window.sessionStorage.getItem("addr"));
                this.order.userId=this.address.uid;
                this.order.addrId=this.address.id;
                this.order.orderState=0;
                if(this.a){
                    this.axios({
                        method:"post",
                        url:"/order",
                        data:this.order
                    }).then(resp=>{
                        if (resp.data.status === "success") {
                            this.id=resp.data.id;
                            this.$message({
                                type: 'success',
                                message: '订单生成成功!'
                            });
                        } else {
                            this.$message({
                                type: 'error',
                                message: '订单生成失败'
                            });
                        }
                    })
                }
            });

        }
    }
</script>

<style scoped>
.el-row{
  margin-bottom: 10px;
}
</style>
