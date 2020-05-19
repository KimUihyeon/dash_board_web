<template>
    <div>
        <input 
            type='input'
            v-model="title"
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
            title : '',
        }
    },
    watch : {
        title(v){
            console.log(v);
        }
    },
    methods:{
        enterKeyPress_handle (e){
            this.addTodoItem();
        },
        addTodoItem(){
            let categoryId = this.$store.state.todo.selectedCategory;
            console.log(categoryId);

            this.$store.dispatch('patch_todo', { id : -1, title : this.title , categoryId})
                .then(data =>{ 
                    alert.elMessageBox({ vueObject : this, type : 'success', message : '추가 되었습니다.' });
                    this.title = '';
                })
                .catch(error =>{
                    alert.elMessageBox({ vueObject : this, type : 'error', message : error });
            });
        }
    }
}
</script>