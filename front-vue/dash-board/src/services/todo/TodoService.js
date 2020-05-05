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
const todoItemUpdate = async (userId , todoItem) => {
    let url = base_url + '/list';

    let data = {
        userId,
        todoItem,
    }
    
    let datas = await rest.get(url, data);
    return todoItem;
}

const todoItemAdd = (userId, todoItem) => {
    let datas = [...tempList];
    
    todoItem.id = Math.max(...datas.map(t=> t.id)) + 1;
    datas.push(todoItem);
    tempList = datas;

    return todoItem;
}

const todoItemDelete = async (todoId) => {
    await rest.delete_( base_url + `/${todoId}`);
}

const getTodoList = async (userId) => {
    return await rest.get( base_url + '/list' , { userId });
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