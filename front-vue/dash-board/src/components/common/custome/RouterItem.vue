<template>
    <router-link v-bind:to=to>
        <el-tooltip class="item" effect="dark" :content="tooltip" placement="bottom">
            <span class="item-space">
                <el-badge v-if='alert !== 0' :value='alert' class="item" >
                    <i v-bind:Class="icon + ' icon-custom'" ></i>
                    <span class="name">{{name}}</span>
                </el-badge>
                <el-badge class="item" v-else>
                    <i v-bind:Class="icon + ' icon-custom'" ></i>
                    <span class="name">{{name}}</span>
                </el-badge>
            </span>
        </el-tooltip>
    </router-link>
</template>


<script>
export default {

    name : 'RouterItem',
    props : {
        icon : String,
        to : String,
        name : String,
        alert : Number,
        padding : Number,
        tooltip : String,
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