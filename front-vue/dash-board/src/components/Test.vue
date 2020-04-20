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
import { getWeather } from "../services";
export default Vue.extend({
    name : 'Test',
    data :  () => {
        return {
            context: 'test.vue -> data rendering',
            weathers : []
        }
    },
    async mounted(){
        this.context = '마운트';    
        this.weathers = await getWeather('APIKEY');
    }
    
})
</script>