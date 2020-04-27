export const isNull = (object) => {
    if(object === null || object === undefined || object === '' || ( object != null && typeof object == "object" && !Object.keys(object).length )) {    
        return true;
    }
    return false;
}


export const createCookie = (name, value , day ) =>{

    if(isNull(day)){
        day = 365 * 1;
    }

    var date = new Date();
    date.setTime(date.getTime() + day*24*60*60*1000);

    var willCookie = `${name}=${value}; expires=${date.toUTCString()}`;

    document.cookie = willCookie;
}

export const getCookie = (name) =>{
    var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
    console.log(document.cookie)
    return value? value[2] : null;;
}


export const removeCookie = (name) =>{
    document.cookie = name + '=; expires=Thu, 01 Jan 1999 00:00:10 GMT;';
}