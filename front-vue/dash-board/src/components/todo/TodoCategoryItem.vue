<template>
    <div class="folder-item" >
        <span class="cursor-pointer" @click="()=>{handleClick(cloneFolder)}">
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

const name = 'TodoCategoryItem';

export default {
    name ,
    data(){
        return {
            cloneFolder : {},
        }
    },
    props : {
        folder : Object,
        handleClick : Function
    },
    mounted(){
        this.cloneFolder = this.folder;
    },
}
</script>