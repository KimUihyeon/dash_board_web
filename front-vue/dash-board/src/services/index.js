import Axios from "axios";


export const getWeather = (APIKEY) =>{
    let url = `https://api.openweathermap.org/data/2.5/onecall?lat=60.99&lon=30.9&appid=${APIKEY}`;

    return Axios.get(url)
}