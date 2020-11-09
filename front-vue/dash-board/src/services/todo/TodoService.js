import { rest , date , data } from '../../util'
import { getBaseUrl , getCurrentLoginID , httpAuhorizationHeaderConfig } from '../common/ServiceHelper'

const base_url = getBaseUrl() + '/v1/todo';

/**
 * 수정, 추가 로직
 * @param {*} userId 
 * @param {*} todoItem 
 */
const updateTodoItem = ( todoItem , categoryId) => {
    let url = base_url + `/item/${todoItem.id}`;
    return rest.authPatch(url, {
        ...todoItem,
        ...categoryId,
        userId : getCurrentLoginID()
    });
}

const addTodoItem = ( todoItem ,categoryId) => {
    return rest.authPost( base_url + '/item', {
        ...todoItem,
        categoryId,
        userId : getCurrentLoginID()
    });
}

const deleteTodoItme = (todoId) => {
    return rest.authDelete( base_url + `/item/${todoId}`);
}

const getTodoList = ( filter , categoryId) => {
    return rest.authGet( base_url + `/list/${categoryId}`, { 
        userId : getCurrentLoginID() ,
        filter , 
        categoryId 
    });
}


export const todoService = {
    updateTodoItem,
    deleteTodoItme,
    getTodoList,
    addTodoItem
};