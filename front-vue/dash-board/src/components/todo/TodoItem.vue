<template>
    <div>
        <div @mouseover="hoverOver" @mouseout="hoverOut">
            <v-card
                class="mx-auto"
                max-width="350"
                :shaped="true"
                :elevation="isHover ? 0 : 3"
                outlined
            >
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
                                @click="toggleMemo">{{cloneItem.title}}</span>
                        </v-list-item-title>

                        <div class="text-left" v-show="this.isMemoOpen">
                            {{cloneItem.memo}}
                        </div>

                    </v-list-item-content>

                    <v-card-actions
                        class="fade-effect-tartget"
                        v-bind:class="isHover ? 'fade-effect-active' : ''">
                        <v-btn text>Button</v-btn>
                        <button @click="deleteItem" class="el-icon-delete" ></button>
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
            elevation : 0,
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
        toggleMemo(){
            this.isMemoOpen = !this.isMemoOpen;
        },
        deleteItem(){
            alert.showConfirm(this, null, '해당 Todo를 삭제하시겠습니까?', '할일 삭제', 
            ()=>{
                alert.showMessage(this, 'success', '성공');
            },
            ()=>{
                alert.showMessage(this, 'info', '취소');
            })
        },
        hoverOver(){
            this.isHover = true;
        },
        hoverOut(){
            this.isHover = false;  
        },
        changed_handle(){
            this.$store.dispatch('todoItemUpdate', { todoItem : this.cloneItem});
        },
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