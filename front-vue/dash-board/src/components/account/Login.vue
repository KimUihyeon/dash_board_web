<template>
    <div>
        <div class="icon-continer">
            <span class="user-icon-outer">
                <span class="user-icon login-title-icon"></span>
            </span>
        </div>
        
        <div>
            <el-input type="text" size="small" @keydown.enter.native="keyPressHandle" placeholder="ID" v-model="id"></el-input>
        </div>

        <div class="inputBox">
            <el-input type="password" @keydown.enter.native="keyPressHandle" placeholder="pw" v-model="pw" size="small"></el-input>
        </div>
        <div class="inputBox">
            <el-button size="small" type="primary" class="loginButton" @click="keyPressHandle">login</el-button>
        </div>
        <div class="inputBox">
            <el-button type="success" size="small" class="loginButton" @click="showSiugnupHandle">Signup</el-button>
        </div>
    </div>
</template>

<script>
import { data, alert } from '../../util';
import { accountService } from '../../services';
import { delay } from '../../util/Delay';

const name = 'Login';
const props = {
    showSiugnupHandle: {
        type: Function,
        default: () => {},
    },
}

export default {
    name,
    props,
    data() {
        return {
            pw: '',
            id: '',
            validation: {
                pw: false,
                id: false,
            },
            showSignUp: false,
        };
    },
    mounted() {
        this.cookieSync();
    },
    methods: {
        logout() { this.$store.dispatch('app_logout').then(() => { this.cookieSync(); }); },
        cookieSync(){
            const cookie = data.cookie.getCookie(process.env.VUE_APP_COOKIE_NAME_LOGIN_HISOTRY);
            this.id = data.cookie.getCookie(process.env.VUE_APP_COOKIE_NAME_LOGIN);
            this.pw = '';
        },
        keyPressHandle(e) {
            if(this.isValidation()) {

                const account = {
                    id : this.id,
                    pw : this.pw
                }

                this.$store.dispatch('app_login', account)
                    .then(({authType}) => {
                        if (authType === 'Auth') {
                            delay.immediately(()=>{ this.$router.push({ path: '/todoCategory' }); })
                        } else if (authType === 'NoAuth') {
                            alert.userInfoCheckAlert(this);
                        }
                    })
                    .catch((err) => {
                        alert.serverErrorAlert(this);
                    });
            }
        },
        isValidation (){
            const message = '';
            const type = 'error';
            
            if (!this.validation.id) {
                message = '아이디를 입력해주세요.(6자 이상)';
                alert.elMessageBox({ vueObject: this, type, message });
                return false;
            }
            else if (!this.validation.pw) {
                message = '패스워드를 입력해주세요.(8자 이상, 20자 이하)';
                alert.elMessageBox({ vueObject: this, type, message });
                return false;
            }

            return true;
        }
    },
    watch: {
        pw(val, olbVal) {
            if (data.isNull(this.pw) || this.pw.length < 8) this.validation.pw = false;
            else this.validation.pw = true;
        },
        id(val, olbVal) {
            if (data.isNull(this.id) || this.id.length < 6) this.validation.id = false;
            else this.validation.id = true;
        },
    },
};
</script>

<style scoped>
.icon-continer {
    width: 100px;
    height: 100px;
    margin: 20px auto;
    margin-top: 0px;
}
.loginButton {
    width: 100%;
}

.user-icon-outer {
    width: 100%;
    height: 100%;
    display: block;
    border: 3px solid #fff;
    border-radius: 50%;
    padding: 3px;
    transition: all 1s;
    margin-bottom: 30px;
}
.user-icon {
    display: inline-block;
    width: 100%;
    height: 100%;
    border-radius: 50%;
    background-position: center;
}
</style>
