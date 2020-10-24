///////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////// {{Null Check}} //////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
const _isUndefined = (obj) => obj === undefined || typeof obj === 'undefined';
const _isEmptyStr = (str) => str === null || str === '' || _isUndefined(str) ;
const _isNullObject = (obj) => obj != null && typeof obj == "object" && !Object.keys(obj).length;

const isNull = (o) => {
    if( _isEmptyStr(o) || _isEmptyStr(o) || _isNullObject(o)){
        return true;
    }
    return false;
}
///////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////// {{compare}} //////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

const compare = (v1, v2) => v1 === v2;
const different = (v1, v2) => !compare(v1,v2);

const isNotNullAndDirty = (orgVal , changeVal) => {
    // 변경된객체가 널이면 안되고 이후 더티체크 함 
    return !isNull(changeVal) || different(orgVal, changeVal);
}

const isDirty = (orgVal , changeVal) =>{ 
    return different(orgVal, changeVal);
}

///////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////// {{Cookie}} //////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

const createCookie = (name, value , day ) =>{

    if(isNull(day)){
        day = 365 * 1;
    }

    var date = new Date();
    date.setTime(date.getTime() + day*24*60*60*1000);
    
    var willCookie = `${name}=${value}; expires=${date.toUTCString()}`;

    document.cookie = willCookie;
}

const getCookie = (name) =>{
    var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
    return value? value[2] : null;;
}

const removeCookie = (name) =>{
    document.cookie = name + '=; expires=Thu, 01 Jan 1999 00:00:10 GMT;';
}


///////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////// {{Validation}} //////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

const validationResult = {
    valide : Boolean,
    msg : String,
}

const validation = (text, type, range) =>{

    if(!isNull(text) && (text.length > range[0] && text.length < range[1] )){
        if(type === 'email') {
            /** 이메일 정규식 필요 */
            return false;
        }else if(type === null || type === 'text') {
            return true;
        }
        else{
            return true;
        }
    }
    else {
        return false;
    }
}


export const data = {
    isNull,
    validation,
    isNotNullAndDirty,
    isDirty,
    compare,
    different,
    
    cookie : {
        createCookie,
        getCookie,
        removeCookie
    }
}