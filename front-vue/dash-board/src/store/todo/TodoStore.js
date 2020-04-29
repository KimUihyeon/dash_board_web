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

        console.log(payload)
        let item = payload.todoItem;
        todoService.todoItemUpdate('', item );
        context.commit('updateTodoItem' , { todoItem : item });
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