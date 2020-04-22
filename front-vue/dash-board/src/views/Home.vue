<template>
  <div class="home">
    <Login v-bind:isHistory='false'/>
    <!-- <WaetherBox v-bind:weather=weather></WaetherBox> -->
    <Time></Time>
  </div>
</template>

<script>
// @ is an alias to /src
import Login from '@/components/common/Login.vue'
import HelloWorld from '@/components/HelloWorld.vue'
import WaetherBox from "@/components/weather/WeatherBox.vue";
import Time from "@/components/timer/Time.vue";
import { date } from "../util";

export default {
  name: 'Home',
  components: {
    HelloWorld,
    WaetherBox,
    Time,
    Login,
  },
  data(){
    return {
      weather : '11111',
      dateTime : date.now(),
    }
  },
  mounted(){
    this.timerStart();
  },
  methods : {
    timerStart : function () {
      setInterval(()=>{
        this.dateTime = date.now();
      }, 1000);
    }
  }
}
</script>
