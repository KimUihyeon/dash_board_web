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

const deleteCalendar = (cal) =>{
    return rest.delete_(`${base_url}/${cal.id}`);
}

const updateCalendar = (cal) =>{
    return rest.patch(`${base_url}/${cal.id}`, cal);
}



export const calendarService = {
    getUserCalendar,
    addCalendar,
    deleteCalendar,
    updateCalendar,
};