<template>
    <div>
        <h1>{{context}}</h1>
        
        <div>
            test.vue
        </div>
        <div>
            {{weathers}} 
        </div>
    </div>
</template>

<script lang="ts">
import Vue from 'vue'
import { weatherService } from "../services";
import { date } from '../util'

export default Vue.extend({
    name : 'Test',
    data :  () => {
        return {
            context: 'test.vue -> data rendering',
            weathers : []
        }
    },
    async mounted(){
        
        let location = {
            cityId : 1835553,
            name : '수원',
            name_kr : '수원'
        }

        let weather2 = await weatherService.getCurrentWeather(location.cityId);
        console.log(weather2);
    }
    
})
</script>