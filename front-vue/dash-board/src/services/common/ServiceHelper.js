import { data } from "../../util";

const __COOKIE_POPS = { 
    LOGIN_ID : process.env.VUE_APP_COOKIE_NAME_LOGIN,
    TOKEN : process.env.VUE_APP_COOKIE_NAME_TOKEN,
} 

export const getBaseUrl = () => {
    return process.env.VUE_APP_API_BASE_URL;
}

export const getCurrentLoginID = () =>{
    return data.cookie.getCookie(__COOKIE_POPS.LOGIN_ID);
}

export const httpAuhorizationHeaderConfig =  () =>{
    return { 
        headers : { 
            Authorization : 'Bearer ' + data.cookie.getCookie(__COOKIE_POPS.TOKEN)
        }
    }
};

export const serviceHelper = {
    getBaseUrl,
    getCurrentLoginID,
    httpAuhorizationHeaderConfig
}