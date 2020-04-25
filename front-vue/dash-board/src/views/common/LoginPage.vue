<template>
  <div class="home">
    <Login/>
  </div>
</template>

<script>
// @ is an alias to /src
import Login from '@/components/common/Login.vue'

export default {
  name: 'LoginPage',
  components: {
    Login,
  },
  mounted(){
    console.log('ttt')
  }
}
</script>