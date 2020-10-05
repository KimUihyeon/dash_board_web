import { rest , date , data } from '../../util'
import { getBaseUrl , getCurrentLoginID , httpAuhorizationHeaderConfig } from '../common/ServiceHelper'

const base_url = getBaseUrl() + '/v1/calendar';

const getUserCalendar = (userId) => { 
    return rest.get(`${base_url}/${userId}`, { userId : userId});
}

const addCalendar = (cal) =>{
    let calendar = {
        ...cal,
        accountId : 'admin@admin.com'
    }
    return rest.post(`${base_url}/`, calendar );
}



export const calendarService = {
    getUserCalendar,
    addCalendar,
};