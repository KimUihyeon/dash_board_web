import { convertByUnixDateTime, now } from './DateUtil'
import { rest as Rest } from './Rest'
import { alert as Alert } from './Alert';
import { data as Data } from './Data';
import { error as Error} from './Error'



/** dependency -> Moment */
export const date = {
    now,
    convertByUnixDate : (dateTime) => {
        return convertByUnixDateTime(dateTime);
    }    
}


/** dependency -> Axios */
export const rest = Rest;
export const alert = Alert;
export const error = Error;
export const data = Data;