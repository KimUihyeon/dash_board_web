import Axios from "axios";
import { data } from './Data'
const isNull = data.isNull;

/**
 * 
 * @param {*} url        = rest ful Url
 * @param {*} dataObject = Nullable
 */
export function get(url, dataObject , config ){

    const apiurl = __uriToDataJoinner(url, dataObject);

    return Axios.get(apiurl, config).then(res=>{
        return res.data;
    });
}


export function post(url, data , config){
    return Axios.post(url, data , config).then(res=>{
        return res.data;
    });
}

export function patch(url , dataObject){
    return Axios.patch(url, dataObject).then(res=>{
        return res.data;
    });
}


export function delete_(url, dataObject){
    const apiurl = __uriToDataJoinner(url, dataObject);
    return Axios.delete(apiurl, dataObject).then(res=>{
        return res.data;
    });
}



function __uriToDataJoinner(url, params){
    
    let dataToUrl = `${url}?`;
    for(var prop in params){
        if(!isNull(params[prop])){
            dataToUrl += `${prop}=${params[prop]}&`;
        }
    }
    return dataToUrl;
}

export const rest = {
    get,
    post,
    patch,
    delete_
}