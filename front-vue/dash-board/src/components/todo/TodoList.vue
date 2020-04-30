<template>
    <div>
        <div
            v-for="(item) in items" 
            v-bind:key="item.id"
            class="todo-space">
            <TodoItem v-bind:item="item"></TodoItem>
        </div>
        <div>
            <input type="text" v-model="todoTitle" @keydown.enter="enterKeyPress_handle"/>
        </div>
    </div>
</template>

<style scoped>
.todo-space{
    padding: 5px;
}
</style>

<script>
import TodoItem from "./TodoItem";
export default {
    name : 'TodoList',
    data(){
        return {
            todoTitle : '',
            todoMemo : '',
        }
    },
    props :{
        items : Array
    },
    components : {
        TodoItem,
    },
    methods:{
        enterKeyPress_handle(e){
            this.$store.dis
        }
    }
    
}
</script>