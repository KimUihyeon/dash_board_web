import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/common/Login.vue'
import TestPage from '../views/TestPage.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Login',
    alias :  ['/login'],
    component: Home
  },
  {
    path: '/test',
    name: 'TestPage',
    component : TestPage
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
