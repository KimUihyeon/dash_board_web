const set_timeout = (func, millisec) => {
    setTimeout(()=>{
        func()
    },millisec);
}

const immediately = (func) =>{
    return set_timeout(func, 1);
}

export const delay = {
    set_timeout,
    immediately
}