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
    let url = base_url + `/item/${todoItem.id}`;

    let data = {
        userId,
        ...todoItem,
    }
    
    let datas = await rest.patch(url, data);
    console.log(datas);
    return datas;
}

const todoItemAdd = async (userId, todoItem) => {
    console.log(todoItem)
    console.log({ ...todoItem });
    let resData = await rest.post(base_url + '/item', todoItem );

    return resData;
}

const todoItemDelete = async (todoId) => {
    await rest.delete_( base_url + `/item/${todoId}`);
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