import Axios from "axios";
/**
 * 
 * @param {*} url        = rest ful Url
 * @param {*} dataObject = Nullable
 */
export function get(url, dataObject ){
    let dataToUrl = `${url}?`;

    /** */
    for(prop in dataObject){
        dataToJson += `${prop}=${JSON.stringify(dataObject[prop])}`;
    }
    return Axios.get(dataToUrl);
}


export function post(url, data){
    return Axios.post(url, data);
}


