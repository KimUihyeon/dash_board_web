<template>
    <div class="folder-add-container">
        <div class="folder-puls-button">
            <AddButton></AddButton>
        </div>
        <div class="folder-add-input">
            <input type="txt" v-model="keyWord" placeholder="디렉토리 추가">
        </div>
    </div>
</template>

<style scoped>
.folder-add-container{
    position: relative;
}
.folder-puls-button{
    position: absolute;
    top : 0;
    left: 0;
}
.folder-add-input{
    position: relative;
    top : 0;
    left: 0;
    width: 100%;
    height: 100%;
    text-align: left;
}
</style>

<script>
import AddButton  from '../common/AddButton';
import { data } from "../../util";

const name = 'TodoFolderAddInput';
const components =  { AddButton };

export default {
    name,
    components, 
    data(){
        return {
            keyWord : '',
            isEmpty : false,
        }
    },
    watch : {
        keyWord(v){
            this.isEmpty = data.isNull(v);
        }
    }
}
</script>