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
                    <TodoFolderList :folders='getDefaultTodoCategories'/>
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
                    <h1>{{selectedCategory.title}}</h1>
                </div>
                <div class="todo-list-area">
                    <TodoList 
                        v-bind:items="getTodoList"/>
                </div>

                <hr/>
                <div class="todo-add-area">
                    <AddButton
                        v-if='selectedCategory.id > 0'
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
            selectedCategory : { 
                title : '' 
            } ,
        }
    },
    mounted(){

        this.$store.dispatch('fetch_todo_categories').then((data)=>{
            this.selectCategory(data[0]);
        });
    },
    beforeRouteUpdate(to, from, next){
        const { type , id } = to.query;
        const selectedCategory = [...this.getTodoCategories].filter(t=> t.id === id)[0];
        
        this.selectCategory(selectedCategory);
    },
    methods:{
        selectCategory(category){
            const { param , id } = category;
            this.selectedCategory = category;
            this.fetchTodoList(param , id);
        },
        fetchTodoList(filter , id){
            this.isLoading = false;
            this.$store.dispatch('fetch_todo_list', { 
                    loginId : 'dkrnl1318@naver.com',
                    filter, 
                    id
                })
                .then( data => {
                     console.log(data);
                    this.isLoading = true;
                })
                .catch(err =>{ this.isLoading = true; 
            });
        },

        addTodoItem(e, param){
            if(this.selectedCategory.id < 0){
                return ;
            }
            this.$store.dispatch('patch_todo', { 
                    todoItem : {
                        id : -1,
                        title : param.keyWord,
                    }, 
                    categoryId : this.selectedCategory.id 
                })
                .then(data=> {this.successAlert(data);})
                .catch(err=>{this.errorAlert(err); });
        },

        addTodoCategory(e, param) {
            this.$store.dispatch('patch_todo_category' , { 
                    todoCategory : {
                        id : -1,
                        title : param.keyWord
                    }})
                .then(data=> {this.successAlert(data);})
                .catch(err=>{this.errorAlert(err);});
        },

        successAlert(data){
            alert.elMessageBox({  vueObject : this, 
                type : 'success', 
                message : '추가 되엇습니다.' 
            });
        },
        errorAlert(err){
            alert.elMessageBox({ 
                vueObject : this,
                type : 'error', 
                message : err 
            });
        }
    },
    computed : {
        ...mapGetters(['getTodoList', 'getTodoCategories' , 'getDefaultTodoCategories']),
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