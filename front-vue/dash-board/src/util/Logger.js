import { date } from "./DateUtil";

const _applicationMode = process.env.VUE_APP_MODE;
const _nowDateTime = ()=> date.now('YY-MM-DD HH:mm:ss');
const _loggerHeaderMaker = (logLevel, header)=> {
    return `${logLevel} | ${_nowDateTime()} | ${header} | `;
}

const logLevel = { 
    DEV : 'DEV' , // 개발용 로그 [배포시 안보임] 

    TRACE : 'TRACE' , //가장 상세한 정보를 나타낼 때 사용한다. [배포시 안보임] [미사용중] 
    DEBUG : 'DEBUG' , //일반 정보를 상세히 나타낼 때 사용한다. [배포시 안보임] [미사용중] 
    INFO : 'INFO' , //일반 정보를 나타낼 때 사용한다.
    WARN : 'WARN' , //에러는 아니지만 주의할 필요가 있을 때 사용한다.
    ERROR : 'ERROR' ,//일반 에러가 일어 났을 때 사용한다.
    FATAL : 'FATAL' , //가장 크리티컬한 에러가 일어 났을 때 사용한다. [미사용중]
}

const dev = (trace , context) =>{
    if(_applicationMode === 'DEV' || _devLoggervisible){
        let lh = _loggerHeaderMaker(logLevel.DEV, trace);
        console.log(lh, context);
    }
}

const debug = (trace, msg) =>{
    if(_applicationMode === 'DEV'){
        console.log(_loggerHeaderMaker(logLevel.DEBUG, trace), msg);
    }
}

const warn = (trace, msg) =>{
    console.warn(_loggerHeaderMaker(logLevel.WARN, trace), msg);
}

const error = (trace, msg) =>{
    console.error(_loggerHeaderMaker(logLevel.ERROR, trace), msg);
}


export const logger = {
    dev,
    debug,
    warn,
    error
}