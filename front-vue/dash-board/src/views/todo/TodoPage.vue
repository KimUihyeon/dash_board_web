<template>
    <div class="todo-warp">
        <div class="todo-container">

            <el-row>
                <el-col :span="7">
                    <TodoFolderList :folders='getFolders1'/>
                    <hr/>
                    
                    <div> 
                        <!-- 스크롤 최대 영역 -->
                        <TodoFolderList :folders='getFolders2'/>
                    </div>

                    <hr/>
                    <TodoFolderAddInput></TodoFolderAddInput>
                </el-col>
                <el-col :span="1">
                    <el-divider direction='vertical'></el-divider>
                </el-col>
                <el-col :span="16">
                    <div class="todo-area">
                        <div class="todo-list-area">
                            <div v-if="!isLoading">
                                로딩중
                            </div>
                            <TodoList 
                                v-else
                                v-bind:items="getTodoList"/>
                        </div>
                        <TodoItemAddInput></TodoItemAddInput>
                    </div>
                </el-col>
            </el-row>


        </div>

    </div>
</template>

<script>
import TodoList from "../../components/todo/TodoList";
import TodoFolderAddInput from "../../components/todo/TodoFolderAddInput";
import TodoFolderList from "../../components/todo/TodoFolderList";
import TodoItemAddInput from "../../components/todo/TodoItemAddInput";
import { mapGetters } from 'vuex';

const name = 'TodoPage';
const components = {
        TodoList,
        TodoFolderList,
        TodoFolderAddInput }

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
.todo-warp{
    height: 100%;
    width: 100%;
    position: relative;
}
.todo-container{
    padding: 15px;
    height: calc(100% - 15px);
    max-width: 650px;
    width: 100%;
    position: absolute;
    right: 15px;
    background: #f0f;
    border-radius: 20px;
    text-align: left;
    overflow: hidden;
}
.devider{
    height: calc(100% - 6px);
    width: 1px;
    margin: 3px 0px;
    display: inline-block;
}
.folder-area{
    width: calc(40% - 30px);
    display: inline-block;
    margin: 10px;
}
.todo-area { 
    height: 100%;
}
.todo-list-area{
    height: calc(100% - 30px);
    overflow: scroll;
}
</style>