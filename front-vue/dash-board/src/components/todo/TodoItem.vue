<template>
    <div
        @mouseover="mouse_handle('hover')" 
        @mouseout="mouse_handle('out')">
        <div class="todo" v-bind:class="isHover ? 'todo-hover' : ''">
            <div  class="todo-left">
                                    
                <span class="chk-span cursor-pointer" @click="changed_handle">
                    <div class="chk-span-container">
                        <i 
                            v-bind:class="cloneItem.todoComplete ? 'active' : ''"
                            class="el-icon-check todo-center text-center" 
                            style="font-size:24px"></i>
                        <i 
                            v-bind:class="!cloneItem.todoComplete ? 'active' : ''"
                            class="el-icon-minus todo-center text-center"
                            style="font-size:24px"></i>
                    </div>
                </span>
                <span class="todo-title-box" @click="click_handle('toggleMemo')">
                    <span class="todo-center" 
                    v-bind:class="cloneItem.todoComplete ? 'complete-todo' : ''" >{{cloneItem.title}}</span>
                </span>
            </div>
            <div class="todo-right">
                <button class="el-icon-more continer-open-button" @click='()=>{toggle = true}'></button>
                <!-- <button @click="click_handle('delete')" class="el-icon-delete todo-center" ></button> -->
            </div>
            <div class="button-container" v-bind:class="toggle ? 'button-container-active' : ''">
                <div class="button-box-close" @click="()=>{toggle = false}">
                </div>
                <div class="button-box">
                    <button class='todo-star-button' v-bind:class=" cloneItem.important ? 'el-icon-star-on': 'el-icon-star-off' " @click="reverseImportant()"></button>
                    <button class="todo-delete-button  el-icon-delete" @click="click_handle('delete')"></button>
                    <button class="container-close-button  el-icon-close" @click="()=>{toggle = false}"></button>
                    
                </div>
            </div>

        </div>
    </div>
</template>

<style scoped>
.button-container button{
    font-size: 20px;
    padding: 10px;
    color: white;
}
.button-container .todo-detail-button{
    color: green;
}
.button-container .todo-star-button{
    color: yellow ;
}
.button-container .todo-delete-button{
    color: red;
}
.button-container .container-close-button{
    color: black;
}
.button-box-close{
    background: #000000ba;
    display: flex;    
    height: 100%;
    font-size: 20px;
    flex:1
}
.button-box{
    display: inline-block;
    padding: 10px;
    background: #b3b3b3;
}
.button-container-active{
    left: 0% !important;
}
.button-container{
    position: absolute;
    display: flex;
    top: 0px;
    left: 100%;
    width: 100%;
    height: 100%;
    border-radius: 10px;
    overflow: hidden;
    transition: all 0.5s;
}
.todo{
    position: relative;
    display:flex;
    height: 60px;
    background: rgba(255, 255, 255, 0.461);
    transition: all 0.75s;
    border-radius: 10px;
    overflow: hidden;
}
.todo-hover{
    box-shadow: 5px 5px 10px #000;
}
.todo-left{
    display: flex;    
    height: 100%;
    flex:1
}
.chk-span{
    width: 30px;
    height: 30px;
    /* border: 1px solid; */
    /* border-radius: 50%; */
    margin: 15px;
    overflow: hidden;
    position: relative;
}
.chk-span i{
    opacity: 0;
    transition: all 0.3s;
}
.chk-span .active{
    opacity: 1 !important;
}
.todo-title-box, .chk-span{
    float: left;
    display: inline-block;
    position: relative;
}
.todo-title-box{
    height: 100%;
    width: 100%;
    flex: 1;
}
.todo-center{
    position: absolute;
    width: 100%;
    top: 50%;
    left: 50%;
    text-overflow: ellipsis;
    white-space: nowrap;
    transform: translateX(-50%) translateY(-50%);
}
.todo-right{
    margin: 10px;
    font-size: 15px;
    text-align: center;
}
.todo-right .continer-open-button{
    padding: 12px;
}


.complete-todo {
    text-decoration: line-through !important;
}
.fade-effect-tartget{
    transition: all 1s;
    opacity: 0;
}
.fade-effect-active{
    opacity: 1;
}

</style>

<script>
import { alert } from "../../util";
export default {
    name : 'TodoItem',
    data()  {
        return {
            cloneItem : {},
            isHover: false,
            isMemoOpen : false,
            toggle : false,
        }
    },
    props : {
        item : Object,
    },
    mounted(){
        this.cloneItem = this.item;
    },
    methods:{
        handleToggle(){
            this.toggle = !this.toggle;
        },
        click_handle(elementType){

            if(elementType === 'delete'){
                this.deleteTodoProcess();
            }
            else if(elementType === 'update'){
                this.updateTodoProcess();
            }
            else if (elementType === 'toggleMemo'){
                this.isMemoOpen = !this.isMemoOpen;
            }
        },
        mouse_handle(position){
            if(position === 'hover'){
                this.isHover = true;
            }
            else if(position === 'out'){
                this.isHover = false; 
            }
        },
        changed_handle(){
            let todoItem = {
                ...this.cloneItem,
                todoComplete : !this.cloneItem.todoComplete
            };
            this.$store.dispatch('patch_todo', { todoItem }).then((res)=>{
                this.cloneItem.todoComplete = !this.cloneItem.todoComplete;
            }).catch(err=>{
                alert.elMessageBox({ vueObject : this , type : 'error' , message : err });
            });
        },
        updateTodoProcess(){
            let okCallBack = () => {

            }
        },
        reverseImportant(){

            let todoItem = {
                ...this.cloneItem,
                important : !this.cloneItem.important
            };
            this.$store.dispatch('patch_todo', { todoItem }).then((res)=>{
                this.cloneItem.important = !this.cloneItem.important;
            }).catch(err=>{
                alert.elMessageBox({ vueObject : this , type : 'error' , message : err });
            });
        },
        deleteTodoProcess(){

            let okCallback = () => {
                this.$store.dispatch('remove_todo', { todoItem : this.cloneItem})
                    .then(data=>{
                        alert.elMessageBox({ vueObject : this, type : 'success', message : '삭제되었습니다' });
                    })
                    .catch(err=>{
                        alert.elMessageBox({ vueObject : this, type : 'error', message : err });
                });
            }

            let cancleCallback = () => {
                alert.elMessageBox({ vueObject : this, type : 'info', message : '취소' });
            }

            alert.elConfirm({
                vueObject: this,
                type : 'Warning',
                confirmMsg : '해당 Todo를 삭제하시겠습니까?',
                title : '할일 삭제',
                okCallback : okCallback,
                cancelCallback: cancleCallback });
        }
    }
    
}
</script>