import { rest , date , data } from '../../util'
import { getBaseUrl , getCurrentLoginID , httpAuhorizationHeaderConfig } from '../common/ServiceHelper'

const base_url = getBaseUrl() + '/v1/calendar/event';

const addEvent = (event, userId) =>{
    let _event = {
        ...event,
        userid : userId,
    }
    return rest.post(`${base_url}/`, _event);
}

const getUserEvent = (userId) => { 
    return rest.get(`${base_url}/${userId}`, { userId : userId});
}

const deleteEvent = (cal) =>{
    return rest.delete_(`${base_url}/${cal.id}`);
}

const updateEvent = (cal) =>{
    return rest.patch(`${base_url}/${cal.id}`, cal);
}

export const eventService = {
    addEvent,
    getUserEvent,
    deleteEvent,
    updateEvent
};