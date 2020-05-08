import Axios from "axios";
import { isNull } from './Data'
// export const get = (url)=>{
//     return Axios.get(url).then(res=>{
//         return res.data;
//     }).catch(e=>{
//         return e;
//     })
// }

/**
 * 
 * @param {*} url        = rest ful Url
 * @param {*} dataObject = Nullable
 */
export function get(url, dataObject ){

    const apiurl = urlFactory(url, dataObject);
    /** */
    return Axios.get(apiurl).then(res=>{
        return res.data;
    });
}


export function post(url, data){
    return Axios.post(url, data).then(res=>{
        return res.data;
    });
}

export function patch(url , dataObject){
    return Axios.patch(url, dataObject).then(res=>{
        return res.data;
    });
}


export function delete_(url, dataObject){
    const apiurl = urlFactory(url, dataObject);
    return Axios.delete(apiurl, dataObject).then(res=>{
        return res.data;
    });
}



function urlFactory(url, params){
    
    let dataToUrl = `${url}?`;
    for(var prop in params){
        if(!isNull(params[prop])){
            dataToUrl += `${prop}=${params[prop]}&`;
        }
    }
    return dataToUrl;
}