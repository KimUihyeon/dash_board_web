<template>
    <div class="margin-r-5 margin-t-5">
        <div class="tag-item-container padding-5" :class="isEditMode ? 'edit-mode-container': ''">
            <span v-show="isEditMode">
                <div class="edit-title-area tag-name-color display-f margin-b-3 padding-b-5" >
                    <el-input 
                            @keydown.enter.native="updateProcess" class="font-size-14 margin-l-3 margin-r-3" maxlength="10" size="mini" v-model="edit.name"></el-input>
                    
                    <span class="color-picker">
                        <el-color-picker v-model="edit.color" size="mini"></el-color-picker>
                    </span>
                </div>
                <div class="tag-item-button-box text-align-r">
                    
                    <el-tooltip class="item" effect="dark" :content="'삭제'">
                        <span class="el-icon-delete color-red margin-r-5" @click="deleteProcess"></span>
                    </el-tooltip>
                    
                    <el-tooltip class="item" :content="'저장'">
                        <span class="el-icon-check color-green" @click="updateProcess"></span>
                    </el-tooltip>
                    
                    
                    <el-tooltip class="item" effect="dark" :content="'닫기'">
                        <span class="el-icon-close color-red" @click="readMode"></span>
                    </el-tooltip>
                </div>
            </span>
            <span v-show="!isEditMode"  class="tag-name-color display-f">
                <span class="display-b width-full display-f">
                    <el-checkbox  size="medium" @change="(v)=>{ onCheckChanged(v,id)}" v-bind:checked="checked"></el-checkbox>
                    <span class="text-dot-dot-dot font-size-14 margin-l-5 margin-r-5" @dblclick="editMode">{{value}}</span>
                </span>
                
                <el-color-picker v-model="edit.color" size="mini" @change="colorChange"></el-color-picker>
            </span>
            <div class="tag-item-button-box">
                <span v-show="!isEditMode">
                </span>
            </div>
        </div>
    </div>
</template>

<script>
import { date } from '../../util';
import { alert } from '../../util/Alert';
import { delay } from '../../util/Delay';
let name = 'CalendarTagItem';
let props = { 
    id : Number,
    value : String,
    checked : Boolean,
    color : {
        type : String, 
        default : '#fff'
    },
    onCheckChanged : {
        type :  Function,
        default : (a)=>{}
    },
    deleteClickHandle : { 
        type :  Function,
        default : (a)=>{}
    },
    updateSubmitHandle : { 
        type :  Function,
        default : (a)=>{}
    },
};
export default {
    name,
    props,
    data()  {
        return {
            isEditMode : false,
            edit:{
                name : '',
                color : '',
            }
        }
    },
    mounted(){ this.propertySync(); },
    methods : {
        propertySync (){ this.edit.color = this.color; this.edit.name = this.value; },
        editMode() { this.propertySync(); this.isEditMode = true; },
        readMode() { this.propertySync(); this.isEditMode = false; },
        deleteProcess(){ 
            this.confirm(
                '삭제하시겠습니까?' ,
                '삭제하기', 
                ()=>{ 
                    this.readMode();
                    this.deleteClickHandle({ id : this.id });
                }
            )
        },
        colorChange(c){ 
            this.updateSubmitHandle({ id : this.id, color : this.edit.color});
            this.readMode();
        },
        updateProcess(){
            this.updateSubmitHandle({ id : this.id, title : this.edit.name});
            this.readMode();
        },
        
        confirm(confirmMsg, title , okCallback) {
            alert.elConfirm(
                 {
                     confirmMsg, title,
                     vueObject : this,
                     type : 'Warning' ,
                     okCallback : ()=>{ okCallback()},
                     cancelCallback : ()=>{ this.readMode();}  
                }
            );
        },
        alert(message){ alert.elMessageBox({ vueObject : this , type :'success' , message  });},
    },
    watch : {
        color : function (v){
            this.propertySync();
        }
    }
}
</script>

<style  scoped>
.tag-item-container{
    position: relative;
    border-radius: 10px;
    box-sizing: border-box;
}
.edit-mode-container{
    border: 1px solid #00000073;
    background: #fff;
}
.text-dot-dot-dot{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    width: 100px;
    flex: 1;
    overflow: hidden;
    display: block;
}
.edit-title-area{
    border-bottom: 1px solid #000;
}
.item{
    padding-right: 3px;
    padding-left: 3px;
    margin-right: 3px;
    margin-left: 3px;
}
.tag-item-button-box{
    top: 0;
    right: 0;
    height: 100%;
}
.tag-name-color{
    align-items: center;
}
.tag-name-color > div { flex: 1;}
.color-picker, .color-picker > span{ height: 30px;}
</style>

<style>
.el-color-picker__mask{
    cursor: pointer !important;
}
.is-disabled  > .el-color-picker__mask { background-color : rgba(255,255,255,.0) !important ; }
</style>