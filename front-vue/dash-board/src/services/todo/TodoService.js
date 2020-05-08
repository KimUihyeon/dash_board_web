import { rest , date } from '../../util'

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

const getTodoList = (userId) => {
    return rest.get( base_url + '/list' , { userId });
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