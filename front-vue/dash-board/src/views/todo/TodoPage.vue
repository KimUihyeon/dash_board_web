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
                    <TodoFolderList :folders='getTodoCategories'/>
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
                        :inputEnterKeyPress_handle="addTodoCategory"
                        />
                </div>
            </div>
            <div class="todo-area">
                <div>
                    <h1>{{title}}</h1>
                </div>
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
            title : '할일 리스트',
            selectedCategory : '',
        }
    },
    mounted(){
        this.todoListDownload(null);
        this.$store.dispatch('todoCategoryDownload', {loginId : 'dkrnl1318@naver.com'});

        this.title = this.getFolders1[0].title;
    },
    beforeRouteUpdate(to, from, next){
        const { type , id } = to.query;
        this.selectedCategory = id;

        console.log([...this.getTodoCategories , ...this.getFolders1]
                        .filter(t=> t.id === id));
    
        this.title = [...this.getTodoCategories , ...this.getFolders1]
                        .filter(t=> t.id === id)[0].title;


        console.log(id);
        this.todoListDownload(type , id);
    },
    methods:{
        todoListDownload(filter , id){
            this.isLoading = false;

            // let param = {
            //     loginId : '',
            //     filter : ''
            // };

            console.log(filter);

            /** Todo List 다운로드 */
            this.$store.dispatch('todoListDownload',{ 
                loginId : 'dkrnl1318@naver.com',
                filter,
                id })
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
            }

            this.$store.dispatch('todoItemUpdate', { todoItem , categoryId : this.selectedCategory })
                .then((data)=>{
                    alert.showMessage({ vueObject : this, type : 'success', message : '추가 되엇습니다.' });
                })
                .catch(error => {
                    alert.showMessage({ vueObject : this, type : 'error', message : error });
            });
        },
        addTodoCategory(e, param) {
            let todoCategory = {
                id : -1,
                title : param.keyWord
            }

            this.$store.dispatch('todoCategoryUpdate' , { 
                    loginId : 'dkrnl1318@naver.com',
                    todoCategory 
                }).then(data=>{
                    alert.showMessage({ vueObject : this, type : 'success', message : '추가 되엇습니다.' });
                }).catch(err=>{
                    alert.showMessage({ vueObject : this, type : 'error', message : err });
            })

        }
    },
    computed : {
        ...mapGetters(['getTodoList', 'getTodoCategories']),
        getFolders1(){
            return [
                { id : -3 , title : '중요' , icon : 'el-icon-star-off', canModify : false , iconColor : 'yellow', fontColor : 'yellow' , param : 'important'},
                { id : -2 , title : '오늘 할일' , icon : 'el-icon-s-opportunity', canModify : false  , iconColor : 'white', fontColor : 'white', param : 'today'},
                { id : -1 , title : '완료된 할일' , icon : 'el-icon-s-release' , canModify : false , iconColor : '#ffb8b8', fontColor : '#ffb8b8' , param : 'complate'},
            ];
        },
    },
}
</script>


<style scoped>
.folder-list-area{
    flex: 1;
    overflow: auto;
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
    overflow: auto;
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
h1{
    color: white;
}
</style>