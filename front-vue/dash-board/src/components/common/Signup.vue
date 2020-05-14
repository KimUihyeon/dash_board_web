<template>
    <el-dialog title="Shipping address" :visible.sync="show" :close="()=>{showModal()}">
        <div>
            {{validation.msg}}
        </div>
        <div>
            <input type="text" v-model="id" ref="id" @keypress.enter="idEnterKeyHandle"/>
        </div>
        <div>
            <input type="text" v-model="pw" ref="pw" @keypress.enter="pwEnterKeyHandle">
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
            validation : {
                id : false,
                pw : false,
                msg : '',
            },
            show : false,
            isIdConfirm : false,
        }
    },
    methods : {
        init(){
            this.id = '';
            this.pw = '';
            this.validation = {
                id : false,
                pw : false,
                msg : '',
            };
            this.isIdConfirm = false;
        },
        idEnterKeyHandle(){
            if(this.validation.id){
                this.focusing(this.$refs.pw);
            }
            else {
                this.alert('error', this.validation.msg)
            }
        },
        pwEnterKeyHandle(){
            if(this.validation.id && this.validation.pw){
                this.submit();
            }
            else {
                if(this.validation.id === false ){
                    this.focusing(this.$refs.id);
                }
                else if (this.validation.pw === false){
                    this.focusing(this.$refs.pw);
                }
                this.alert('error', this.validation.msg)
            }
        },
        focusing(element){
            element.focus();
        },
        submit(){
            accountService.signup(this.id, this.pw).then(res =>{
                console.log(res);
            });
        },
        alert( type , message){
            alert.showMessage({
                vueObject : this ,
                type,
                message  
            });
        }
    },
    watch : {
        showModal(v){
            this.show = v;
            this.init();
        },
        id(v){
            if(data.isNull(v) && v.length < 8){
                this.validation.id = false;
                this.validation.msg = 'id는 8글자 이상 이어야 합니다.'
            }
            else {
                this.validation.id = true
            }
        },
        pw(v){
            if(data.isNull(v) && v.length < 8){
                this.validation.pw = false;
                this.validation.msg = '패스워드는 8 이상 이어야 합니다.'
            }
            else {
                this.validation.pw = true;
            }
        }
    }
}
</script>