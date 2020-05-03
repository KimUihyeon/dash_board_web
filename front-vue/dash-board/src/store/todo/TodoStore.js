/**
 *  Store 작성법
 * @since 20.04.21 김의현
 * 
 *  state = { property : '' }
 *  getters = { functionName(){} },
 *  actions = { functionName(){} },
 *  mutation = { functionName(){} },
 */

import { todoService } from "../../services";


const state = {
    todoList : [],
    selectedTodoItem : {}

}

const getters = {
    getTodoList : function(state){
        return state.todo.todoList;
    },
    getSeltedTodoItem : function(state,){

    }
}

const actions = {
    todoListDownload : function(context , payload){
        /**
         * payload = { loginId , folder? }
         */
        let todoList = todoService.getTodoList();
        console.log('다운로드');
        context.commit('setTodoList', { todoList });
    },
    todoItemDelete : function (context , payload){
        let { todoItem } = payload;
        todoService.todoItemDelete(todoItem.id);
        context.commit('deleteTodoItem' , { todoItem });
    },
    todoItemUpdate : function (context , payload){
        /**
         * 신규객체 아이디 -1 처리 디비에서 한번
         * 서버에 업데이트 된 객체 받아서 자바스크립트에 추가하는 로직에 한번 .. ! 처리할것 !
         */

        let { todoItem } = payload;


        if(todoItem.id === -1){  // 신규
            //TODO : 서버로직 만들고 삭제하기 !
            let savedItem = todoService.todoItemAdd('', todoItem ); // 디비에 업데이트
            context.commit('addTotoItem', { todoItem : savedItem }); // 디비 업데이트 이후에 객체 받아서 처리할것 ..!
        }
        else {  // 기존 업데이트
            let savedItem = todoService.todoItemUpdate('', todoItem ); // 디비에 업데이트
            context.commit('updateTodoItem' , { todoItem : savedItem }); 
        }
    }

}

const mutation = {
    setTodoList : function(state ,payload){
        state.todo.todoList = payload.todoList;
    },
    deleteTodoItem : function (state, payload){

        let todoList = [ ... state.todo.todoList];

        state.todo.todoList = todoList.filter(t=>{
            if(t.id === payload.todoItem.id){
                return false;
            }
            else {
                return true;
            }
        });
    },
    addTotoItem : function(state , payload){
        let { todoItem } = payload;
        state.todo.todoList.push(todoItem);
    },
    updateTodoItem : function(state , payload){

        let todoList = [ ... state.todo.todoList];
        state.todo.todoList = todoList.map(t=> {
            if( t.id === payload.todoItem.id ){
                return {
                    ...payload.todoItem,
                }
            }
            return t;
        });
    }
}


export const todoStoreBudle = {
    name : 'todo',
    state,
    getters,
    actions,
    mutation
}