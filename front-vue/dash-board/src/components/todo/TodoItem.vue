<template>
    <div>
        <div
            @mouseover="mouse_handle('hover')" 
            @mouseout="mouse_handle('out')">

            <v-card
                class="mx-auto"
                max-width="350"
                :shaped="true"
                :elevation="isHover ? 0 : 3"
                outlined>

                <v-list-item three-line>
                    <v-list-item-content>
                        <!-- <div class="overline mb-4 text-left">type</div> -->
                        <v-list-item-title class="headline text-left">
                            <span>
                                <el-checkbox 
                                    v-model="cloneItem.todoComplate" 
                                    v-on:change="changed_handle"></el-checkbox>
                                <!-- <input
                                    type="checkbox" 
                                    v-model="cloneItem.todoComplate" 
                                    v-on:change="changed_handle"/> -->
                            </span>
                            <span
                                v-bind:class="cloneItem.todoComplate ? 'complate-todo' : ''" 
                                @click="click_handle('toggleMemo')">{{cloneItem.title}}</span>
                        </v-list-item-title>

                        <div class="text-left" v-show="this.isMemoOpen">
                            {{cloneItem.memo}}
                        </div>

                    </v-list-item-content>

                    <v-card-actions
                        class="fade-effect-tartget"
                        v-bind:class="isHover ? 'fade-effect-active' : ''">
                        <button @click="click_handle('delete')" class="el-icon-delete" ></button>
                    </v-card-actions>

                </v-list-item>         
            </v-card>
        </div>
    </div>
</template>

<style scoped>

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
            console.log( this.cloneItem );
            this.$store.dispatch('todoItemUpdate', { todoItem : this.cloneItem});
        },
        deleteTodoProcess(){

            let okCallback = () =>{
                alert.showMessage(this, 'info', '123123123');
                this.$store.dispatch('todoItemDelete', { todoItem : this.cloneItem});
                alert.showMessage(this, 'success', '삭제되었습니다.');
            }
            let cancleCallback = () => {
                alert.showMessage(this, 'info', '취소');
            }
            console.log('ttttt');

            alert.showConfirm(this, null, '해당 Todo를 삭제하시겠습니까?', '할일 삭제', 
                okCallback,  cancleCallback);
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