import { rest , date , data } from '../../util'

const base_url = process.env.VUE_APP_API_BASE_URL + '/v1/todo';


/**
 * 임시 Login
 */
const getCurrentLoginID = () =>{
    return data.cookie.getCookie(process.env.VUE_APP_COOKIE_NAME_LOGIN);
}

const getHttpHeader =  () =>{
    return { headers : { Authorization : 'Bearer ' + data.cookie.getCookie(process.env.VUE_APP_COOKIE_NAME_TOKEN)}}
};

/**
 * 수정, 추가 로직
 * @param {*} userId 
 * @param {*} todoItem 
 */
const todoItemUpdate = ( todoItem , categoryId) => {
    let url = base_url + `/item/${todoItem.id}`;
    return rest.patch(url, {
        ...todoItem,
        ...categoryId,
        userId : getCurrentLoginID()
    });
}

const todoItemAdd = ( todoItem ,categoryId) => {
    return rest.post( base_url + '/item', {
        ...todoItem,
        categoryId,
        userId : getCurrentLoginID()
    });
}

const todoItemDelete = (todoId) => {
    return rest.delete_( base_url + `/item/${todoId}`);
}

const getTodoList = ( filter , categoryId) => {
    let config = getHttpHeader();
    return rest.get( base_url + '/list', { 
        userId : getCurrentLoginID() ,
        filter , 
        categoryId 
    }, config);
}

const getTodoItem = (todoId) => {

}

export const todoService = {
    todoItemUpdate,
    todoItemDelete,
    getTodoList,
    getTodoItem,
    todoItemAdd
};