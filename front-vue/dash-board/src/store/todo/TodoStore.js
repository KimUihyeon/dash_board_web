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
import Axios from "axios";


const state = {
    todo_list : [],
    selectedTodoItem : {},
    todo_categories : [],
}


const getters = {
    getTodoList : function(state){
        return state.todo.todo_list;
    },
    getTodoCategories : function(state){

        return state.todo.todo_categories;
    },
    getDefaultTodoCategories : function(state){
        return [
            { id : -3 , title : '중요' , icon : 'el-icon-star-off', canModify : false , iconColor : 'yellow', fontColor : 'yellow' , param : 'important'},
            { id : -2 , title : '오늘 할일' , icon : 'el-icon-s-opportunity', canModify : false  , iconColor : 'white', fontColor : 'white', param : 'today'},
            { id : -1 , title : '완료된 할일' , icon : 'el-icon-s-release' , canModify : false , iconColor : '#ffb8b8', fontColor : '#ffb8b8' , param : 'complate'}
        ]
    }
}


const actions = {
    /**
     * 
     * @param {*} context 
     * @param {*} payload  = { loginId , type? }
     */
    fetch_todo_list : function(context , payload){
        let { filter , id } = payload;

        return new Promise((resolve , reject) =>{
            todoService.getTodoList( filter , id)
                .then(data=>{
                    alert.logger('todo List download', 'fetch_todo_list');
                    context.commit('SET_TODO_LIST', { todoList : data });
                    resolve(data);
                })
                .catch(error=>{
                    reject(error);
            });
        })
    },

    /**
     * 
     * @param {*} context 
     * @param {*} payload  = { loginId, todoItem }
     */
    remove_todo : function (context , payload){
        let { todoItem } = payload;

        return new Promise((resolve, reject)=>{
            todoService.deleteTodoItme(todoItem.id)
                .then( data =>{
                    context.commit('REMOVE_TODO' , { todoItem : data });
                    resolve(data);
                })
                .catch(error => {
                    reject(error);
            });

        })
    },
    patch_todo : function (context , payload){
        let { todoItem , categoryId } = payload;

        return new Promise((resolve , reject)=>{
            
            if(todoItem.id === -1){
                // TODO : 해더에 JWT 던져서 만들어 컨트롤러에 주는식으로 변경 ..!

                todoService.addTodoItem( todoItem , categoryId).then(data=>{
                    context.commit('ADD_TODO', { todoItem : data }); 
                    resolve(data);
                })
                .catch(error=>{
                    reject(error);
                });
            }else {
                // TODO : 해더에 JWT 던져서 만들어 컨트롤러에 주는식으로 변경 ..!
                todoService.updateTodoItem(todoItem ).then(data => {
                    context.commit('PATCH_TODO' , { todoItem : data }); 
                    resolve(data);
                })
                .catch(error => {
                    reject(error)
                })
            }
        })
    },

    fetch_todo_categories: function (context , payload){
        // let { loginId } = payload;

        return new Promise((resolve, reject)=>{
            todoCategoryService.getDatas()
                .then(data =>{
                    const categories = data.map(t=>{
                        return{
                            ...t,
                            param : 'category'
                        }
                    })
                    context.commit('SET_TODO_CATEGORIES' , { todoCategories : categories }); 
                    resolve(categories);
                })
                .catch(err=>{
                    reject(err);
            });
        })
    },

    patch_todo_category : function (context , payload){
        let { todoCategory } = payload;

        return new Promise((resolve , reject)=>{

            if(todoCategory.id === -1){ // 신규

                todoCategoryService.addItem( todoCategory).then(data =>{
                    context.commit('ADD_TODO_CATEGORY', { todoCategory : data })
                    resolve(data);
                }).catch(err=>{
                    reject(err);
                })
            }

            else { // 수정
                todoCategoryService.modifyItem( todoCategory).then(data =>{
                    context.commit('PATCH_TODO_CATEGORY', { todoCategory : data })
                    resolve(data);
                }).catch(err=>{
                    reject(err);
                })
            }
        });
    },

    remove_todo_category : function (context, payload) {
        let { todoCategory } = payload;

        return new Promise((resolve , reject)=>{
            todoCategoryService.deleteItem( todoCategory.id ).then(data=>{
                context.commit('REMOVE_TODO_CATEGORY',{ id : data.id});
                resolve(data);
            }).catch(err=>{
                reject(err);
            })
        })
    },
}

const mutation = {
    /// todo Items
    /// todo Items
    /// todo Items
    SET_TODO_LIST : function(state ,payload){
        console.log(payload);
        state.todo.todo_list = payload.todoList;
    },
    REMOVE_TODO : function (state, payload){
        let { todoItem } = payload;
        state.todo.todo_list = state.todo.todo_list
                                .filter(t=> t.id !== todoItem.id);
    },
    ADD_TODO : function(state , payload){
        let { todoItem } = payload;
        state.todo.todo_list.push(todoItem);
    },
    PATCH_TODO : function(state , payload){
        let { todoItem } = payload;
        state.todo.todo_list = state.todo.todo_list.map(t=> {
            if( t.id === todoItem.id ){
                return {
                    ...todoItem,
                }
            }
            return t;
        });
    },
    
    // Category
    // Category
    // Category
    SET_TODO_CATEGORIES : function (state , payload) {
        state.todo.todo_categories = payload.todoCategories;
    },
    ADD_TODO_CATEGORY : function (state , payload){
        let { todoCategory } = payload;
        state.todo.todo_categories.push(todoCategory);
    },
    REMOVE_TODO_CATEGORY: function (state , payload) {
        let { id } = payload;
        state.todo.todo_categories = state.todo.todo_categories
                                        .filter(t=> t.id!== id);
    },
    PATCH_TODO_CATEGORY : function (state , payload) {
        let { todoCategory } = payload;
        state.todo.todo_categories = state.todo.todo_categories.map(t=>{
            if(t.id === todoCategory.id){
                return {
                    ...todoCategory
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