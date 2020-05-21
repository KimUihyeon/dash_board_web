<template>
    <div v-bind:style="'height:'+ height">
        <div class="todo-warp">
            <div class="todo-container" 
                v-bind:style="'max-width:'+ maxWidth +'; width:'+ width">
                <slot></slot>
            </div>
        </div>
    </div>

</template>


<style scoped>

.todo-warp{
    height: 100%;
    width: 100%;
    position: relative;
}
.todo-container{
    padding: 1%;
    height: calc(100% - 15px);
    position: absolute;
    right: 15px;
    background: #21212185;
    border-radius: 20px;
    text-align: left;
    /* overflow: hidden; */
}


@media all and (max-width:768px) {
.todo-container{
    padding: 1%;
    height: calc(100% - 15px);
    position: absolute;
    right: 15px;
    background: #21212185;
    border-radius: 20px;
    text-align: left;
    /* overflow: hidden; */
}
}

</style>

<script>
const name = 'RadianBox'

export default {
    name,
    props : {
        title : String,
        height : String,
        width : String,
        maxWidth : String,
    }
}
</script>