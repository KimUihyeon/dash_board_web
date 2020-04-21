<<template>
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
    </div>
</template>

<script>
import Vue from 'vue'
export default Vue.extend({
    name : 'Header',
})
</script>