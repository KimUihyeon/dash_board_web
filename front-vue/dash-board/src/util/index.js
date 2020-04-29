import { convertByUnixDateTime, now } from './DateUtil'
import { get } from './AxiosUtil'
import { isNull, createCookie, getCookie, removeCookie } from './Data';
import { showMessage , showConfirm } from './Alert';
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
    isNull : isNull,
    createCookie,
    getCookie,
    removeCookie
}

// #rest ful
export const rest = {
    get,
}

export const alert = {
    showMessage,
    showConfirm,
}