
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