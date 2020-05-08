import { rest , date } from '../../util'

const base_url = process.env.VUE_APP_API_BASE_URL + '/v1/todo';


let TempList =[
    { id : 0 , title : '중요' , icon : 'el-icon-star-off' , pk : 1 , canModify : false , iconColor : 'yellow', fontColor : 'yellow'}
]


const getDatas = async (userId) =>{
    return await rest.get(base_url + `/categories/${userId}` , {userId});
}

const addItem = async (userId, todoCategory) =>{
    return await rest.post(base_url + `/category` , { userId , ...todoCategory});
}

const modifyItem = async ( userId, todoCategory) =>{
    return await rest.patch(base_url + `/category/${todoCategory.id}` , { userId, ...todoCategory});
};

const deleteItem = async ( userId , id) =>{
    return await rest.patch(base_url + `/category/${id}` , { userId, id});
}



export const todoCategoryService = {
    getDatas,
    addItem,
    modifyItem,
    deleteItem,
}