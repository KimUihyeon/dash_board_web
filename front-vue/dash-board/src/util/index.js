import { convertByUnixDateTime, now } from './DateUtil'
import { get } from './AxiosUtil'
import { isNull } from './Data';
import { showMessage } from './Alert';
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
    isNull : isNull
}

// #rest ful
export const rest = {
    get,
}

export const alert = {
    showMessage
}