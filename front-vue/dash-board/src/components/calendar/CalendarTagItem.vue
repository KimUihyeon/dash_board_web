<template>
    <div class="margin-5">
        <div class="tag-item-container">
            <span v-show="isEditMode">
                <input/>
            </span>
            <span v-show="!isEditMode">
                <el-checkbox :label="lable" >{{value}}</el-checkbox>
            </span>
            <div class="tag-item-button-box">
                <span v-show="isEditMode">
                    
                    <el-tooltip class="item" :content="'저장'">
                        <span class="el-icon-check color-green" @click="()=> { updateSubmitHandle({id : id, name : value}); }"></span>
                    </el-tooltip>
                    
                    <el-tooltip class="item" effect="dark" :content="'닫기'">
                        <span class="el-icon-close color-red" @click="readMode"></span>
                    </el-tooltip>
                </span>

                <span v-show="!isEditMode">
                    
                    <el-tooltip class="item" effect="dark" :content="'수정'" >
                        <span class="el-icon-edit color-green" @click="editMode"></span>
                    </el-tooltip>
                    
                    <el-tooltip class="item" effect="dark" :content="'삭제'">
                        <span class="el-icon-delete color-red" @click="()=> { deleteClickHandle({ id : id }); }"></span>
                    </el-tooltip>
                </span>
            </div>
        </div>
    </div>
</template>

<script>
let name = 'CalendarTagItem';
let props = { 
    id : Number,
    lable : String, 
    value : String,
    deleteClickHandle : { 
        type :  Function,
        default : (a)=>{}
    },
    updateSubmitHandle : { 
        type :  Function,
        default : (a)=>{}
    },
};
export default {
    name,
    props,
    data()  {
        return {
            isEditMode : false,
            test : '123'
        }
    },
    methods : {
        editMode() { this.isEditMode = true; },
        readMode() { this.isEditMode = false; },
    }
}
</script>

<style  scoped>
.tag-item-container{
    position: relative;
}
.item{
    padding-right: 3px;
    padding-left: 3px;
    margin-right: 3px;
    margin-left: 3px;
}
.tag-item-button-box{
    position: absolute;
    top: 0;
    right: 0;
    height: 100%;
}
</style>