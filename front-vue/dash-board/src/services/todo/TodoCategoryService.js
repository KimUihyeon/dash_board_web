import { rest , date } from '../../util'

const base_url = process.env.VUE_APP_API_BASE_URL + '/v1/todo';

const getDatas = (userId) =>{
    return rest.get(base_url + `/categories/${userId}` , {userId});
}

const addItem = (userId, todoCategory) =>{
    return  rest.post(base_url + `/category` , { userId , ...todoCategory});
}

const modifyItem = ( userId, todoCategory) =>{
    return rest.patch(base_url + `/category/${todoCategory.id}` , { userId, ...todoCategory});
};

const deleteItem = ( userId , id) =>{
    return rest.patch(base_url + `/category/${id}` , { userId, id});
}



export const todoCategoryService = {
    getDatas,
    addItem,
    modifyItem,
    deleteItem,
}