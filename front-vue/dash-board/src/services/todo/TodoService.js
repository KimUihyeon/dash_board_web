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

const todoItemUpdate = (userId , todoItem) => {
    let datas = [...tempList];

    tempList = datas.map(t=>{
        if(t.id === todoItem.id){
            return todoItem;
        }
        return t;
    })
}

const todoItemDelete = (todoId) => {

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
    getTodoItem
};