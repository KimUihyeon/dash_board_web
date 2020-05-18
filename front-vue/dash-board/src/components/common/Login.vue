<template>
    <div>
        <div class="blurred-box">
            <div class="user-login-box">
                <span class="user-icon-outer" v-show="isLoginCookie" v-bind:class="!isLoginCookie ? 'blurred-box-smail':''">
                    <span
                        class="user-icon"
                        style="background-image: url(https://avatars3.githubusercontent.com/u/30382976?s=460&u=d8c6040…&v=4);"
                    ></span>
                </span>
                <div class="user-name" v-show="isLoginCookie" >Kim Uihyeon</div>
                
                <div class="inputBox" v-show="!isLoginCookie">
                    <el-input 
                        type="text" 
                        size="small"
                        @keydown.enter.native='keyPress_handle' 
                        placeholder="ID" 
                        v-model="id"></el-input>

                </div>
                <div class="inputBox" >
                    
                    <el-input 
                        type="password" 
                        @keydown.enter.native='keyPress_handle'
                        placeholder="pw" 
                        v-model="pw"
                        size="small"></el-input>

                </div>
                <div v-show="isLoginCookie">
                    <span class="login_help" @click="logout">logout</span>
                </div>

                <div v-show="!isLoginCookie">
                    <span class="login_help" @click="showSignupModal">signup</span>
                    <span class="login_help"> | </span>
                    <span class="login_help">find ID</span>
                </div>
            </div>
        </div>
        <Signup
            :submitHandle="()=>{}"
            :showModal='showModal'
        />
    </div>
</template>


<script>
import { data , alert } from "../../util";
import Axios from 'axios';
import Signup from './Signup';
import { accountService } from "../../services";

const name = 'Login';
const components = {
    Signup,
}

export default {
    name,
    components,
    data(){
        return {
            pw : '',
            id : '',
            isLoginCookie : false,
            isAutoLogin : false,
            validation : {
                pw : false,
                id : false,
                msg : ''
            },
            showModal : false,
        }
    },
    mounted(){
        this.init();
        console.log(this.$router)
    },
    methods : {
        init () {
            
            let cookie = data.cookie.getCookie(process.env.VUE_APP_COOKIE_NAME_LOGIN_HISOTRY);
            this.id = data.cookie.getCookie(process.env.VUE_APP_COOKIE_NAME_LOGIN);
            this.pw = '';
            this.isLoginCookie = ! data.isNull(cookie) ? Boolean(cookie) : false;
        },
        keyPress_handle : function (e){

            if(!this.validation.id) {
                this.validation.msg = '아이디를 입력해주세요.(6자 이상)';
            }
            if(!this.validation.pw) {
                this.validation.msg = '패스워드를 입력해주세요.(8자 이상)';
            }

            if(!this.validation.pw || !this.validation.pw) {
                alert.showMessage({ vueObject : this, type : 'error', message : this.validation.msg });
            }
            else {

                this.$store.dispatch('app_login',{ id : this.id , pw : this.pw})
                .then( res =>{
                    let { authType} = res;
                    if(authType === 'Auth'){
                        setTimeout(()=>{
                            this.$router.push({ path : '/todo'})
                        },50)
                    }
                    else if(authType === 'NoAuth'){
                        alert.showMessage({ 
                                vueObject : this ,
                                type : 'error' , 
                                message : '비밀번호 혹은 아이디를 확인해주세요.' });
                    }
                })
                .catch( err => {
                    alert.showMessage({ 
                            vueObject : this ,
                            type : 'error' , 
                            message : err });
                })
            }
        },
        logout(){
            this.$store.dispatch('app_logout').then(()=>{
                this.init();
            })
        },
        showSignupModal (){
            this.showModal = false;
             setTimeout(()=>{
                this.showModal = true
            }, 1); 
        },
        signupModal(){
        },
    },
    watch : {
        pw(val, olbVal){
            if((data.isNull(this.pw) || this.pw.length < 8))
                this.validation.pw = false;
            else 
                this.validation.pw = true;
        },
        id(val, olbVal){
            if((data.isNull(this.id) || this.id.length < 6))
                this.validation.id = false;
            else 
                this.validation.id = true;
        }
    }
}
</script>


<style scoped>  

.not-valide{
    border: #F44336 1px solid !important;
}

.blurred-box-smail {
    height: 170px !important;
    top: calc(50% - 85px) !important;
}

.blurred-box{
    position: absolute;
    width: 250px;
    height: 300px;
    top: calc(50% - 150px);
    left: calc(50% - 125px);
    background: inherit;
    border-radius: 2px;
    overflow: hidden;
    text-align: center;
    z-index: 1;
}

.blurred-box:after{
 content: '';
 width: 300px;
 height: 300px;
 background: inherit; 
 position: absolute;
 left: -25px;
 right: 0;
 top: -25px;  
 bottom: 0;
 box-shadow: inset 0 0 0 200px rgba(255,255,255,0.05);
 filter: blur(10px);
}
.login_help{
    font-size: 13px;
}

.el-input__inner{
    background-color: #ffffff8c;
}
.user-login-box {
    position: relative;
    margin-top: 50px;
    text-align: center;
    z-index: 1;
}
.user-icon-outer{
    width: 110px;
    height: 110px;
    border: 3px solid #fff;
    border-radius: 50%;
    padding: 3px;
    transition: all 1s;
}
.user-icon {
    display: inline-block;
    width: 100%;
    height: 100%;
    border-radius: 50%;
    background-size: contain;
}

</style>