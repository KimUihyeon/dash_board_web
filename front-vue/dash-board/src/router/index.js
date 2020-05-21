import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '../views/Main.vue'
import LoginPage from '../views/common/LoginPage.vue'
import TestPage from '../views/TestPage.vue'
import TodoPage from '../views/todo/TodoPage.vue'
import { data, rest, error } from '../util';
import store from '../store/index';

const logoutProcess = () => (to, from , next) => {
  store.dispatch('app_logout').then(auth=>{
    return next(`/login`);
  }).catch(err=>{
    return next(`/login`);
  })
}


const authCheck = () => (to, from, next) => {
  store.dispatch('check_app_auth').then(auth=>{
    if (auth === 'Auth') { // 인증완료
      return next();
    }
    else { // 정상적인 인증실패
      return next(`/login?e=${error.authencationError.code}`);
        }
  }).catch(err=>{

    if(err.status === 404){ // 404
        return next(`/login?e=${error.httpUrlNotSurpport.code}`);
    }
    else { // 500
        return next(`/login?e=${error.nullExceptionError.code}`);
    }
  })
}


Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    alias: ['/login'],
    component: LoginPage,
  },
  {
    path: '/main',
    name: 'TestPage',
    component: Main,
    beforeEnter: authCheck()
  },
  {
    path: '/test',
    name: 'TestPage',
    component: TestPage,
    beforeEnter: authCheck()
  },
  {
    path: '/',
    name: 'Home',
    alias: ['/login'],
    component: TodoPage,
    beforeEnter: authCheck()
  },
  {
    path: '/todo',
    name: 'TestPage',
    component: TodoPage,
    beforeEnter: authCheck()
  },
  {
    path: '/weather',
    name: 'TestPage',
    component: TestPage,
    beforeEnter: authCheck()
  },
  {
    path: '/Logout',
    name: 'Logout',
    component: TestPage,
    beforeEnter: logoutProcess()
  },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
