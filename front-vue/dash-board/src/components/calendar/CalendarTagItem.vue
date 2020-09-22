<template>
    <div>
        <el-checkbox :label="lable" >{{value}}</el-checkbox>
    </div>
</template>

<script>
let props = { 
    lable : String, 
    value : String
};
export default {
    props,
}
</script>

<style  scoped>
label { flex: 1; display: block;}
</style>>