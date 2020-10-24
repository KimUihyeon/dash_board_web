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

        serverError : '처리되지 않았습니다.'
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


// function logger(context , functionName){
//     let VUE_APP_MODE = process.env.VUE_APP_MODE;
//     if(VUE_APP_MODE === 'DEV'){
//         console.log(` ${functionName} => `, context);
//     }
// }



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
}