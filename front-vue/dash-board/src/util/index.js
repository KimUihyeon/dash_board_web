import { convertByUnixDateTime, now , format} from './DateUtil'
import { rest as Rest } from './Rest'
import { alert as Alert } from './Alert';
import { data as Data } from './Data';
import { error as Error } from './Error'
import { delay as Delay } from './Delay'
import { modelMapper as ModelMapper } from './ModelMapper';



/** dependency -> Moment */
export const date = {
    now,
    convertByUnixDate : (dateTime) => {
        return convertByUnixDateTime(dateTime);
    },
    format
}


/** dependency -> Axios */
export const rest = Rest;
export const alert = Alert;
export const error = Error;
export const data = Data;
export const delay = Delay;
export const modelMapper = ModelMapper;