<template>
    <el-dialog title="Shipping address" :visible.sync="show" :close="()=>{showModal()}" :close-on-click-modal="false">
        <div>
            {{validation.msg}}
        </div>
        <div>
            <el-input 
                type="text" 
                @keydown.enter.native="enterKeyHandle('id')"
                placeholder="id" 
                ref="id"
                v-model="id"
                size="small"></el-input>
        </div>
        <div>
            <el-input 
                type="password" 
                @keydown.enter.native="enterKeyHandle('pw')"
                placeholder="pw" 
                ref="pw"
                v-model="pw"
                size="small"></el-input>
        </div>
        <div>
            <el-input 
                type="password" 
                @keydown.enter.native="enterKeyHandle('pwConfirm')"
                placeholder="PassWord 확인" 
                ref="pwConfirm"
                v-model="pwConfirm"
                size="small"></el-input>
        </div>
        <div>
            <el-input 
                type="password" 
                @keydown.enter.native="enterKeyHandle('name')"
                placeholder="name" 
                ref="name"
                v-model="name"
                size="small"></el-input>
        </div>
    </el-dialog>
</template>


<script>

import { data, alert , rest } from "../../util";
import { accountService } from '../../services'


const name = 'Signup';
const props = { showModal : Boolean }

export default {
    name, 
    props, 
    data(){
        return {
            id : '',
            pw : '',
            pwConfirm : '',
            name : '',
            valide : {
                id : false,
                pw : false,
                name : false,
            },
            show : false,
            isIdConfirm : false,
        }
    },
    methods : {
        init(){
            this.id = '';
            this.pw = '';
            this.isIdConfirm = false;
        },
        enterKeyHandle(elType){
            if(elType === 'id'){
                this.focusing(this.$refs.pw);
            }else if(elType === 'pw'){
                this.focusing(this.$refs.pwConfirm);
            }else if(elType === 'pwConfirm'){
                this.focusing(this.$refs.name);
            }else if(elType === 'name'){
                if(this.validation()){

                };
            }
        },
        idEnterKeyHandle(){
        },
        pwEnterKeyHandle(){
            this.focusing(this.$refs.name);
            if(this.validation()){
                this.submit();
            }
        },
        nameEnterKeyHandle(){

        },
        submit(){
            accountService.signup(this.id, this.pw).then(res =>{
                console.log(res);
            });
        },
        focusing(element){
            element.focus();
        },
        alert( type , message){
            alert.showMessage({
                vueObject : this ,
                type,
                message  
            });
        },
        validation(){
            console.log(data.validation(this.id , 'text', [5,20]))
            console.log(this.id.length);
            if(!data.validation(this.id , 'text', [5,20])) {
                this.alert('error', '아이디는 6자이상 20자 미만입니다.')
                return false;
            }
            if(!data.validation(this.pw, 'text', [5,14])){
                this.alert('error', '패스워드는 6자이상 14자 미만입니다.')
                return false;
            }
            if(this.pw !== this.pwConfirm){
                this.alert('error', '패스워드 확인이 일치하지 않습니다.')
                return false;
            }
            if(!data.validation(this.name, 'text', [1,10])){
                this.alert('error', '이름은 2자이상 10자 미만입니다.')
                return false;
            }
            return true;
        }
    },
    watch : {
        showModal(v){
            this.show = v;
            this.init();
        }
    }
}
</script>