import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '../views/Main.vue'
import LoginPage from '../views/common/LoginPage.vue'
import TestPage from '../views/TestPage.vue'
import TodoPage from '../views/todo/TodoPage.vue'
import { data, rest, error } from '../util';


const authCheck = () => (to, from, next) => {
  let userId = data.getCookie(process.env.VUE_APP_COOKIE_NAME_LOGIN);

  let authApi = process.env.VUE_APP_API_BASE_URL + '/v1/auth';

  if (!data.isNull(userId)) {
    let token = data.getCookie(process.env.VUE_APP_COOKIE_NAME_TOKEN);

    rest.post( authApi , { token }).then(({authType}) => {
      if (authType === 'Auth') { // 인증완료
        return next();
      }
      else { // 정상적인 인증실패
        return next(`/login?e=${error.authencationError.code}`);
      }

    }).catch(({response}) => {// 서버에러 
      if(response.status === 404){ // 404
        return next(`/login?e=${error.httpUrlNotSurpport.code}`);
      }
      else { // 500
        return next(`/login?e=${error.nullExceptionError.code}`);
      }
    });
  }
  else {
    return next(`/login?e=${error.authencationError.code}`);
  }
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
    component: Main,
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
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
