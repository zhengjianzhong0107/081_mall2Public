// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from "vue";
import App from "./App";
import router from "./router";
//处理前后端分离的跨域请求
import axios from "axios";
import VueAxios from "vue-axios";
//前端UI
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
//网站标题设置
import VueWechatTitle from "vue-wechat-title";
//使用VueX用于登录验证
import Vuex from "vuex";
import store from "./store";
//api跨域
import { VueJsonp } from "vue-jsonp";
import Router from "vue-router";
Vue.use(VueJsonp);
Vue.use(Vuex);
Vue.use(VueWechatTitle);
Vue.use(ElementUI);

//配置后端服务地址
axios.defaults.baseURL = "http://localhost:8081/";
//添加的配置
axios.defaults.withCredentials = true;
//在Vue中配置axios，在所有的Vue组件中就可以通过this.axios使用axios库
Vue.use(VueAxios, axios);
Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: "#app",
  router,
  store,
  components: { App },
  template: "<App/>"
});
router.beforeEach((to, from, next) => {
  if (to.matched.some(route => route.meta.requiresAuth)) {
    if (
      window.localStorage.getItem("userName") === "" ||
      window.localStorage.getItem("userName") === null
    ) {
      // 没有登录信息跳转到登录页

      next({
        path: "/login",
        query: { redirect: to.fullPath } // 'to.fullPath'跳转到登录之前页面的路径
      });
    } else {
      next();
    }
  } else {
    next();
  }
});

// router.beforeEach(function(to,from,next){
//   if(store.state.userInfo||to.path==='/login'){
//     next();
//   }
//   else {
//     next({path:'/login'})
//   }
// })
// // 动态修改页面标题
// router.beforeEach((to, from, next) => {
//   /* 路由发生变化修改页面title */
//   if (to.meta.title) {
//     document.title = to.meta.title
//   }
//   next()
// });
