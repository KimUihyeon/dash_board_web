<<template>
    <div id="nav">
      <span class="box-left">

        <Timer></Timer>
      </span>

      <HeaderWeather/>

      <RouterItem to="/home" icon="el-icon-finished" name="홈" />
      <RouterItem to="/todo" icon="el-icon-finished" name="할일" />
      <RouterItem to="/calendor" icon="el-icon-date" name="일정" />
      <RouterItem to="/search" icon="el-icon-finished" name="검색" />
      <RouterItem to="/study" icon="el-icon-date" name="공부" />
      <RouterItem to="/setting" icon="el-icon-finished" name="설정" />


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
import Timer from '../timer/Time'

const name = 'Header';
const components = {
  Timer,
  RouterItem,
  HeaderWeather
}

export default Vue.extend({
    name ,
    components,
})
</script>

