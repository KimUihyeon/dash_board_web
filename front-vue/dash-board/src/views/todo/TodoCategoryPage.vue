<template>
    <div class="full-height">
        <RadianBox
            title="123"
            :margin="getWindowSize > 765 ? '0px' : '1%'"
            :padding="getWindowSize > 765 ? '2%' : '4%'"
            :right="getWindowSize > 765 ? '15px' : '0px'"
            :width="getWindowSize > 765 ? '50%' : '98%'"
            height="100%"
            :maxWidth="getWindowSize > 765 ? '500px' : 'none'"> 

            <div class="folder-area">
                <div class="folder-list-area">
                    <TodoCategoryList 
                        :folders='getDefaultTodoCategories'
                        :handleClick='selectedCategory'/>
                    <hr/>
                    <TodoCategoryList 
                        :folders='getTodoCategories'
                        :handleClick='selectedCategory'/>
                </div>

                <div class="add-area">
                    <hr/>
                    <ItmeAdd
                        width="100%"
                        height="100%"
                        placeholder="디렉토리 추가"
                        marginTop="5px"
                        iconColor="#ACFFCF"
                        backgroundColor="#ffffff61"
                        fontColor="#fff"
                        fontSize="12px"
                        iconSize="17px"
                        :inputEnterKeyPress_handle="addTodoCategory"
                        />
                </div>
            </div>
        </RadianBox>
    </div>

</template>

<script>

import TodoCategoryList from "../../components/todo/TodoCategoryList";
import RadianBox from '../../components/common/RadianBox';
import ItmeAdd  from '../../components/todo/ItmeAdd';
import { mapGetters } from 'vuex';
import { data , date , alert } from '../../util'

const name = 'TodoCategoryPage';
const components = {
        TodoCategoryList,
        RadianBox,
        ItmeAdd }

export default {
    name ,
    components,
    data() {
        return {
            isLoading : true,
        }
    },
    mounted(){
        this.isLoading = true
        this.$store.dispatch('fetch_todo_categories').then(()=>{
            this.isLoading = false;
        });
        this.$store.dispatch('clear_todo_list');
    },
    methods:{
        selectedCategory(category){
            console.log(category);
            let { param , id } = category;
            this.$router.push({ path: 'todo', query: { type : param , id : id }});
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
        ...mapGetters(['getTodoCategories' , 'getDefaultTodoCategories', 'getWindowSize']),
    },
}
</script>


<style scoped>

.folder-list-area{
    flex: 1;
    overflow: auto;
}
.folder-area{
    height: 100%;
    float: left;
    box-sizing: border-box;
    display: flex;
    flex: 1;
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
</style>