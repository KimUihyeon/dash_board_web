import { rest , date , data  } from '../../util'
import { getBaseUrl , getCurrentLoginID , httpAuhorizationHeaderConfig } from '../common/ServiceHelper'

const base_url = getBaseUrl() + '/v1/todo';

const getDatas = () =>{
    const userId = getCurrentLoginID();
    return rest.get(base_url + `/categories/${userId}`);
}

const addItem = (todoCategory) =>{
    return  rest.post(base_url + `/category` , { userId : getCurrentLoginID() , ...todoCategory});
}

const modifyItem = (todoCategory) =>{
    return rest.patch(base_url + `/category/${todoCategory.id}` , { userId : getCurrentLoginID(), ...todoCategory});
};

const deleteItem = (id) =>{
    return rest.patch(base_url + `/category/${id}` , { userId : getCurrentLoginID(), id});
}



export const todoCategoryService = {
    getDatas,
    addItem,
    modifyItem,
    deleteItem,
}