<template>
  <div>
    <el-row :gutter="10">
      <el-col :span="24">
        <div align="center">
          <el-table
            :data="addresses"
            style="width: 660px"
            size="mini"
            @selection-change="handleSelectionChange"
            tooltip-effect="light"
            　　　　highlight-current-row
            　　　
          >
            <el-table-column label="选择" width="50" center>
              　　　　　　<template slot-scope="scope">
              　　　　　　　　<el-radio
              　　　　　　　　　　class="radio"
              　　　　　　　　　　v-model="radio"
              　　　　　　　　　　:label="scope.$index"
              　　　　　　　　　　@change.native="getCurrentRow(scope.$index)"
              　　　　　　　　　　>
              　　　　　　　　&nbsp;</el-radio>
              　　　　　　</template>
              　　　　</el-table-column>
            <el-table-column
              prop="name"
              label="收货人姓名"
              width="150">
            </el-table-column>
            <el-table-column
              prop="mobile"
              label="收货人手机号"
              width="200">
            </el-table-column>
            <el-table-column
              prop="address"
              label="地址"
              width="200">
            </el-table-column>
          </el-table>
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
      </el-col>
    </el-row>

  </div>
</template>

<script>
    import {mapMutations} from "vuex";

    export default {
        name: "Order1",
        data() {
            return {
                currentRow: null,
                radio: false,
                selections: [],
                addresses:[]
            }
        },
        methods: {
            ...mapMutations(['addAc']),
            handleSelectionChange(value) {
                this.selections = value;
            },
            getCurrentRow(index) {
                this.radio = index;
            },
            next(){
               this.addAc(3) ;
               window.sessionStorage.setItem("addr",JSON.stringify(this.addresses[this.radio]))
                this.$router.push('/order_ok')
            },
            // 取消并返回首页
            cancel() {
                this.$router.push("/");
            }
        },
        watch:{
            //观察是否有父组件传递的初始值或者变化，重新选中
            chooseData(val){
                if(val){
                    this.getCurrentRow(val)
                }
            }
        },
        created() {
            this.axios.get("/address",{
                params:{
                    uid:JSON.parse(this.$store.state.userName).id
            }
            }).then(resp=>{
                if(resp.data==null){
                    this.$notify({
                        title: '没有设置收货地址',
                        message: '请先去个人中心添加一条收货地址,5秒后自动跳转',
                        type: 'warning',
                        onClose:this.$router.push("/user/address")
                    });
                }else{
                    this.addresses=resp.data;
                    for (let i=0;i<this.addresses.length;i++){
                        if(this.addresses[i].sid===1){
                            this.radio=i
                        }
                    }
                }
                console.log(this.radio)
            });
            window.sessionStorage.setItem('act', 0);

        }
    }
</script>

<style scoped>
  .el-row {
    margin-bottom: 10px;
  }
</style>
