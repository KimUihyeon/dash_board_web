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

export const eventService = {
    addEvent
};