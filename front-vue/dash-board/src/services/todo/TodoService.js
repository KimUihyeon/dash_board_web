import { rest , date , data } from '../../util'

const base_url = process.env.VUE_APP_API_BASE_URL + '/v1/todo';


// let tempList = [];
// for(var i = 0 ; i< 10 ; i++ ){
//     tempList.push({
//         id : i,
//         title : '제목 _'+i,
//         memo : '메모 _____'+i,
//         date : date.now,
//         todoComplete : false,
//     })
// }

/**
 * 수정, 추가 로직
 * @param {*} userId 
 * @param {*} todoItem 
 */
const todoItemUpdate = (userId , todoItem) => {
    let url = base_url + `/item/${todoItem.id}`;

    let data = {
        userId,
        ...todoItem,
    }
     
    return rest.patch(url, data);
}

const todoItemAdd = (userId, todoItem) => {
    return rest.post(base_url + '/item', todoItem);
}

const todoItemDelete = (todoId) => {
    return rest.delete_( base_url + `/item/${todoId}`);
}

const getTodoList = (userId , filter , categoryId) => {
    let param = { userId , filter , categoryId };
    console.log('token ->' , data.getCookie(process.env.VUE_APP_COOKIE_NAME_TOKEN));

    let config = { headers : { Authorization : 'Bearer ' + data.getCookie(process.env.VUE_APP_COOKIE_NAME_TOKEN)}}
    return rest.get( base_url + '/list' , param, config);
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