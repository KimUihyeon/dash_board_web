import Axios from "axios";
import { data } from './Data'
const isNull = data.isNull;


// Axios.interceptors.request.use(
//     (config)=>{ console.log(config )},
//     (error) => { console.log(error)});
/**
 * 
 * @param {*} url        = rest ful Url
 * @param {*} dataObject = Nullable
 */
export function get(url, dataObject , config ){

    const apiurl = __uriToDataJoinner(url, dataObject);
    // const config2 = {
    //     headers : {
    //         authentication : 'aaa'
    //     }
    // }

    return Axios({
        method : 'get',
        url : apiurl,
        headers :{
            "Access-Control-Allow-Origin" : "*",
            "Content-type": "Application/json",
            "Authorization": `Bearer askdljaskldj012idji2op12d`
        }
    }).then((res)=>{ return res})
    .catch((err)=>{ return err});

    // aa.then
    // return Axios.get(apiurl, { 
    //     headers : {
    //         authentication : 'aaa'
    //     }}).then(res=>{
    //     return res.data;
    // });
}


export function post(url, data , config){
    
    return Axios({
        method : 'get',
        data : data,
        url ,
        headers :{
            authentication : 'aaa'
        }
    }).then((res)=>{ return res})
    .catch((err)=>{ return err});

    // return Axios.post(url, data , {
    //     headers : {
    //         authentication : 'aaa'
    //     }}).then(res=>{
    //     return res.data;
    // });
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

export function authGet(url, dataObject , config ){
    const apiurl = __uriToDataJoinner(url, dataObject);

    return Axios.get(apiurl, config).then(res=>{
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