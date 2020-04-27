
<template>
    <router-link v-bind:to=to>
        <span class="item-space">
            <el-badge :value=value class="item" v-if='value !== 0'>
                <!-- <i v-bind:Class="icon + ' icon-custom'" ></i> -->
                <span class="name">{{name}}</span>
            </el-badge>
            <el-badge class="item" v-else>
                <!-- <i v-bind:Class="icon + ' icon-custom'" ></i> -->
                <span class="name">{{name}}</span>
            </el-badge>
        </span>
    </router-link>
</template>


<script>
export default {

    name : 'RouterItem',
    props : {
        icon : String,
        to : String,
        name : String,
        value : Number,
        padding : Number
    }
    
}
</script>


<style scoped>
.name {
}
.item-space{
    padding: 5px;
}
.icon-custom{
  color: #fff;
  font-size: 26px;
}
</style>