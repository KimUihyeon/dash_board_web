import { convertByUnixDateTime, now } from './DateUtil'
import { get } from './AxiosUtil'
import moment from 'moment';

// #dateTime
const convertByUnixDate = (dateTime) => {
    return convertByUnixDateTime( dateTime);
}

export const date = {
    now,
    convertByUnixDate
}




// #rest ful
// #rest ful
export const rest = {
    get,
}