import Axios from "axios";

export const get = (url)=>{
    return Axios.get(url).then(res=>{
        return res.data;
    }).catch(e=>{
        return e;
    })
}