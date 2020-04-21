<template>
  <div class="home">
    <WaetherBox v-bind:weather=weather></WaetherBox>
    <Time v-bind:dateTime=dateTime></Time>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import WaetherBox from "@/components/weather/WeatherBox.vue";
import Time from "@/components/timer/Time.vue";
import { date } from "../util";

export default {
  name: 'Home',
  components: {
    HelloWorld,
    WaetherBox,
    Time
  },
  data(){
    return {
      weather : '11111',
      dateTime : date.now(),
    }
  },
  mounted(){
    console.log(this);
    console.log('mounted');
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
