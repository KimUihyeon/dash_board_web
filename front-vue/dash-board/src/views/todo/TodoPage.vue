<template>

    <div class="full-height">
        <RadianBox
            :isLoading='isLoading'
            :margin="getWindowSize > 765 ? '0px' : '1%'"
            :padding="getWindowSize > 765 ? '2%' : '4%'"
            :right="getWindowSize > 765 ? '15px' : '0px'"
            :width="getWindowSize > 765 ? '50%' : '98%'"
            height="100%"
            :maxWidth="getWindowSize > 765 ? '500px' : 'none'"> 
            <div class="todo-area">
                <div>
                    <h1 class="todo-title">
                        <button @click='back' class="el-icon-d-arrow-left"></button>
                        <span :style="'color : ' + selectedCategory.iconColor">
                            <i v-bind:class='selectedCategory.icon'></i>
                        </span>
                        <span>{{selectedCategory.title}}</span>
                    </h1>
                </div>
                <div class="todo-list-area">
                    <TodoList 
                        v-bind:items="getTodoList"/>
                </div>

                <hr v-show="!inputDisabled"/>
                <div class="add-area" v-show="!inputDisabled">
                    <ItmeAdd
                        v-if='selectedCategory.id > 0'
                        placeholder="새 할일 추가하기."
                        width="100%"
                        height="100%"
                        backgroundColor="#ffffff61"
                        marginTop="5px"
                        iconColor="#ACFFCF"
                        fontColor="#fff"
                        fontSize="12px"
                        iconSize="17px"
                        :isReadOnly="inputDisabled"
                        :inputEnterKeyPress_handle="addTodoItem"
                    ></ItmeAdd>
                </div>
            </div>
        </RadianBox>
    </div>

</template>

<script>
import TodoList from "../../components/todo/TodoList";
import RadianBox from '../../components/common/RadianBox';
import { todoCategoryService } from '../../services'
import ItmeAdd  from '../../components/todo/ItmeAdd';
import { mapGetters } from 'vuex';
import { data , date , alert } from '../../util'

const name = 'TodoPage';
const components = {
        TodoList,
        RadianBox,
        ItmeAdd }

export default {
    name ,
    components,
    data() {
        return {
            isLoading : true,
            selectedCategory : { 
                title : '',
                param : '',
            } ,
        }
    },
    async mounted(){
        const { id } = this.$route.query;
        const selectedCategory = await this.findCategoryItem(id);
        this.selectCategory(selectedCategory);
    },
    methods:{
        back(){
            this.$router.push({path : '/todoCategory'});
        },
        findCategoryItem(id){
            return new Promise((resolve,reject)=>{
                let fintCategory = this.getDefaultTodoCategories.filter(t=>t.id === id)[0];
                if(data.isNull(fintCategory)){
                    todoCategoryService.get(id).then((res)=>{
                        let data = {
                            ...res,
                            param : 'category',
                        }
                        resolve(data);
                    });
                }else {
                    resolve(fintCategory);
                }
            })
        },
        selectCategory(category){
            const { param , id } = category;
            this.selectedCategory = category;
            this.fetchTodoList(param , id);
        },
        fetchTodoList(filter , id){
            this.isLoading = true;

            setTimeout(()=>{
                this.$store.dispatch('fetch_todo_list', { 
                        loginId : 'dkrnl1318@naver.com',
                        filter, 
                        id
                    })
                    .then( data => {
                        this.isLoading = false;
                    })
                    .catch(err =>{ this.isLoading = false; 
                });
            },300)
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
        ...mapGetters(['getTodoList', 'getTodoCategories' , 'getDefaultTodoCategories', 'getWindowSize']),
        inputDisabled : function(){
            if(data.isNull(this.selectedCategory) ){
                return true;
            }
            const param = this.selectedCategory.param;
            return (data.isNull(param) ||param.indexOf('category') === -1) ?
                true : false;
        }
    },
}
</script>


<style scoped>

.folder-list-area{
    flex: 1;
    overflow: auto;
}
.todo-area{
    overflow: auto;
    width: 100%;
    height: 100%;
    float: left;
    overflow: hidden;
    display: flex;
    flex-direction: column;
}

.todo-list-area{
    flex: 1;
    overflow: auto;
}
.add-area{
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

.todo-title button{
    margin-right: 15px ;
    font-size: 0.75em;
    color: #bab1b5;
}
.todo-title span{
    margin-right: 10px ;
}
</style>