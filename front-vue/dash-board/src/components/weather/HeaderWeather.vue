<template>
    <span class="header-weather-box" @click="seletedDetail">
        <i class="el-icon-sunny waetherIcon"></i>
        <span>
            <div class="temp row-box">{{ temp }}<span class="unit">°C</span></div>
            <div class="otherTemp row-box">
                <span class="minTemp">{{ minTemp }}</span>
                <span>/</span>
                <span class="maxTemp">{{ maxTemp }}</span>
            </div>
        </span>

        <span class="header-detail-box">
            <div></div>
        </span>
    </span>
</template> 

<script>
import { weatherService } from '../../services';

let weatherIcon = {
    sunny: 'el-icon-sunny',
    cloudy: 'el-icon-cloudy',
    rain: 'el-icon-light-rain',
    heavyRain: 'el-icon-heavy-rain',
    lightning: 'el-icon-lightning',
};

export default {
    name: 'HeaderWeather',
    data() {
        return {
            weatherIcon: '',
            temp: 0,
            maxTemp: 0,
            minTemp: 0,
            windSpeed: 0,
        };
    },
    async mounted() {
        this.setCurrentWeather();
        this.setMinMaxTemp();
    },
    methods: {
        getLocation() {
            return {
                cityId: 1835553,
                name: '수원',
                name_kr: '수원',
            };
        },
        setCurrentWeather() {
            weatherService.getCurrentWeather(this.getLocation().cityId).then((data) => {
                // console.log(data)
                this.temp = data.temp;
            });
        },
        setMinMaxTemp() {
            weatherService.getCityForcast(this.getLocation().cityId).then((data) => {
                this.maxTemp = 25.5;
                this.minTemp = 15.7;
                // console.log(data)
            });
        },
        seletedDetail() {
            // alert('디테일');
        },
    },
};
</script>

<style scoped>
.header-weather-box {
    padding: 5px;
}

.minTemp {
    color: blue;
}
.maxTemp {
    color: red;
}
.row-box {
    display: inline-block;
}
.temp {
    font-size: 15px;
    padding: 3px;
}
.otherTemp {
    font-size: 11px;
    padding: 3px;
}
.unit {
    font-size: 11px;
}
.header-weather-box {
    position: relative;
}
.header-detail-box {
    position: absolute;
    top: 15px;
    left: 50%;
    text-align: center;
}
.waetherIcon {
    font-size: 30px;
}
</style>
