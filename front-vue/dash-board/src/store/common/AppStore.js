
import { accountService } from '../../services'
import { data } from '../../util'

const __COOKIE_PROPS_NAME_LOGIN_ID = process.env.VUE_APP_COOKIE_NAME_LOGIN;
const __COOKIE_PROPS_NAME_TOKEN = process.env.VUE_APP_COOKIE_NAME_TOKEN;
const __COOKIE_PROPS_NAME_LOGIN_HISTORY = process.env.VUE_APP_COOKIE_NAME_LOGIN_HISOTRY;

/**
 *  Store 작성법
 * @since 20.04.21 김의현
 * 
 *  state = { property : '' }
 *  getters = { functionName(){} },
 *  actions = { functionName(){} },
 *  mutation = { functionName(){} },
 */

const state = {
    isLogin : false,
    token : '',
}

const getters = {
    isLogin : function (state){
        return state.app.isLogin;
    }
}

const actions = {
    app_login : function (context , {id ,pw}){
        return new Promise((resolve, reject)=>{
            accountService.login(id, pw).then(res =>{
                let { authType , id , token } = res

                if(authType === 'Auth'){
                    data.createCookie(__COOKIE_PROPS_NAME_LOGIN_HISTORY, true ,365);
                    data.createCookie(__COOKIE_PROPS_NAME_LOGIN_ID, id, 365);
                    data.createCookie(__COOKIE_PROPS_NAME_TOKEN, token, 365 ); 
                }

                resolve(res);
            }).catch(err =>{
                reject(err);
            })
        });
    },
    app_logout : function (){
        return new Promise((resolve , reject)=>{
            try{
                data.removeCookie(__COOKIE_PROPS_NAME_LOGIN_HISTORY);
                data.removeCookie(__COOKIE_PROPS_NAME_LOGIN_ID);
                data.removeCookie(__COOKIE_PROPS_NAME_TOKEN);
                resolve();
            }
            catch(e){
                reject(e);
            }
        })
    },
}

const mutation = {
    SET_TOKEN : function (state , payload){
        state.app.token = payload.token;
    },
    REMOVE_TOKEN : function (state , payload){
        state.app.token = '';
    }
}


export const appStoreBudle = {
    name : 'app',
    state,
    getters,
    actions,
    mutation
}