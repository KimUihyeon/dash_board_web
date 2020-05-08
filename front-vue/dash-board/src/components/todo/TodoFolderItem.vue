<template>
    <div class="folder-item" >
        <span class="cursor-pointer" @click="click_handle">
            <span :style="'color : ' + cloneFolder.iconColor">
                <i v-bind:class='cloneFolder.icon'></i>
            </span>
            <span :style="'color :' + cloneFolder.fontColor">
                {{cloneFolder.title}}
            </span>
        </span>
    </div>
</template>


<style scoped>
.folder-item{
    padding: 4px;
    font-size: 14px;
}
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
    },
    methods: {
        click_handle(){
            let { param , id } = this.cloneFolder;
            this.$router.push({ path: 'todo', query: { type : param , id : id }});
        }
    }
}
</script>