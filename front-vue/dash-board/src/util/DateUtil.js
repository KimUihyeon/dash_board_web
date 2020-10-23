const moment = require('moment');

/**
 * 
 * @param {String} type 
 * @param {number} dateTime 
 */

const formatting = (moment ,format) =>{

    if(format === null || format === undefined ){
        format = 'YYYY-MM-DD HH:mm:ss';
    }
    
    return  moment.format(format)
}



export const now = (format) => {
    return formatting(moment(moment.now()) , format);
}


export const convertByUnixDateTime = (unix_DateTime,format) =>{
    var date = new Date(unix_DateTime * 1000);
    return formatting(moment(date) , format);
}


export const format = (dateStr, format) =>{
    return moment(dateStr).format(format);
}


export const addDays = (date, addDays, format) => {
    let _f = format;
    if(_f === undefined || f === null){
        _f = "YYYY-MM-DD"
    }

    return moment(date, _f).add(addDays, 'days').format(_f);
}



export const date = {
    now,
    convertByUnixDate : (dateTime) => {
        return convertByUnixDateTime(dateTime);
    },
    format,
    addDays,

}
