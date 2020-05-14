
const errorSample = {
    code : String, // Server Cdoe
    serverCode : Number, // url Code
    msg : String ,  // alert Message
    description : String, // 설명
}

export const nullExceptionError = {
    code : 'E500',
    serverCode : 500,
    msg : '잘못된 접근 입니다.',
    description : 'Server null exception Error'
}

export const httpUrlNotSurpport ={
    code : 'E404',
    serverCode : 404,
    msg : '요청하신 주소를 찾을수 없습니다.',
    description : '컨트롤러 주소 입력 오류.'
}


export const authencationError = {
    code : 'E403',
    serverCode : 403,
    msg : '로그인이 해제 되었습니다.',
    description : '토큰 인증실패 애러'
}


let errorContainner = [
    nullExceptionError,
    httpUrlNotSurpport,
    authencationError,
]


export const findError = (code) => {
    return errorContainner.filter(t=>t.code === code)[0];
}