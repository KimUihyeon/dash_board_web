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
    getSeltedTodoItem : function(state,){

    }
}

const actions = {
    todoListDownload : function(context , payload){
        /**
         * payload = { loginId , folder? }
         */

        let { loginId } = payload;

        return new Promise((resolve , reject) =>{
            todoService.getTodoList(loginId)
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
        /**
         * 신규객체 아이디 -1 처리 디비에서 한번
         * 서버에 업데이트 된 객체 받아서 자바스크립트에 추가하는 로직에 한번 .. ! 처리할것 !
         */
        // let { todoItem } = payload;


        // if(todoItem.id === -1){  // 신규
        //     return todoService.todoItemAdd('', todoItem ).then((data)=>{
        //         context.commit('addTotoItem', { todoItem : savedItem }); 
        //     }).catch(e=>{
        //         console.log('취소')
        //         console.log(e);
        //     }); 
        // }
        // else {  // 기존 업데이트
        //     let savedItem = await todoService.todoItemUpdate('', todoItem ); // 디비에 업데이트
        //     context.commit('updateTodoItem' , { todoItem : savedItem }); 
        // }
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
    },

    setTodoCategories : function (state , payload) {
        state.todo.todoCategories = payload.todoCategories;
    },
    addTodoCategory : function (state , payload){
        let { todoCategory } = payload;
        state.todo.todoCategories.push(todoCategory);
    },
    removeTodoCategory : function (state , payload) {
        state.todo.addTodoCategory = state.todo.addTodoCategory.filter(t=> t.id!==payload.id);
    }
}


export const todoStoreBudle = {
    name : 'todo',
    state,
    getters,
    actions,
    mutation
}