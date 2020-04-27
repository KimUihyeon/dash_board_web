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
                <!-- <el-input placeholder="Please input" v-model="pw"></el-input> -->
                <div class="inputBox" v-show="!isLoginCookie">
                    <input 
                        class="user-password user-login-input" 
                        type="text" 
                        v-bind:class="validation.id ? '' : 'not-valide' "
                        @keydown.enter='keyPress_handle' 
                        placeholder="ID" 
                        v-model="id" 
                    />
                </div>
                <div class="inputBox" >
                    <input 
                        class="user-password user-login-input"
                        v-bind:class="validation.pw ? '' : 'not-valide' "
                        type="password" 
                        @keydown.enter='keyPress_handle'
                        placeholder="pw" 
                        v-model="pw" 
                    />
                </div>
                <div v-show="isLoginCookie">
                    <span class="login_help" @click="logout">logout</span>
                </div>

                <div v-show="!isLoginCookie">
                    <span class="login_help">signup</span>
                    <span class="login_help"> | </span>
                    <span class="login_help">find ID</span>
                </div>
            </div>
        </div>
    </div>
</template>


<script>
import { data , alert } from "../../util";
export default {
    name : 'Login',
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
            }
        }
    },
    mounted(){
        this.init();
    },
    methods : {
        init () {

            let cookie = data.getCookie('dash_login_history');
            this.id = data.getCookie('dash_last_login');
            this.pw = '';
            this.isLoginCookie = !data.isNull(cookie) ? Boolean(cookie) : false;
        },
        keyPress_handle : function (e){

            if(!this.validation.id) {
                this.validation.msg = '아이디를 입력해주세요.(6자 이상)';
            }
            if(!this.validation.pw) {
                this.validation.msg = '패스워드를 입력해주세요.(8자 이상)';
            }

            if(!this.validation.pw || !this.validation.pw) {
                alert.showMessage(this, 'error' , this.validation.msg );
            }
            else {
                this.$router.push('/main');

                data.createCookie('dash_login_history',true,365);
                data.createCookie('dash_last_login',this.id,365);
            }
        },
        logout(){
            data.removeCookie('dash_login_history');
            data.removeCookie('dash_last_login');

            this.init();
        }
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