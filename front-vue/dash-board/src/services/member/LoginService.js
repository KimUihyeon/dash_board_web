import { rest } from "../../util";

const base_url = process.env.VUE_APP_API_BASE_URL + '/v1/common';


const login = (id, pw) =>{
    return rest.post( base_url + "/login" , { id , pw });
}

const loginByJwt = (jwt) =>{
    return rest.post( base_url + "/login" , { jwt });
}

const logout = () =>{
    return true;
}


export const loginService = {
    login,
    loginByJwt,
    logout,
}