<template>
    <div>
        <span>{{cloneFolder.icon}}</span>
        <span>*</span>
        <span>{{cloneFolder.title}}</span>
    </div>
</template>


<style scoped>
</style>

<script>

const name = 'TodoFolderItem';

export default {
    name ,
    data(){
        return {
            cloneFolder : {},
        }
    },
    props : {
        folder : Object
    },
    mounted(){
        this.cloneFolder = this.folder;
    }
}
</script>