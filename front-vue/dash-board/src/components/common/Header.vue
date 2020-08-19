<template>
    <div id="nav">
      <span class="box-left nav-color-white time-box">
        <Timer></Timer>
      </span>
      <span class="box-left">
        <HeaderWeather/>
      </span>

      <!-- <el-dropdown trigger="click" @command="route" class="nav-color-white">
        <span class="el-dropdown-link">Menu<i class="el-icon-s-unfold el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item :disabled='!isLogin' icon="el-icon-s-home" command='/home'>Home</el-dropdown-item>
          <el-dropdown-item :disabled='!isLogin' icon="el-icon-circle-check" command='/todoCategory'>todoList</el-dropdown-item>
          <el-dropdown-item :disabled='!isLogin' icon="el-icon-date" command='/cal'>calendar</el-dropdown-item>
          <el-dropdown-item v-show="isLogin" icon="el-icon-lock" divided command='/logout'>logout</el-dropdown-item>
          <el-dropdown-item v-show="!isLogin" icon="el-icon-user-solid" divided command='/signup'>sign Up</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown> -->

      <span>
          <RouterItem v-show='!isLogin' icon="el-icon-s-home" to='/home' name='home' tooltip='홈'/>
          <span class="line">|</span>
          <RouterItem v-show='!isLogin' icon="el-icon-circle-check" to='/todoCategory' name='todo' tooltip='투두리스트'/>
          <RouterItem v-show='!isLogin' icon="el-icon-date" to='/cal' name='calendar' tooltip='일정'/>
          <span class="line">|</span>
          <RouterItem v-show="!isLogin" icon="el-icon-lock" to='/logout' name='logout' tooltip='로그 아웃'/>
          <RouterItem v-show="!isLogin" icon="el-icon-user-solid" to='/signup' name="sign Up" tooltip='회원 가입'/>
      </span>

      <Signup :submitHandle="()=>{}" :showModal='showSignup' /> 
    </div>
</template>

<script>
import Vue from 'vue'
import { mapGetters } from "vuex";
import { data , rest } from '../../util'

import HeaderWeather from '../weather/HeaderWeather';
import Signup from '../account/Signup';
import Timer from '../timer/Time'
import RouterItem from './custome/RouterItem'

const name = 'Header';
const components = {
  Timer,
  HeaderWeather,
  Signup,
  RouterItem
}

export default Vue.extend({
    name ,
    components,
    data(){
      return {
        mode : process.env.VUE_APP_MODE,
        showSignup : false,
      }
    },
    computed : {
      ...mapGetters(['isLogin']),
    },
    methods : {
      route(path){
        if(path.indexOf('signup') !== -1){
          this.showSignupModal();
        }else{
          this.$router.push({path});
        }
      },
      showSignupModal (){
          this.showSignup = false;
            setTimeout(()=>{
              this.showSignup = true
          }, 1); 
      },
    }
})
</script>



<style scoped>
#nav a.router-link-exact-active{
  color: black !important;
}

@media only all and (min-width: 768px) {
  #nav > .time-box{
    opacity: 1;
  }
}

@media all and (max-width:768px) {
  #nav > .time-box{
    opacity: 0;
  }
}

.logout-button{
  font-size: 24px;
}
#nav > .el-dropdown{
  color: white;
}
#nav > .nav-color-white{
  color: white !important;
}

.line {
  /* border: 1px solid #000; */
  /* box-sizing: border-box; */
  padding-left: 3px;
  padding-right: 3px;
  width: 1px;
  display: inline-block;
  height: 100%;
}
</style>