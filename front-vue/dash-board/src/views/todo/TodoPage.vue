<template>
    <div>
        <div v-if="!isLoading">
            로딩중
        </div>

        <TodoList 
            v-else
            v-bind:items="getTodoList"/>

        <TodoForm></TodoForm>
        <button @click="test">123123123</button>
    </div>
</template>

<script>
import TodoList from "../../components/todo/TodoList";
import TodoForm from "../../components/todo/TodoForm";
export default {
    name : 'TodoPage',
    data() {
        return {
            isLoading : true,
        }
    },
    components : {
        TodoList
    },
    mounted(){
            this.isLoading = false;

            /** Todo List 다운로드 */
            this.$store.dispatch('todoListDownload');
            this.isLoading = true;
    },
    computed : {
        getTodoList(){
            return this.$store.getters.getTodoList;
        }
    },
    methods:{
        test(){
            console.log(this.$store.getters.getTodoList);
            console.log(this.$store.state.todo.todoList);
        },
    }
}
</script>