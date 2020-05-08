/**
 *  Store 작성법
 * @since 20.04.21 김의현
 * 
 *  state = { property : '' }
 *  getters = { functionName(){} },
 *  actions = { functionName(){} },
 *  mutation = { functionName(){} },
 */

import { todoService, todoCategoryService } from "../../services";
import { alert } from '../../util'


const state = {
    todoList : [],
    selectedTodoItem : {},
    todoCategories : [],
}

const getters = {
    getTodoList : function(state){
        return state.todo.todoList;
    },
    getTodoCategories : function(state){

        return state.todo.todoCategories.map(t=>{
            return {
                ...t,
                param : 'category'
            }
        });
    }
}

const actions = {
    todoListDownload : function(context , payload){
        /**
         * payload = { loginId , type? }
         */

        let { loginId , filter , id } = payload;

        console.log(filter);

        return new Promise((resolve , reject) =>{
            todoService.getTodoList(loginId , filter , id)
                .then(data=>{
                    alert.logger('todo List -> 다운로드');
                    context.commit('setTodoList', { todoList : data });
                    resolve(data);
                })
                .catch(error=>{
                    reject(error);
            });
        })
    },
    todoItemDelete : function (context , payload){
        let { todoItem } = payload;

        return new Promise((resolve, reject)=>{
            todoService.todoItemDelete(todoItem.id)
                .then( data =>{
                    context.commit('deleteTodoItem' , { todoItem : data });
                    resolve(data);
                })
                .catch(error => {
                    reject(error);
            });

        })
    },

    todoItemUpdate : function (context , payload){

        let { todoItem } = payload;

        return new Promise((resolve , reject)=>{

            if(todoItem.id === -1){
                todoService.todoItemAdd('',todoItem).then(data=>{
                    context.commit('addTotoItem', { todoItem : data }); 
                    resolve(data);
                })
                .catch(error=>{
                    reject(error);
                });
            }else {
                todoService.todoItemUpdate('', todoItem ).then(data => {
                    context.commit('updateTodoItem' , { todoItem : data }); 
                    resolve(data);
                })
                .catch(error => {
                    reject(error)
                })
            }
        })
    },


    todoCategoryDownload : function (context , payload){
        let { loginId } = payload;

        return new Promise((resolve, reject)=>{
            todoCategoryService.getDatas(loginId)
                .then(data =>{
                    context.commit('setTodoCategories' , { todoCategories : data }); 
                    resolve(data);
                })
                .catch(err=>{
                    reject(err);
            });
        })
    },
    todoCategoryUpdate : function (context , payload){
        let { loginId , todoCategory } = payload;

        return new Promise((resolve , reject)=>{

            if(todoCategory.id === -1){ // 신규

                todoCategoryService.addItem(loginId, todoCategory).then(data =>{
                    context.commit('addTodoCategory', { todoCategory : data })
                    resolve(data);
                }).catch(err=>{
                    reject(err);
                })
            }

            else { // 수정
                todoCategoryService.modifyItem(loginId, todoCategory).then(data =>{
                    context.commit('updateTodoCategory', { todoCategory : data })
                    resolve(data);
                }).catch(err=>{
                    reject(err);
                })
            }
        });
    },

    todoCategoryRemove : function (context, payload) {
        let { loginId , todoCategory } = payload;

        return new Promise((resolve , reject)=>{
            todoCategoryService.deleteItem( loginId , todoCategory.id ).then(data=>{
                context.commit('removeTodoCategory',{ id : data.id});
                resolve(data);
            }).catch(err=>{
                reject(err);
            })
        })
    },
}

const mutation = {
    /// todo Items
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
    },

    // Category
    setTodoCategories : function (state , payload) {
        state.todo.todoCategories = payload.todoCategories;
    },
    addTodoCategory : function (state , payload){
        let { todoCategory } = payload;
        state.todo.todoCategories.push(todoCategory);
    },
    removeTodoCategory : function (state , payload) {
        state.todo.addTodoCategory = state.todo.addTodoCategory.filter(t=> t.id!==payload.id);
    },
    updateTodoCategory : function (state , payload) {
        let todoCategories = [...state.todo.todoCategories];
        state.todo.todoCategories = todoCategories.map(t=>{
            if(t.id === payload.todoCategory.id){
                return {
                    ...payload.todoCategory 
                }
            }
            return t;
        })
    }
}


export const todoStoreBudle = {
    name : 'todo',
    state,
    getters,
    actions,
    mutation
}