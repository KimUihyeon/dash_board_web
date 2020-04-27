import Axios from "axios";
import { citys } from "../../util/City.js"
import { rest , date } from '../../util'
import { WeatherModel , WeatherDetail ,WeatherOneDay }  from './WeatherResponseModel'
import moment from "moment";


const _API_KEY = process.env.VUE_APP_WEATHER_API;


/**
 * 도시 번호를 기반으로 현재 지역의 날씨를 가지고옴 .. !
 * 
 * @param {number} cityId 도시번호
 */
const getCurrentWeather= (cityId) => {
    let url = `http://api.openweathermap.org/data/2.5/weather?`
               +`id=${cityId}&`
               +`appid=${_API_KEY}&`
               +`lang=kr&`
               +`units=metric`;

    return rest.get(url).then(res=>{
        let { coord , main , name , weather , wind } = res;

        let resLocation = {
            lat: coord.lat,
            lon: coord.lon,
            name : name,
            cityId,
        }

        let resCurrentTemp = new WeatherOneDay(resLocation, date.now(), main.temp,
                                        main.feels_like, main.humidity,
                                         wind.speed , weather , 0, 0);
        return resCurrentTemp;
    }).catch(e=>{
        console.log(e);
        return undefined;
    });
} 

/**
 * One calling Weather datas
 * 현재 날씨, 15일 날씨 예보, 1시간 단위 날씨 예보 구해옴.!
 * 
 * @param {number} lat 위도 
 * @param {number} lon 경도
 */
const oneCallWeather = (lat, lon) =>{
    let url = `https://api.openweathermap.org/data/2.5/onecall?`
             +`lat=${lat}&`
             +`lon=${lon}&`
             +`appid=${_API_KEY}&`
             +`units=metric&`
             +`lang=kr`;

    return rest.get(url);
}


/**
 * 도시 정보를 기반으로 5일치 날씨 정보를 가지고옴 .. ! 
 * feat. 뭔가 부정확함 날짜 대비 기온이 안맞음 !
 * 
 * @param {number} cityId 도시번호
 */
const getCityForcast = (cityId) => {

    let url = `http://api.openweathermap.org/data/2.5/forecast?`
               +`id=${cityId}&`
               +`appid=${_API_KEY}&`
               +`lang=kr&`
               +`units=metric`;

    return rest.get(url)
        .then(data=>{
            return data.list
        }).then(items=>{
            console.log(items);

            let results = [];
            items.forEach((item)=>{

                let { main , weather , wind } = item;

                let d = date.convertByUnixDate(item.dt);
                let resCurrentTemp = new WeatherOneDay(  {} , d , main.temp,
                                                         main.feels_like, main.humidity,
                                                         wind.speed , weather , main.temp_max, main.temp_min);
                results.push(resCurrentTemp);
            })
            return results;
        });
}


/**
 * 
 * @param {*} location 
 */
const getWeatherDetailByLocation = async (location) =>{

    let resLocation = undefined;
    let resWeather = undefined;
    let resCurrent = undefined;

    let current = await getCurrentWeather(location.cityId);
    let day5  = await getCityForcast(location.cityId).then(res=>{
        // let { list } = res;

        // let listObject = list.map(i=>{
        //     return {
        //         time : date.convertByUnixDate(i.dt),
        //     }
        // })

        return res;
    });

    console.log(current);
    console.log(day5);
}

export const weatherService = {
    getCityForcast,
    getCurrentWeather,
    oneCallWeather,
    getWeatherDetailByLocation
}

const getCity = () =>{
    let datas = citys.filter(t=>t.country==="KR");
    console.log(datas);
    console.log(JSON.stringify(datas));
    
}