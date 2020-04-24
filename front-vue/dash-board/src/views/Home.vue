<template>
  <div class="home">
    <Login v-bind:isHistory='false'/>
    <Time></Time>
  </div>
</template>

<script>
// @ is an alias to /src
import Login from '@/components/common/Login.vue'
import WaetherBox from "@/components/weather/WeatherBox.vue";
import Time from "@/components/timer/Time.vue";
import { date } from "../util";

export default {
  name: 'Home',
  components: {
    Time,
    Login,
  }
}
</script>
