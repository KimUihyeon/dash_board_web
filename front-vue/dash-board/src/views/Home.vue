<template>
  <div class="home">
    <WaetherBox></WaetherBox>
    <Time></Time>
  </div>
</template>

<script>
// @ is an alias to /src
import WaetherBox from "@/components/weather/WeatherBox.vue";
import Time from "@/components/timer/Time.vue";

export default {
  name: 'Home',
  components: {
    Time,
    WaetherBox
  }
}
</script>