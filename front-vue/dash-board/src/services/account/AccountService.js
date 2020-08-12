import { rest } from "../../util";
import { serviceHelper } from "../common/ServiceHelper";

const base_url = serviceHelper.getBaseUrl() + '/v1/account';


const login = (id, pw) =>{
    return rest.post( base_url + "/login" , { id , pw });
}

const loginByJwt = (jwt) =>{
    return rest.post( base_url + "/login" , { jwt });
}

const duplicateCheck = (id) =>{
    return rest.post( base_url + '/existence ', { id });
}

const signup = (id, pw, name) =>{
    return rest.post( base_url + '/signup' , { id , pw , name});
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