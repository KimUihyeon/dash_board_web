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