import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '../views/Main.vue'
import LoginPage from '../views/common/LoginPage.vue'
import TestPage from '../views/TestPage.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/login',
    name: 'Login',
    alias :  ['/login'],
    component: LoginPage
  },
  {
    path: '/main',
    name: 'TestPage',
    component : Main
  },
  {
    path: '/test',
    name: 'TestPage',
    component : TestPage
  },
  {
    path: '/',
    name: 'Home',
    alias :  ['/login'],
    component: Main
  },
  {
    path: '/todo',
    name: 'TestPage',
    component : TestPage
  },
  {
    path: '/weather',
    name: 'TestPage',
    component : TestPage
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
