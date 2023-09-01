<template>
  <el-container style="height: 98vh">
    <el-header><Header/></el-header>
    <el-main >
      <el-row :gutter="10" style="margin-top: 50px">
        <el-col :span="8">&nbsp;</el-col>
       <el-col :span="7" >
         <el-card class="box-card">
           <div slot="header" class="clearfix">
             <span style="font-size: 20px;color: #42b983;">用户登录</span>
             <el-button style="float: right; padding: 3px 0" type="text" @click="toReg">没有账号？前去注册</el-button>
           </div>
           <div>
             <el-form :model="login" :rules="rules" ref="login" label-width="100px" class="demo-login" @keyup.enter.native="submitForm('login')">
               <el-form-item label="登录账号" prop="email">
                 <div style="width: 90%">
                   <el-input v-model="login.email"></el-input>
                 </div>

               </el-form-item>
               <el-form-item label="登录密码" prop="password">
                 <div style="width: 90%">
                 <el-input type="password" v-model="login.password" show-password></el-input>
                 </div>
               </el-form-item>
              <el-row :gutter="10">

               <el-col :span="12" align="left"  >
                 <div>
                   <el-button style="float: left; padding: 3px 30px 0" type="text" @click="jihuo">激活账号</el-button>
                 </div>
               </el-col>
                <el-col :span="10">
                  <div  style="">
                    <el-button style="float: right; padding: 3px 0  0" type="text" @click="findPwd">忘记密码</el-button>
                  </div>
                </el-col>
              </el-row>
               <el-form-item style="margin-top: 15px">
                 <el-button type="primary" @click="submitForm('login')">立即登录</el-button>
                 <el-button @click="resetForm('login')">重置</el-button>
               </el-form-item>
             </el-form>
           </div>
         </el-card>

       </el-col>
      </el-row>

    </el-main>
    <el-footer><Footer/></el-footer>
  </el-container>


</template>

<script>
    import Footer from "./common/Footer";
    import {mapMutations} from 'vuex';
    import Header from "./common/Header";
    export default {
        name: "Login",
        components: {Header, Footer},
        data() {
            return {
                val: false,
                fakeHead:'',
                nickName:'',
                login: {
                    email: '',
                    password: '',
                },
                rules: {
                    email: [{ required: true, message: '账号不能为空'},
                        { type: 'string',
                            message: '邮箱格式不正确',
                            trigger: 'blur',
                            transform (value) {
                                if (!/^\w+((-\w+)|(\.\w+))*@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test(value)) {
                                    return true
                                }else{
                                }
                            }
                        },
                        { type: 'string', message: '长度不能超过30位', trigger: 'blur', max: 30 }
                    ],
                    password: [
                        { required: true, message: '输入密码', trigger: 'blur' },
                        { type: 'string', message: '密码长度应保持在6-16之间', trigger: 'blur', min: 6,max:16 }
                    ]
                }
            };
        },
        methods: {
            ...mapMutations(['loginChange']),
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.qqmail();
                        this.axios({
                            method: "get",
                            url: "/user/login",
                            params: {
                                email: this.login.email,
                                password: this.login.password,
                                fakeHead:this.fakeHead,
                                nickName:this.nickName,
                            }
                        }).then(resp => {
                            //获取后端返回数据
                            let state = resp.data.state;
                            //打印token
                            console.log(resp.data);
                            //判断是否登录成功
                            if (state === 200) {
                                // 加入到全局变量中
                                this.loginChange({
                                    userName: resp.data.user,
                                    Authorization: resp.data.token,
                                    signTime: new Date().getTime()
                                });
                                this.$message.success("登陆成功");
                                setTimeout(() => {
                                    let path = '/';
                                    if (this.$route.query.redirect) {
                                        path = this.$route.query.redirect   // 跳到之前的页面
                                    }
                                    this.$router.push({
                                        path: path
                                    });
                                }, 1000)
                            }else if (state === 400) {

                                this.$message.warning("该账号未激活，请先激活再使用！");

                            }else if (state === 500) {

                                this.$message.warning("此账号不存在！请先注册");

                            } else {
                                this.$message.error("用户名或密码错误！");
                            }

                        });
                    }
                })
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            findPwd(){
                    this.$prompt('请输入注册邮箱', '找回密码', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
                        inputErrorMessage: '邮箱格式不正确',

                    }).then(({ value }) => {
                        this.axios.get("/user/findPwd",{params:{email:value}}).then(resp=>{
                            if(resp.data==="success"){
                                this.$message({
                                    type: 'success',
                                    message: '密码已经发送到您的邮箱，请注意查收'
                                });
                            }else {
                                this.$message({
                                    type: 'error',
                                    message: '该邮箱未注册！'
                                });
                            }
                        })
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '取消输入'
                        });
                    });

            },
            jihuo(){
                this.$prompt('请输入激活邮箱', '账号激活', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
                    inputErrorMessage: '邮箱格式不正确',

                }).then(({ value }) => {
                    this.axios.get("/user/jiHuo",{params:{email:value}}).then(resp=>{
                        if(resp.data==="success"){
                            this.$message({
                                type: 'success',
                                message: '激活链接已经发送到您的邮箱，请注意查收'
                            });
                        }else {
                            this.$message({
                                type: 'error',
                                message: '该账号未注册！'
                            });
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消输入'
                    });
                });

            },
            toReg(){
                this.$router.push("/register");
            },
            qqmail(){
                let load='\\w+@(qq)\\.\\w+';
                        //交验邮箱是否为QQ邮箱
                        if(load.search(this.login.email)){
                            let a= this.login.email.split('@');
                            console.log(a[0]);
                            this.axios('http://121.40.159.226/api/qq/'+a[0]).then(res=> {
                                this.nickName=res.data.data.name;
                                this.fakeHead="https://q1.qlogo.cn/g?b=qq&nk="+a[0]+"&s=100";
                            })

                        }

            },
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
</style>
