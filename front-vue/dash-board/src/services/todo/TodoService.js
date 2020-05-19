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
    return rest.patch(url, {
        ...todoItem,
        ...categoryId,
        userId : getCurrentLoginID()
    });
}

const addTodoItem = ( todoItem ,categoryId) => {
    return rest.post( base_url + '/item', {
        ...todoItem,
        categoryId,
        userId : getCurrentLoginID()
    });
}

const deleteTodoItme = (todoId) => {
    return rest.delete_( base_url + `/item/${todoId}`);
}

const getTodoList = ( filter , categoryId) => {
    let config = httpAuhorizationHeaderConfig();
    return rest.get( base_url + '/list', { 
        userId : getCurrentLoginID() ,
        filter , 
        categoryId 
    }, config);
}

const getTodoItem = (todoId) => {

}

export const todoService = {
    updateTodoItem,
    deleteTodoItme,
    getTodoList,
    getTodoItem,
    addTodoItem
};