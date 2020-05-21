
import { accountService } from '../../services'
import { data , rest } from '../../util'

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
                    data.cookie.createCookie(__COOKIE_PROPS_NAME_LOGIN_HISTORY, true ,365);
                    data.cookie.createCookie(__COOKIE_PROPS_NAME_LOGIN_ID, id, 365);
                    data.cookie.createCookie(__COOKIE_PROPS_NAME_TOKEN, token, 365 );
                    context.commit('SET_IS_LOGIN', { isLogin : true});
                }

                resolve(res);
            }).catch(err =>{
                reject(err);
            })
        });
    },
    app_logout : function (context){
        return new Promise((resolve , reject)=>{
            try{
                debugger;
                data.cookie.removeCookie(__COOKIE_PROPS_NAME_LOGIN_HISTORY);
                data.cookie.removeCookie(__COOKIE_PROPS_NAME_LOGIN_ID);
                data.cookie.removeCookie(__COOKIE_PROPS_NAME_TOKEN);
                context.commit('SET_IS_LOGIN', { isLogin : false});
                debugger;
                resolve();
            }
            catch(e){
                reject(e);
            }
        })
    },
    check_app_auth : function (context){

        let userId = data.cookie.getCookie(process.env.VUE_APP_COOKIE_NAME_LOGIN);
        let authApi = process.env.VUE_APP_API_BASE_URL + '/v1/auth';
        let token = data.cookie.getCookie(process.env.VUE_APP_COOKIE_NAME_TOKEN);

        return new Promise((resolve , reject)=>{
            if (!data.isNull(userId)) {
    
                rest.post( authApi , { token }).then(({authType}) => {
                    console.log(authType);

                    if (authType === 'Auth') { // 인증완료
                        context.commit('SET_IS_LOGIN', { isLogin : true});
                    }
                    else { // 정상적인 인증실패
                        context.commit('SET_IS_LOGIN', { isLogin : false});
                    }
                    resolve(authType);
    
                }).catch((err) => {// 서버에러 
                    
                    context.commit('SET_IS_LOGIN', { isLogin : false});
                    reject(err);
                });
            }
            else {
                context.commit('SET_IS_LOGIN', { isLogin : false});
                const authType = 'NoAuth';
                reject(authType);
            }

        })
    }
}

const mutation = {
    SET_IS_LOGIN : function (state , payload){
        state.app.isLogin = payload.isLogin;
    },
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