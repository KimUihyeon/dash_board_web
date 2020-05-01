<template>
    <div>
        <input 
            type='input'
            v-model="todoItem.title"
            @keydown.enter='enterKeyPress_handle'
            />
    </div>
</template>


<style scoped>
</style>

<script>
import { alert , date } from "../../util";
export default {
    name : 'TodoItemAddInput',
    data(){
        return {
            todoItem : {},
            title : '',
        }
    },
    mounted(){
        this.clearTodoItem();
    },
    methods:{
        enterKeyPress_handle (e){

            this.addTodoItem();
            this.clearTodoItem();
        },
        addTodoItem(){
            this.todoItem.date = date.now();
            this.$store.dispatch('todoItemUpdate', { todoItem : this.todoItem });
            alert.showMessage(this, 'success', '추가되었습니다.' )
        },
        clearTodoItem(){
            this.todoItem = {
                id : -1,
                title : '',
                memo : '',
                date : date.now(),
                todoComplate : false,
            }
        }
    }
}
</script>