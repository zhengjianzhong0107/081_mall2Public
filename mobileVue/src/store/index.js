import Vue from 'vue';
import Vuex from 'vuex';
Vue.use(Vuex);

const store =  new Vuex.Store({
  state: {
    //注册时用
    email:window.sessionStorage.getItem('myEmail') ? window.sessionStorage.getItem('myEmail') : '',
    active:window.sessionStorage.getItem('myActive')? Number.parseInt(window.sessionStorage.getItem('myActive')):0,
    act:window.sessionStorage.getItem('act')? Number.parseInt(window.sessionStorage.getItem('act')):0,
    // 登录时保存
    userName: window.localStorage.getItem('userName') ? window.localStorage.getItem('userName') : '',
    myAdmin: window.localStorage.getItem('myAdmin') ? window.localStorage.getItem('myAdmin') : '',
    Authorization: window.localStorage.getItem('Authorization') ? window.localStorage.getItem('Authorization') : '',
    signTime: window.localStorage.getItem('signTime') ? window.localStorage.getItem('signTime') : '',
  },
  mutations: {
    // 注册步骤条加
    add(state, step) {
      state.active += step;

      console.log(typeof (state.active));
      console.log("赋值后" + state.active);
      window.sessionStorage.setItem('myActive', state.active);
    },
    // 订单生成步骤条
    addAc(state, step) {
      state.act += step;
      window.sessionStorage.setItem('act', state.act);
    },
    //临时改变用于注册账号
    emailChange(state, email) {
      state.email = email;
      window.sessionStorage.setItem('myEmail', state.email);
    },

    // 用户信息状态
    loginChange(state, user) {
      state.userName = JSON.stringify(user.userName);
      state.Authorization = user.Authorization;
      state.signTime = user.signTime;
      console.log(state.userName);
      window.localStorage.setItem('userName', state.userName);
      window.localStorage.setItem('Authorization', state.Authorization);
      window.localStorage.setItem('signTime', state.signTime);
    },
// 后台用户信息
    // 用户信息状态
    adminLogin(state, admin) {
      state.myAdmin = JSON.stringify(admin.myAdmin);
      state.Authorization = admin.Authorization;
      state.signTime = admin.signTime;
      window.localStorage.setItem('myAdmin', state.myAdmin);
      window.localStorage.setItem('Authorization', state.Authorization);
      window.localStorage.setItem('signTime', state.signTime);
    },
    //退出
    loginOut(state) {
      state.userName = '';
      state.Authorization = '';
      state.signTime = '';
      state.active = '';
      window.localStorage.setItem('userName', state.userName);
      window.localStorage.setItem('Authorization', state.Authorization);
      window.localStorage.setItem('signTime', state.signTime);
      window.sessionStorage.setItem('active', state.active);
    },
    //后台退出
    //退出
    loginBack(state) {
      state.myAdmin = '';
      state.Authorization = '';
      state.signTime = '';
      window.localStorage.setItem('myAdmin', state.myAdmin);
      window.localStorage.setItem('Authorization', state.Authorization);
      window.localStorage.setItem('signTime', state.signTime);
    },

  }

});
export default store;
