<template>
    <div>
        <div @click="storeDataChange">
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
        this.weathers = weather2;
    },
    computed : {
    },
    methods : {
    }
    
})
</script>