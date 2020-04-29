<template>
  <div id="app">
    <v-app>
      <Header/>
      <router-view/>
    </v-app>
  </div>
</template>

<script>
import Header from "@/components/common/Header.vue"
export default {
  components : {
    Header,
  }
}
</script>