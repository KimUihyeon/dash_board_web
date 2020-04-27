<<template>
    <div id="nav">
      <HeaderWeather/>


      <RouterItem to="/todo" icon="el-icon-finished" name="할일" />
      <RouterItem to="/study" icon="el-icon-date" name="일정" />
      <RouterItem to="/calendor" icon="el-icon-date" name="공부" />


      <div>
        <span style="padding:10px">
          <router-link to="/login">Login</router-link>
        </span>
        <span style="padding:10px">
          <router-link to="/main">Main</router-link>
        </span>
        <span style="padding:10px">
          <router-link to="/test">Test</router-link>
        </span>
      </div>

    </div>
</template>

<script>
import Vue from 'vue'
import RouterItem from "../common/custome/RouterItem";
import HeaderWeather from '../common/custome/HeaderWeather';

export default Vue.extend({
    name : 'Header',
    components : {
      RouterItem,
      HeaderWeather
    }
})
</script>


<style scoped>
#nav{
  text-align: end;
}
</style>