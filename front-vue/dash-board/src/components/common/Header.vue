<template>
    <div id="nav">
      <span class="box-left nav-color-white time-box">
        <Timer></Timer>
      </span>
      <HeaderWeather/>

      <el-dropdown trigger="click" @command="route" class="nav-color-white">
        <span class="el-dropdown-link">Menu<i class="el-icon-s-unfold el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item :disabled='!isLogin' icon="el-icon-s-home" command='/home'>Home</el-dropdown-item>
          <el-dropdown-item :disabled='!isLogin' icon="el-icon-circle-check" command='/todoCategory'>todoList</el-dropdown-item>
          <el-dropdown-item :disabled='!isLogin' icon="el-icon-date" command='/cal'>calendar</el-dropdown-item>
          <el-dropdown-item v-show="isLogin" icon="el-icon-lock" divided command='/logout'>logout</el-dropdown-item>
          <el-dropdown-item v-show="!isLogin" icon="el-icon-user-solid" divided command='/signup'>sign Up</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>


<!-- 
      <RouterItem v-show="isLogin" to="/todo" icon="el-icon-finished" name="할일" />
      <RouterItem v-show="isLogin" to="/Logout" icon="el-icon-finished" name="로그아웃" /> -->

      
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
            <!-- <span @click='logOut'>logOut</span> -->
          </span>
          <span style="padding:10px">
            <router-link to="/main">Main</router-link>
          </span>
          <span style="padding:10px">
            <router-link to="/test">Test</router-link>
          </span>
        </div>
      </div>

      <Signup
          :submitHandle="()=>{}"
          :showModal='showSignup'
      />
    </div>
</template>

<script>
import Vue from 'vue'
import { mapGetters } from "vuex";
import RouterItem from "../common/custome/RouterItem";
import HeaderWeather from '../common/custome/HeaderWeather';
import Signup from './Signup';
import Timer from '../timer/Time'
import { data , rest } from '../../util'

const name = 'Header';
const components = {
  Timer,
  RouterItem,
  HeaderWeather,
  Signup
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
</style>