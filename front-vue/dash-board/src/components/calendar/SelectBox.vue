<template>
    <span>
        <el-option
            v-for="item in items"
            :key="item.id"
            :label="item.title"
            :value="item.id">
                                        
            <span style="float: left">{{ item.title }}</span>
            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.title }}</span>  
        </el-option>
    </span>
</template>

<script>
const name = 'SelectBox';
const props = { 
    items : Array
}

export default {
    name,
    props,
}
</script>