<template>
    <span>
        <span class="switchContext">
            {{switchValue}}
        </span>
        <span v-on:mouseover='aaa' style="
    display: inline-block;">
            <el-switch
                v-model="switchValue"
                v-bind:active-color="activeColor"
                v-bind:inactive-color="inactiveColor"
                v-bind:active-value="activeValue"
                v-bind:inactive-value="inactiveValue">
            </el-switch>
        </span>
    </span>
</template>

<script>
export default {
    name : 'CustomSwichBar',
    props : {
        initValue : String ,
        inactiveValue : String ,
        activeValue : String,
        inactiveColor : String ,
        activeColor : String,
        changedEvent : Function
    },
    mounted(){
        this.switchValue = this.defaultValue;
    },
    data (){
        return {
            switchValue : '' 
        }
    },
    methods:{
        aaa (){
            console.log('aaa');
        }
    },
    watch : {
        switchValue(v){
            this.changedEvent(v);
        }
    }
}
</script>

<style scoped>
    .active{
        opacity: 1;
        transition: 0.5s all;
    }
    .continer{
        position: relative;
        z-index: 99;
    }
    .item{
        position: absolute;
        display: block;
        text-align: center;
        width: 100%;
    }
    .switchContext{
        opacity: 0.3;
    }
</style>