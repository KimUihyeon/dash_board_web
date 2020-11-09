import { rest , date , data } from '../../util'
import { getBaseUrl , getCurrentLoginID , httpAuhorizationHeaderConfig } from '../common/ServiceHelper'

const base_url = getBaseUrl() + '/v1/calendar/event';

const addEvent = (event, userId) =>{
    let _event = {
        ...event,
        userid : userId,
    }
    return rest.authPost(`${base_url}/`, _event);
}

const getUserEvent = (userId) => { 
    return rest.authGet(`${base_url}/${userId}`, { userId : userId});
}

const deleteEvent = (event) =>{
    return rest.authDelete(`${base_url}/${event.id}`);
}

const updateEvent = (event) =>{
    console.log(event)
    return rest.authPatch(`${base_url}/${event.id}`, event);
}

export const eventService = {
    addEvent,
    getUserEvent,
    deleteEvent,
    updateEvent
};