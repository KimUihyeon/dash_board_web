<template>
    <div>
        <div class="icon-continer">
            <span class="user-icon-outer">
                <span class="user-icon login-title-icon"></span>
            </span>
        </div>

        <div>
            <el-input type="text" size="small" @keydown.enter.native="keyPress_handle" placeholder="ID" v-model="id"></el-input>
        </div>

        <div class="inputBox">
            <el-input type="password" @keydown.enter.native="keyPress_handle" placeholder="pw" v-model="pw" size="small"></el-input>
        </div>
        <div class="inputBox">
            <el-button size="small" type="primary" class="loginButton" @click="keyPress_handle">login</el-button>
        </div>
        <div class="inputBox">
            <el-button type="success" size="small" class="loginButton" @click="showSiugnupHandle">Signup</el-button>
        </div>
    </div>
</template>

<script>
import { data, alert } from '../../util';
import { accountService } from '../../services';
import Signup from './Signup';

const name = 'Login';

export default {
    name,
    components: { Signup },
    props: {
        showSiugnupHandle: {
            type: Function,
            default: () => {},
        },
    },
    data() {
        return {
            pw: '',
            id: '',
            isLoginCookie: false,
            isAutoLogin: false,
            validation: {
                pw: false,
                id: false,
                msg: '',
            },
            showSignUp: false,
        };
    },
    mounted() {
        this.init();
    },
    methods: {
        init() {
            let cookie = data.cookie.getCookie(process.env.VUE_APP_COOKIE_NAME_LOGIN_HISOTRY);
            this.id = data.cookie.getCookie(process.env.VUE_APP_COOKIE_NAME_LOGIN);
            this.pw = '';
            this.isLoginCookie = !data.isNull(cookie) ? Boolean(cookie) : false;
        },
        keyPress_handle: function(e) {
            if (!this.validation.id) {
                this.validation.msg = '아이디를 입력해주세요.(6자 이상)';
            }
            if (!this.validation.pw) {
                this.validation.msg = '패스워드를 입력해주세요.(8자 이상, 20자 이하)';
            }

            if (!this.validation.pw || !this.validation.pw) {
                alert.elMessageBox({
                    vueObject: this,
                    type: 'error',
                    message: this.validation.msg,
                });
            } else {
                this.$store
                    .dispatch('app_login', { id: this.id, pw: this.pw })
                    .then((res) => {
                        let { authType } = res;
                        if (authType === 'Auth') {
                            setTimeout(() => {
                                this.$router.push({ path: '/todoCategory' });
                            }, 50);
                        } else if (authType === 'NoAuth') {
                            alert.elMessageBox({
                                vueObject: this,
                                type: 'error',
                                message: '비밀번호 혹은 아이디를 확인해주세요.',
                            });
                        }
                    })
                    .catch((err) => {
                        alert.elMessageBox({
                            vueObject: this,
                            type: 'error',
                            message: err,
                        });
                    });
            }
        },
        logout() {
            this.$store.dispatch('app_logout').then(() => {
                this.init();
            });
        },
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
