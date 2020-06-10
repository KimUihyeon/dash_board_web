<template>
    <div class="home">

        <div class="blurred-box">
            <div class="user-login-box">
            
                <div class="login-components">
                    <Login />
                </div>

                <div>
                    <span class="login_help cursor-pointer" @click="showSignupModal">SignUp</span>
                    <span class="login_help"> | </span>
                    <span class="login_help">find ID</span>
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

const name = 'LoginPage';
const components = { Login, Signup };

export default {
    name,
    components,
    data(){
        return {
            showSignUp : false,
        }
    },
    methods : {

        showSignupModal() {
            console.log('123123');
            this.showSignUp = false;
            setTimeout(() => {
                this.showSignUp = true;
            }, 1);
        },
    }
};
</script>


<style scoped>
.login-components{
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
    height: 350px;
    top: calc(50% - 150px);
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
    height: 350px;
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
