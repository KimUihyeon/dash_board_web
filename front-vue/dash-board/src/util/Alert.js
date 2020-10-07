import { data } from "./Data";
const isNull = data.isNull;

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


function logger(context , functionName){
    let VUE_APP_MODE = process.env.VUE_APP_MODE;
    if(VUE_APP_MODE === 'DEV'){
        console.log(` ${functionName} => `, context);
    }
}


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

        serverError : '처리되지 않았습니다.'
    }
}

const alertClosure = (message , type) =>{
    const prop = { message, type };
    return (vueObject) =>{ 
        prop.vueObject = vueObject;
        elMessageBox(prop)
    };
}

const addSuccessAlert = (vueObject)=>{ alertClosure(prop.msg.addSuccess, prop.type.success)(vueObject) }
const editSuccessAlert = (vueObject) =>{ alertClosure(prop.msg.editSuccess, prop.type.success)(vueObject)}
const deleteSuccessAlert = (vueObject) =>{ alertClosure(prop.msg.deleteSuccess, prop.type.success)(vueObject)}
const cencleSuccessAlert = (vueObject) =>{ alertClosure(prop.msg.cencleSuccess, prop.type.success)(vueObject)}

const serverErrorAlert = (vueObject) =>{ alertClosure(prop.msg.serverError, prop.type.error)(vueObject)}



export const alert = {
    elConfirm,
    elMessageBox,
    logger,
    prop,

    addSuccessAlert,
    editSuccessAlert,
    deleteSuccessAlert,
    cencleSuccessAlert,
    serverErrorAlert,
}