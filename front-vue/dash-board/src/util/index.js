import { convertByUnixDateTime, now } from './DateUtil'
import { get , post , delete_ , patch} from './AxiosUtil'
import { data as Data } from './Data';
import { showMessage , showConfirm , logger} from './Alert';
import { authencationError, nullExceptionError , httpUrlNotSurpport, findError } from './Error'
import moment from 'moment';

// #dateTime
const convertByUnixDate = (dateTime) => {
    return convertByUnixDateTime(dateTime);
}

export const date = {
    now,
    convertByUnixDate
}

export const data = Data;

// #rest ful
export const rest = {
    get,
    post,
    patch,
    delete_
}

export const alert = {
    showMessage,
    showConfirm,
    logger
}

export const error = {
    findError,

    nullExceptionError,
    authencationError,
    httpUrlNotSurpport,


};

