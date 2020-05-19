import { rest , date , data  } from '../../util'

const base_url = process.env.VUE_APP_API_BASE_URL + '/v1/todo';

const getCurrentLoginID = () =>{
    return data.cookie.getCookie(process.env.VUE_APP_COOKIE_NAME_LOGIN);
}

const getHttpHeader =  () =>{
    return { headers : { Authorization : 'Bearer ' + data.cookie.getCookie(process.env.VUE_APP_COOKIE_NAME_TOKEN)}}
};


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