<template>
    <div>
        <div v-if="!isLoading">
            로딩중
        </div>

        <TodoList 
            v-else
            v-bind:items="getTodoList"/>

        <button @click="test">123123123</button>
    </div>
</template>

<script>
import TodoList from "../../components/todo/TodoList";
export default {
    name : 'TodoPage',
    data() {
        return {
            isLoading : true,
            todoItmes : [],
        }
    },
    components : {
        TodoList,
    },
    methods:{
        test(){
            console.log(this.$store.getters.getTodoList);
            console.log(this.$store.state.todo.todoList);
        },
    },
    computed : {
        getTodoList(){
            this.isLoading = false;
            this.$store.dispatch('todoListDownload');

            this.isLoading = true;
            return this.$store.getters.getTodoList;
        }
    }
}
</script>