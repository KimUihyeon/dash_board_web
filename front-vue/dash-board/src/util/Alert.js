import { isNull } from "./Data";

/**
 * Element Ui dependency Alert!
 * 
 * @param {*} vueObject 
 * @param {*} type 
 * @param {*} message 
 */
export const showMessage = (vueObject, type, message) => {
    let _this = vueObject;
    _this.$message({
        message,
        type
    });
}


export const showConfirm = (
    vueObject , type, confirmMsg, title,
    okCallback , cancelCallback, 
    okValue, cancleValue) => {
        

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