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
    display: flex;
}
.folder-puls-button{
    display: inline;
}
.folder-add-input{
    flex: 1;
}
.folder-add-input input {
    width: 100%;
    height: 100%;
    display: block;
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
    props : {
        placeholder : String ,
        butonColor : String,
        fontColor : String,
        height : String,
        width : String,
        backgroundColor : String ,
    },
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