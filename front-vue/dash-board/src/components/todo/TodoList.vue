<template>
    <div>
        <div v-for="(item) in items" v-bind:key="item.id">
            <TodoItem v-bind:item="item"></TodoItem>
        </div>
    </div>
</template>

<style scoped>

</style>

<script>
import TodoItem from "./TodoItem";
export default {
    name : 'TodoList',
    props :{
        items : Array
    },
    components : {
        TodoItem,
    }
    
}
</script>