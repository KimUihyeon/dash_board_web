<template>
    <div class="folder-item padding-5 font-size-14" >
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