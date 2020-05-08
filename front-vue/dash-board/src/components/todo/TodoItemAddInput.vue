<template>
    <div>
        <input 
            type='input'
            v-model="todoItem.title"
            @keydown.enter='enterKeyPress_handle'
            />
    </div>
</template>


<style scoped>
</style>

<script>
import { alert , date } from "../../util";
import AddButton from '../../components/common/AddButton';
export default {
    name : 'TodoItemAddInput',
    components : { AddButton },
    data(){
        return {
            todoItem : {},
            title : '',
        }
    },
    mounted(){
        this.clearTodoItem();
    },
    methods:{
        enterKeyPress_handle (e){

            this.addTodoItem();
            this.clearTodoItem();
        },
        addTodoItem(){
            this.todoItem = {
                id : -1,
                title : '',
                memo : '',
                date : date.now(),
                todoComplete : false,
            }
            this.todoItem.date = date.now();

            this.$store.dispatch('todoItemUpdate', { todoItem : this.todoItem })
                .then(data =>{ 
                    alert.showMessage({ vueObject : this, type : 'success', message : '추가 되었습니다.' });
                })
                .catch(error =>{
                    alert.showMessage({ vueObject : this, type : 'error', message : error });
            });
        },
        clearTodoItem(){
            this.todoItem = {
                id : -1,
                title : '',
                memo : '',
                date : date.now(),
                todoComplete : false,
            }
        }
    }
}
</script>