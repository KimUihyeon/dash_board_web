<template>
    <div>
        <div class="blurred-box">
            <div class="user-login-box">
                <span class="user-icon-outer" v-show="isHistory" v-bind:class="!isHistory ? 'blurred-box-smail':''">
                    <span class="user-icon"></span>
                </span>
                <div class="user-name" v-show="isHistory" >Kim Uihyeon</div>
                <!-- <el-input placeholder="Please input" v-model="pw"></el-input> -->
                <div class="inputBox">
                    <input class="user-password" type="text" @keypress="keyPress_handle" placeholder="ID" v-model="id" />
                </div>
                <div class="inputBox">
                    <input class="user-password" type="text" @keypress="keyPress_handle" placeholder="pw" v-model="pw" />
                </div>

                <div v-show="isHistory">
                    <span class="login_help">logout</span>
                </div>

                <div v-show="!isHistory">
                    <span class="login_help">signup</span>
                    <span class="login_help"> | </span>
                    <span class="login_help">find ID</span>
                </div>
            </div>
        </div>
    </div>
</template>


<script>
export default {
    name : 'Login',
    props : ['isHistory' , 'autoLogin'],
    data(){
        return {
            pw : '',
            id : '',
        }
    },
    mounted(){
        this.pw = '';
        console.log(this.autoLogin)
    },
    methods : {
        keyPress_handle : function (e){
            console.log(e)
        }
    }
}
</script>


<style scoped>  

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
    background-image: url(https://avatars3.githubusercontent.com/u/30382976?s=460&u=d8c6040…&v=4);
}

</style>