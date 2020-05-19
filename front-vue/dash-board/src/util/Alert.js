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
                    elMessageBox(vueObject , 'success' , '확인 되었습니다.');
                }
                else{
                    okCallback();
                }

            }).catch((e) => {
                console.log(e);
                if(isNull(cancelCallback)){
                    elMessageBox(vueObject , 'info' , '취소 되었습니다.');
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

export const alert = {
    elConfirm,
    elMessageBox,
    logger,
}