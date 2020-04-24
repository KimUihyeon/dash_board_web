export const isNull = (object) => {
    if(object === null || object === undefined || object === '' || ( object != null && typeof object == "object" && !Object.keys(object).length )) {    
        return true;
    }
    return false;
}