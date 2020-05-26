<template>
    <div>
        <div v-for="folder in folders" :key="folder.id">
            <TodoCategoryItem 
                :folder="folder"
                :handleClick='handleClick'/>
        </div>
    </div>
</template>

<script>
import TodoCategoryItem from "./TodoCategoryItem";

const name = 'TodoCategoryList';
const components = { TodoCategoryItem };

export default {
    name,
    components,
    props : {
        folders  : Array,
        handleClick : Function,
    }
}
</script>