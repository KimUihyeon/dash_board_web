import { convertByUnixDateTime, now } from './DateUtil'
import { get } from './AxiosUtil'
import { isEmpty , isNull } from './Data';
import moment from 'moment';

// #dateTime
const convertByUnixDate = (dateTime) => {
    return convertByUnixDateTime( dateTime);
}

export const date = {
    now,
    convertByUnixDate
}

export const data = {
    isEmpty,
    isNull : isNull
}


// #rest ful
// #rest ful
export const rest = {
    get,
}