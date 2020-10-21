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

const deleteEvent = (event) =>{
    return rest.delete_(`${base_url}/${event.id}`);
}

const updateEvent = (event) =>{
    return rest.patch(`${base_url}/${event.id}`, event);
}

export const eventService = {
    addEvent,
    getUserEvent,
    deleteEvent,
    updateEvent
};