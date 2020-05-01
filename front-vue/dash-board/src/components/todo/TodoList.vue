<template>
    <div>
        <div
            v-for="(item) in items" 
            v-bind:key="item.id"
            class="todo-space">
            <TodoItem v-bind:item="item"></TodoItem>
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
}
</script>