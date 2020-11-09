import { data } from "../../util";

const __COOKIE_POPS = { 
    LOGIN_ID : process.env.VUE_APP_COOKIE_NAME_LOGIN,
    TOKEN : process.env.VUE_APP_COOKIE_NAME_TOKEN,
} 

export const getBaseUrl = () => {
    return process.env.VUE_APP_API_BASE_URL;
}

/**
 * 현재 쿠키에서 로그인데이터 가져옴
 */
export const getCurrentLoginID = () =>{
    return data.cookie.getCookie(__COOKIE_POPS.LOGIN_ID);
}

/**
 * Axios 통신 시 Header
 */
export const httpAuhorizationHeaderConfig =  () =>{
    return { 
        headers : { 
            Authorization : 'bearer ' + data.cookie.getCookie(__COOKIE_POPS.TOKEN)
        }
    }
};

export const serviceHelper = {
    getBaseUrl,
    getCurrentLoginID,
    httpAuhorizationHeaderConfig
}