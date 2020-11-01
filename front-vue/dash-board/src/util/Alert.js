import { data } from "./Data";
const isNull = data.isNull;

const prop = {
    type : {
        success : 'success',
        error : 'error',
    },

    msg : {
        editSuccess : '수정 완료',
        deleteSuccess : '삭제 완료',
        addSuccess : '추가',

        cencleSuccess : '취소',

        serverError : '처리되지 않았습니다.',
        notDuplicateEmail : '사용 가능한 이메일 입니다.',
        isDuplicateEmail : '이미 존재하는 이메일 입니다.',
        emailDuplicateCheck : '이메일 중복 확인을 해주세요.',
        userInfoCheck : '비밀번호 혹은 아이디를 확인해주세요.',
    }
}

/**
 * Element Ui Alert popup !
 * 
 * @param {*} vueObject 
 * @param {*} type 
 * @param {*} message 
 */
const elMessageBox = ({ vueObject, type, message }) => {
    vueObject.$message({
        message,
        type
    });
}


/**
 * Element Ui Comfirm popup
 * 
 * @param {vueObject} vueObject ,
 * @param {String , null} type  ,
 * @param {String , null} confirmMsg ,
 * @param {String , null} title ,
 * @param {String , null} okValue ,
 * @param {String , null} cancleValue ,
 * @param {function} okCallback ,
 * @param {function} cancelCallback  
 */
const elConfirm = (
    {
        vueObject ,
        type  ,
        confirmMsg ,
        title ,
        okValue ,
        cancleValue ,
        okCallback ,
        cancelCallback  }) => 
    {
        
        type = isNull(type) ? 'Warning' : type;
        okValue = isNull(okValue) ? '확인' : okValue;
        cancleValue = isNull(cancleValue) ? '취소' : cancleValue;


        return  vueObject.$confirm(confirmMsg , title , {
            confirmButtonText: okValue,
            cancelButtonText: cancleValue,
            type : type
            }).then(() => {
                if(isNull(okCallback)){
                    elMessageBox({ vueObject, type :'success' , message : '확인 되었습니다.' });
                }
                else{
                    okCallback();
                }

            }).catch((e) => {
                console.log(e);
                if(isNull(cancelCallback)){
                    elMessageBox({ vueObject, type :'success' , info : '취소 되었습니다.' });
                }
                else{
                    cancelCallback();
                }  
            });
}


const alertClosure = (type)=>{    
    const prop = { type };

    return ( message )=>{
        prop.message = message;
        return (vueObject)=>{
            prop.vueObject = vueObject;
            elMessageBox(prop)
        }
    }
}


const successAlertClosure = (m) => {return alertClosure(prop.type.success)(m)}
const errorAlertClosure = (m) => {return alertClosure(prop.type.error)(m)}

const addSuccessAlert = (v) => { successAlertClosure(prop.msg.addSuccess)(v) }
const editSuccessAlert = (v) =>{ successAlertClosure(prop.msg.editSuccess)(v)}
const deleteSuccessAlert = (v) =>{ successAlertClosure(prop.msg.deleteSuccess)(v)}
const cencleSuccessAlert = (v) =>{ successAlertClosure(prop.msg.cencleSuccess)(v)}
const notDuplicateEmailSuccessAlert = (v) =>{ successAlertClosure(prop.msg.notDuplicateEmail)(v) } // 사용 가능한 이메일 입니다.

const duplicateAlert = (v) => { errorAlertClosure(prop.msg.emailDuplicateCheck)(v) }


const userInfoCheckAlert = (v) => { errorAlertClosure(prop.msg.userInfoCheck)(v)}
const isDuplicateEmailSuccessAlert = (v) =>{ errorAlertClosure(prop.msg.isDuplicateEmail)(v) } // 이미 존재하는 이메일 입니다.
const serverErrorAlert = (v) =>{ errorAlertClosure(prop.msg.serverError)(v)}




export const alert = {
    elConfirm,
    elMessageBox,
    prop,

    addSuccessAlert,
    editSuccessAlert,
    deleteSuccessAlert,
    cencleSuccessAlert,
    serverErrorAlert,
    notDuplicateEmailSuccessAlert,
    isDuplicateEmailSuccessAlert,
    duplicateAlert,
    userInfoCheckAlert
}