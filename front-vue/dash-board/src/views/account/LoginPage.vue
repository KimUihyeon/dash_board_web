<template>
    <div class="home">
        <div class="blurred-box">
            <div class="user-login-box">
                <div class="login-components">
                    <Login :showSiugnupHandle="showSignupModal" />
                </div>

                <div class="sns-login-container">
                    <SnsLoginButton
                        v-for="(sns, index) in snsComponents"
                        v-bind:key="index"
                        :size="snsSize"
                        :margin="snsMargin"
                        :iconClass="sns.icon"
                        :clickHandle="sns.click"
                    ></SnsLoginButton>
                </div>
            </div>
        </div>
        <Signup :submitHandle="() => {}" :showModal="showSignUp" />
    </div>
</template>

<script>
// @ is an alias to /src
import Login from '../../components/account/Login';
import Signup from '../../components/account/Signup';
import SnsLoginButton from '../../components/account/SnsLoginButton';

import { alert } from '../../util';

const name = 'LoginPage';
const components = { Login, Signup, SnsLoginButton };

export default {
    name,
    components,
    data() {
        return {
            showSignUp: false,
            snsSize: '35px',
            snsMargin: '10px',
            snsComponents: [
                { icon: 'kakao-login-icon', click: this.kakaoLogin },
                { icon: 'naver-login-icon', click: this.naverLogin },
                { icon: 'google-login-icon', click: this.googleLogin },
            ],
        };
    },
    methods: {
        kakaoLogin() {
            alert.elMessageBox({ vueObject: this, type: 'info', message: 'kakao 로그인' });
        },
        naverLogin() {
            alert.elMessageBox({ vueObject: this, type: 'success', message: 'naver 로그인' });
        },
        googleLogin() {
            alert.elMessageBox({ vueObject: this, type: 'error', message: 'google 로그인' });
        },
        showSignupModal() {
            this.showSignUp = false;
            setTimeout(() => {
                this.showSignUp = true;
            }, 1);
        },
    },
};
</script>

<style scoped>
.sns-login-container {
    margin: 0 auto;
}
.login-components {
    margin: 5px auto;
}
.not-valide {
    border: #f44336 1px solid !important;
}

.blurred-box-smail {
    height: 170px !important;
    top: calc(50% - 85px) !important;
}

.blurred-box {
    position: absolute;
    width: 250px;
    height: 430px;
    top: calc(50% - 215px);
    left: calc(50% - 125px);
    background: inherit;
    border-radius: 2px;
    overflow: hidden;
    text-align: center;
    z-index: 1;
    background: #00000024;
}

.blurred-box:after {
    content: '';
    width: 300px;
    height: 430px;
    background: inherit;
    position: absolute;
    left: -25px;
    right: 0;
    top: -25px;
    bottom: 0;
    box-shadow: inset 0 0 0 200px rgba(255, 255, 255, 0.05);
    filter: blur(10px);
}
.login_help {
    font-size: 13px;
    color: white;
}

.el-input__inner {
    background-color: #ffffff8c;
}
.user-login-box {
    position: relative;
    margin-top: 50px;
    text-align: center;
    z-index: 1;
}
</style>
