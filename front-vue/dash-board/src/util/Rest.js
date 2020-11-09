import restHelper  from './RestHelper'
const axios = restHelper.axios;


function get(url, datas ){
    const apiurl = restHelper.uriJoinQueryString(url, datas);
    return axios.get(apiurl).then(res=> res.data );
}

function post(url, data){ return axios.post(url, data).then(res=> res.data ) }

function patch(url , datas){ return axios.patch(url, datas).then(res=> res.data) }

function delete_(url, datas){
    const apiurl = restHelper.uriJoinQueryString(url, datas);
    return axios.delete(apiurl, datas).then(res=> res.data );
}

function authGet(url, datas){
    const apiurl = restHelper.uriJoinQueryString(url, datas);
    const authAxios = restHelper.createAuthAxios();
    return authAxios.get(apiurl).then(res=> res.data);
}

function authPost(url, datas){
    const authAxios = restHelper.createAuthAxios();
    return authAxios.post(url, datas).then(res=> res.data);
}


function authPatch(url, datas){
    const authAxios = restHelper.createAuthAxios();
    return authAxios.patch(url, datas).then(res=> res.data);
}


function authDelete(url, datas){
    const apiurl = restHelper.uriJoinQueryString(url, datas);
    const authAxios = restHelper.createAuthAxios();
    return authAxios.delete(apiurl, datas).then(res=> res.data);
}


export const rest = {
    get,
    post,
    patch,
    delete_,
    authGet,
    authPost,
    authPatch,
    authDelete,
}