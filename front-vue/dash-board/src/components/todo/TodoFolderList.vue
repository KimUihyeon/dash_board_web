<template>
    <div>
        <div v-for="folder in folders" :key="folder.id">
            <TodoFolderItem :folder="folder"/>
        </div>
    </div>
</template>

<script>
import TodoFolderItem from "./TodoFolderItem";

const name = 'TodoFolderList';
const components = { TodoFolderItem };

export default {
    name,
    components,
    props : {
        folders  : Array,
    }
}
</script>