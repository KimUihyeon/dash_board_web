<template>
    <div class="folder-add-container display-f" :style="continerStyle">
        <div class="folder-puls-button display-lb text-align-c">
            <i class='el-icon-plus icon' :style="iconStyle"></i>
        </div>
        <div class="folder-add-input flex-1">
            <input type="txt" 
                v-model="keyWord" 
                :style='inputStyle'
                :readonly="isReadOnly"
                @keydown.enter="(e)=>{
                    inputEnterKeyPress_handle(e, { keyWord });
                    keyWord = '';
                }" 
                :placeholder="placeholder">
        </div>
    </div>
</template>

<style scoped>
.folder-add-container{
    position: relative;
}
.folder-puls-button{
    width: 30px;
    height: 30px;
    align-self: center;
}
.folder-add-input input {
    width: 100%;
    height: 100%;
    display: block;
}
.icon{
    vertical-align: middle;
    position: relative;
}
input::placeholder {
  color: white;
}
input:focus {
    outline:none;
}

</style>

<script>

import { data } from "../../util";
const name = 'ItmeAdd';

export default {
    name,
    props : {
        placeholder : String ,
        iconColor : String,
        iconSize : String,
        fontColor : String,
        fontSize : String,
        height : String,
        width : String,
        backgroundColor : String ,
        marginTop : String,
        isReadOnly : Boolean,

        inputEnterKeyPress_handle : Function,
    },
    data(){
        return {
            keyWord : '',
            isEmpty : false,
            continerStyle : '',
            iconStyle : '',
            inputStyle : '',
        }
    },
    watch : {
        keyWord(v){
            this.isEmpty = data.isNull(v);
        }
    },
    mounted(){
        this.continerStyle = this.getContainerStyle();
        this.iconStyle = this.getIconStyle();
        this.inputStyle = this.getInputStyle();
    },
    methods: {
        getContainerStyle (){
            let style = '';
            if(!data.isNull(this.height)){
                style += `height : ${this.height}; `;
            }
            if(!data.isNull(this.width)){
                style += `width : ${this.width}; `;
            }
            if(!data.isNull(this.backgroundColor)){
                style += `backgroundColor : ${this.backgroundColor}; `;
            }
            if(!data.isNull(this.marginTop)){
                style += `margin-top : ${this.marginTop}; `
            }
            
            return style;
        },
        getIconStyle(){
            let style = '';
            
            if(!data.isNull(this.iconColor)){
                style += `color : ${this.iconColor}; `;
            }
            if(!data.isNull(this.iconSize)){
                style += `font-size : ${this.iconSize } ;`;
            }
            
            return style;

        },
        getInputStyle(){
            let style = '';
            
            if(!data.isNull(this.fontColor)){
                style += `color : ${this.fontColor}; `;
            }
            if(!data.isNull(this.fontSize)){
                style += `font-size : ${this.fontSize } ;`;
            }

            return style;
        }
    }
}
</script>