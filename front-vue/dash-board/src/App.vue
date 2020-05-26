<template>
  <div id="app">
    <v-app>
      <div v-if="true">
        <Header/>
      </div>
      <router-view/>
    </v-app>
  </div>
</template>

<script>
import Header from "@/components/common/Header.vue"
export default {
  components : {
    Header,
  },
  created(){
    this.$store.commit('SET_WINDOW_SIZE', { windowSize : window.innerWidth});
    window.addEventListener('resize', () => {
      this.$store.commit('SET_WINDOW_SIZE', { windowSize : window.innerWidth});
    });
  }
}
</script>