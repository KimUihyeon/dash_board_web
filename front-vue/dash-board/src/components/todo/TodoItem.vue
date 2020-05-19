<template>
    <div>
        <div
            @mouseover="mouse_handle('hover')" 
            @mouseout="mouse_handle('out')">

            <div>
                <div class="todo" v-bind:class="isHover ? 'todo-hover' : ''">
                    <div  class="todo-left">
                                            
                        <span class="chk-span" @click="changed_handle">
                            <i 
                                v-show="cloneItem.todoComplete"
                                class="el-icon-check todo-center"
                                style="font-size:24px"></i>
                            <i 
                                v-show="cloneItem.todoComplete === false"
                                class="el-icon-minus todo-center"
                                style="font-size:24px"></i>
                            

                        </span>
                        <span class="todo-title-box" @click="click_handle('toggleMemo')">
                            <span class="todo-center" 
                            v-bind:class="cloneItem.todoComplete ? 'complate-todo' : ''" >{{cloneItem.title}}</span>
                        </span>
                    </div>
                    <div class="todo-right">
                        <button @click="click_handle('delete')" class="el-icon-delete todo-center" ></button>
                    </div>

                </div>
            </div>

        </div>
    </div>
</template>

<style scoped>
.todo{
    display:flex;
    height: 60px;
    background: rgba(255, 255, 255, 0.461);
    transition: all 0.1s;
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
    width: 20px;
    height: 20px;
    margin: 20px;
    position: relative;
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
        }
    },
    props : {
        item : Object,
    },
    mounted(){
        this.cloneItem = this.item;
    },
    methods:{
        click_handle(elementType){

            if(elementType === 'delete'){
                this.deleteTodoProcess();
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
            this.cloneItem.todoComplete = !this.cloneItem.todoComplete;

            let todoItem = this.cloneItem;
            this.$store.dispatch('patch_todo', { todoItem }).catch(err=>{
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


<style scoped>
.complate-todo {
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