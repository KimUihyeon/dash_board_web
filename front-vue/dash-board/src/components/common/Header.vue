<template>
    <div id="nav">
      <span class="box-left">
        <Timer></Timer>
      </span>
      <HeaderWeather/>

      <!-- <RouterItem to="/home" icon="el-icon-finished" name="홈" /> -->

      <RouterItem to="/todo" icon="el-icon-finished" name="할일" />
      <RouterItem to="/Logout" icon="el-icon-finished" name="로그아웃" />
      <span>{{isLogin}}</span>
      <!-- <RouterItem to="/Logout" icon="el-icon-finished" name="Logout" @click="logOut" /> -->
      
      <!-- 
      <RouterItem to="/calendor" icon="el-icon-date" name="일정" /> 
      <RouterItem to="/search" icon="el-icon-finished" name="검색" /> 
      <RouterItem to="/study" icon="el-icon-date" name="공부" /> 
      <RouterItem to="/setting" icon="el-icon-finished" name="설정" /> 
      -->

      <div v-if="mode === 'DEV'">
        <div>
          <span style="padding:10px">
            <router-link to="/login">Login</router-link>
          </span>
          <span style="padding:10px">
            <span @click='logOut'>logOut</span>
          </span>
          <span style="padding:10px">
            <router-link to="/main">Main</router-link>
          </span>
          <span style="padding:10px">
            <router-link to="/test">Test</router-link>
          </span>
        </div>
      </div>
    </div>
</template>

<script>
import Vue from 'vue'
import { mapGetters } from "vuex";
import RouterItem from "../common/custome/RouterItem";
import HeaderWeather from '../common/custome/HeaderWeather';
import Timer from '../timer/Time'
import { data , rest } from '../../util'

const name = 'Header';
const components = {
  Timer,
  RouterItem,
  HeaderWeather
}

export default Vue.extend({
    name ,
    components,
    data(){
      return {
        mode : process.env.VUE_APP_MODE
      }
    },
    // methods: {
    //   logOut(){
    //     this.$store.dispatch('app_logout').then(()=>{
    //       this.$router.push({ path : '/login'});
    //     })
    //   },
    // },
    computed : {
      ...mapGetters(['isLogin']),
    }
})
</script>



<style scoped>
.logout-button{
  font-size: 24px;
}
</style>