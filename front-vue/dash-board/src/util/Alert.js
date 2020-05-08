import { isNull } from "./Data";

/**
 * Element Ui dependency Alert!
 * 
 * @param {*} vueObject 
 * @param {*} type 
 * @param {*} message 
 */
export const showMessage = ({ vueObject, type, message }) => {
    vueObject.$message({
        message,
        type
    });
}


/**
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
export const showConfirm = (
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
        type: type
        }).then(() => {
            if(isNull(okCallback)){
                showMessage(vueObject , 'success' , '확인 되었습니다.');
            }
            else{
                okCallback();
            }

        }).catch((e) => {
            console.log(e);
            if(isNull(cancelCallback)){
                showMessage(vueObject , 'info' , '취소 되었습니다.');
            }
            else{
                cancelCallback();
            }  
        });
}


export function logger(context){
    let { VUE_APP_MODE } = process.env;
    if(VUE_APP_MODE === 'DEV'){
        console.log(context)
    }
}