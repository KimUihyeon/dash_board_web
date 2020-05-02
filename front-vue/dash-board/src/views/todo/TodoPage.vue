<template>

    <div class="full-height">
        <RadianBox
            title="123"
            width="50%"
            height="100%"
            maxWidth="500px"
            >


            <div class="folder-area">
                <div class="folder-list-area">
                    <TodoFolderList :folders='getFolders1'/>
                    <hr/>
                    <TodoFolderList :folders='getFolders2'/>
                </div>

                <div class="folder-add-area">
                    <hr/>
                    <TodoFolderAddInput></TodoFolderAddInput>
                </div>
            </div>
            <div class="todo-area">
                <div>
                    <div class="todo-list-area">
                        <TodoList 
                            v-bind:items="getTodoList"/>
                    </div>

                    <div>
                        <TodoItemAddInput></TodoItemAddInput>
                    </div>
                </div>
            </div>

        </RadianBox>
    </div>

</template>

<script>
import TodoList from "../../components/todo/TodoList";
import TodoFolderAddInput from "../../components/todo/TodoFolderAddInput";
import TodoFolderList from "../../components/todo/TodoFolderList";
import TodoItemAddInput from "../../components/todo/TodoItemAddInput";
import RadianBox from '../../components/common/RadianBox';
import { mapGetters } from 'vuex';

const name = 'TodoPage';
const components = {
        TodoList,
        TodoFolderList,
        TodoFolderAddInput,
        TodoItemAddInput,
        RadianBox }

export default {
    name ,
    components,
    data() {
        return {
            isLoading : true,
        }
    },
    mounted(){
            this.isLoading = false;

            /** Todo List 다운로드 */
            this.$store.dispatch('todoListDownload');
            this.isLoading = true;
    },
    computed : {
        ...mapGetters(['getTodoList']),
        getFolders1(){
            return [
                { id : 0 , title : '중요' , icon : '' , pk : 1 , canModify : false },
                { id : 1 , title : '오늘 할일' , icon : '' , pk : 2, canModify : false  },
                { id : 2 , title : '완료된 할일' , icon : '' , pk : 3 , canModify : false },
            ];
        },
        getFolders2(){
            return [
                { id : 3 , title : '기본 디렉토리' , icon : '' , pk : 4, canModify : false  },
                { id : 4 , title : '디렉토리 1' , icon : '' , pk : 5 , canModify : true }
            ];
        }
    },
}
</script>


<style scoped>
.folder-list-area{
    flex: 1;
    overflow: scroll;
}
.folder-area{
    width: 35%;
    height: 100%;
    padding-right: 2%;
    float: left;
    box-sizing: border-box;
    border-right: 1px solid #f5f5f512;

    display: flex;
    flex-direction: column;
}
.todo-area{
    overflow: auto;
    width: 65%;
    height: 100%;
    padding-left: 2%;
    float: left;
    overflow: overlay;
}
</style>