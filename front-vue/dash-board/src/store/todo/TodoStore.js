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
    todoListDownload : function(context){
        let todoList = todoService.getTodoList();
        context.commit('setTodoList', { todoList });
    },
    todoItemUpdate : function (context , payload){
        /**
         * 신규객체 아이디 -1 처리 디비에서 한번
         * 서버에 업데이트 된 객체 받아서 자바스크립트에 추가하는 로직에 한번 .. ! 처리할것 !
         */

        let item = payload.todoItem;
        todoService.todoItemUpdate('', item ); // 디비에 업데이트

        
        if(item.id === -1){  // 신규
            context.commit('addTotoItem', {todoItem : item }); // 디비 업데이트 이후에 객체 받아서 처리할것 ..!
        }
        else {  // 기존 업데이트
            context.commit('updateTodoItem' , { todoItem : item }); 
        }
    }

}

const mutation = {
    setTodoList : function(state ,payload){
        state.todo.todoList = payload.todoList;
    },
    addTotoItem : function(state , payload){
        state.todo.todoList.push(payload.todoItem);
    },
    updateTodoItem : function(state , payload){

        let todoList = [ ... state.todo.todoList];
        state.todo.todoList = todoList.map(t=> {
            if( t.id === payload.todoItem.id ){
                return {
                    ...payload,
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