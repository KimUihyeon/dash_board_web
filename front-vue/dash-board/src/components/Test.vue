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
import { dateConvert } from '../util'

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
        console.log(weather2)


        // let weather3 = await weatherService.getCityForcast(1835553);
        // let result3 = weather3.list.map(t=>{
        //     console.log(t.dt);

        //     let dt = dateConvert.byUnixDate(t.dt).format('YYYY-MM-DD HH:mm:ss');
        //     return {
        //          dt,
        //         temp : t.main.temp
        //     }
        // })
        // console.log(result3)

        // let weather_suwon = await wh getWeather(37.267081, 127.034136);
        // console.log(weather_suwon);

        // let datatimes = weather_suwon.hourly.map(t=>{
        // });



        // let current_weather = await getCurrentWeather(1835553);
        // console.log(current_weather);

        // let weather_suwon_forcast = await getForcast(1835553);

        // console.log(weather_suwon_forcast.list.map(data => {
        //     return {
        //         dt_txt : data.dt_txt,
        //         temp : data.main.temp
        //     }
        // }))
        //let weather_sihung =await getWeather(37.439154, 126.796821);
        //console.log(weather_suwon);
    }
    
})
</script>