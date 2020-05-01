import { rest , date } from '../../util'

let tempList = [];
for(var i = 0 ; i< 10 ; i++ ){
    tempList.push({
        id : i,
        title : '제목 _'+i,
        memo : '메모 _____'+i,
        date : date.now,
        todoComplate : false,
    })
}

/**
 * 수정, 추가 로직
 * @param {*} userId 
 * @param {*} todoItem 
 */
const todoItemUpdate = (userId , todoItem) => {
    let datas = [...tempList];

    tempList = datas.map(t=>{
        if(t.id === todoItem.id){
            return todoItem
        }
        return t;
    })
    return todoItem;
}

const todoItemAdd = (userId, todoItem) => {
    let datas = [...tempList];
    
    todoItem.id = Math.max(...datas.map(t=> t.id)) + 1;
    datas.push(todoItem);
    tempList = datas;

    return todoItem;
}

const todoItemDelete = (todoId) => {
    let datas = [...tempList];

    datas.filter(t=>{
        if(t.id === todoId){
            return false
        }
        else {
            return true;
        }
    })

    tempList = datas;
    console.log('삭제된 데이터 ' ,tempList)
}

const getTodoList = (userId) => {
    return tempList;
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