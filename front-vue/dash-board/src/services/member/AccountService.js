import { rest } from "../../util";

const base_url = process.env.VUE_APP_API_BASE_URL + '/v1/account';


const login = (id, pw) =>{
    return rest.post( base_url + "/login" , { id , pw });
}

const loginByJwt = (jwt) =>{
    return rest.post( base_url + "/login" , { jwt });
}

const duplicateCheck = (id) =>{
    return rest.post( base_url + '/existence ', { id });
}

const signup = (id, pw) =>{
    return rest.post( base_url + '/signup' , { id , pw });
}

const logout = () =>{
    return true;
}


export const accountService = {
    login,
    loginByJwt,
    logout,
    signup,
    duplicateCheck
}