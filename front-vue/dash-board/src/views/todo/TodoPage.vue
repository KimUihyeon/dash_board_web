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
                    <AddButton
                        width="100%"
                        height="100%"
                        placeholder="디렉토리 추가"
                        marginTop="5px"
                        iconColor="#ACFFCF"
                        fontSize="12px"
                        iconSize="17px"
                        :inputEnterKeyPress_handle="addTodoItem"
                        />
                </div>
            </div>
            <div class="todo-area">
                <div class="todo-list-area">
                    <TodoList 
                        v-bind:items="getTodoList"/>
                </div>

                <hr/>
                <div class="todo-add-area">
                    <AddButton
                        placeholder="새 할일 추가하기."
                        width="100%"
                        height="calc(100% - 1px)"
                        backgroundColor="#ffffff61"
                        marginTop="5px"
                        iconColor="#ACFFCF"
                        fontSize="12px"
                        iconSize="17px"
                        :inputEnterKeyPress_handle="addTodoItem"
                    ></AddButton>
                    <!-- <TodoItemAddInput></TodoItemAddInput> -->
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
import AddButton  from '../../components/common/AddButton';
import { mapGetters } from 'vuex';
import { data , date , alert } from '../../util'

const name = 'TodoPage';
const components = {
        TodoList,
        TodoFolderList,
        TodoFolderAddInput,
        TodoItemAddInput,
        RadianBox,
        AddButton }

export default {
    name ,
    components,
    data() {
        return {
            isLoading : true,
        }
    },
    mounted(){
        this.todoListDownload();
    },
    beforeRouteUpdate(to, from, next){
        console.log(to)
        console.log(to.query.folder); 
        // this.todoListDownload();
    },
    methods:{
        todoListDownload(){
            this.isLoading = false;

            // let { folderId } = this.$router.query;

            // let param = {
            //     loginId : '',
            //     folder : folderId
            // };

            // console.log(folderId);

            /** Todo List 다운로드 */
            this.$store.dispatch('todoListDownload',{ loginId : 'dkrnl1318@naver.com' ,})
                .then(data=>{
                    console.log(data);
                    this.isLoading = true;
                })
                .catch(err =>{
                    this.isLoading = true;
            });
        },
        addTodoItem(e, param){

            let todoItem = {
                id : -1,
                title : param.keyWord,
                memo : '',
                date : date.now(),
                todoComplete : false,
            }

            this.$store.dispatch('todoItemUpdate', { todoItem })
                .then((data)=>{
                    alert.showMessage({ vueObject : this, type : 'success', message : '추가 되엇습니다.' });
                    })
                .catch(error => {
                    alert.showMessage({ vueObject : this, type : 'error', message : error });
            });
        },
    },
    computed : {
        ...mapGetters(['getTodoList']),
        getFolders1(){
            return [
                { id : 0 , title : '중요' , icon : 'el-icon-star-off' , pk : 1 , canModify : false , iconColor : 'yellow', fontColor : 'yellow'},
                { id : 1 , title : '오늘 할일' , icon : 'el-icon-s-opportunity' , pk : 2, canModify : false  , iconColor : 'white', fontColor : 'white'},
                { id : 2 , title : '완료된 할일' , icon : 'el-icon-s-release' , pk : 3 , canModify : false , iconColor : '#ffb8b8', fontColor : '#ffb8b8'},
            ];
        },
        getFolders2(){
            return [
                { id : 3 , title : '기본 디렉토리' , icon : 'el-icon-folder-delete' , pk : 4, canModify : false  , iconColor : 'white', fontColor : 'white'},
                { id : 4 , title : '디렉토리 1' , icon : 'el-icon-folder' , pk : 5 , canModify : true , iconColor : 'white', fontColor : 'white'}
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
    overflow: hidden;
    display: flex;
    flex-direction: column;
}

.todo-list-area{
    flex: 1;
    overflow: scroll;
}
.todo-add-area ,
.folder-add-area{
    height: 40px;
}
.todo-add-area{
    height: 50px;
}
hr{
    color: red;
    background-color: red;
    margin: 2px;
}
</style>