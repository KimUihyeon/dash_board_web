<template>
    <router-link v-bind:to=to>
        <el-tooltip class="item" effect="dark" :content="tooltip" placement="bottom">
            <span class="item-space">
                <el-badge v-if='alert !== 0' :value='alert' class="item" >
                    <div class="icon-box">
                        <i v-bind:Class="icon + ' icon-custom'" ></i>
                    </div>
                    <div>
                        <!-- <span class="name">{{name}}</span> -->
                    </div>
                </el-badge>
                <el-badge class="item" v-else>
                    <div class="icon-box">
                        <!-- <i v-bind:Class="icon + ' icon-custom'" ></i> -->
                    </div>
                    <div>
                        <!-- <span class="name">{{name}}</span> -->
                    </div>
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
.icon-box{
    text-align: center;
}
.name {
    font-size: 16px;
}
.item-space{
    padding: 10px;
}
.icon-custom{
  color: #fff;
  font-size: 26px;
}
.icon-custom{
    color: inherit;
}

</style>