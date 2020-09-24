<template>
    <div>
        <div v-for="(item) in items"  v-bind:key="item.id" 
            class="todo-space padding-5">
            <TodoItem v-bind:item="item"/>
        </div>
    </div>
</template>

<script>
import TodoItem from "./TodoItem";

const name = 'TodoList';
const components = { TodoItem };
const props = {
    items : Array
}

export default {
    name,
    components,
    props,

    data(){
        return {
            todoTitle : '',
            todoMemo : '',
        }
    }
}
</script>