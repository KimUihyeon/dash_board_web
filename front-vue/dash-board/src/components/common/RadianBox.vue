<template>
    <div v-bind:style="'height:'+ height">
        <div class="todo-warp">
            <div class="todo-container" 
                v-loading="isLoading"
                element-loading-text="로딩중..."
                element-loading-background="#21212185"
                v-bind:style="getStyle">
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
    height: calc(100% - 15px);
    position: absolute;
    background: #21212185;
    border-radius: 20px;
    text-align: left;
    display: flex;
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
        padding : String,
        right : String,
        isLoading : Boolean,
        margin : String,
    },
    computed : {
        getStyle (){
            let style = {
                'max-width': this.maxWidth,
                'width' : this.width,
                'padding' : this.padding,
                'right' : this.right,
                'margin' : this.margin
            }
            let styleStr = '';
            for(let prop in style){
                styleStr += prop + ':' + style[prop] + ';';
            }
            return styleStr;
        }
    }
}
</script>