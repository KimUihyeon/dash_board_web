import Axios from "axios";
import { data } from './Data'
const isNull = data.isNull;


const _COOKIE_POPS = { 
    LOGIN_ID : process.env.VUE_APP_COOKIE_NAME_LOGIN,
    TOKEN : process.env.VUE_APP_COOKIE_NAME_TOKEN,
} 

const _createJWT = () =>{
    return `Bearer ${data.cookie.getCookie(_COOKIE_POPS.TOKEN)}`;
}

const createAuthAxios = () =>{
    let jwt = _createJWT();
    console.log(jwt);
    return Axios.create({
        headers : {
            Authorization : jwt,
            withCredentials: true,
        }
    })
}

function uriJoinQueryString(url, datas){
    let urlJoinQueryString = `${url}?`;
    for(var prop in datas){
        if(!isNull(datas[prop])){
            urlJoinQueryString += `${prop}=${datas[prop]}&`;
        }
    }
    return urlJoinQueryString;
}

const axios = Axios;

const restHelper = {
    createAuthAxios,
    uriJoinQueryString,
    axios
}
export default restHelper;