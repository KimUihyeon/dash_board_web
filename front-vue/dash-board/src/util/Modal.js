const showModal = () =>{

this.$prompt('Please input your e-mail', 'Tip', {
    confirmButtonText: 'OK',
    cancelButtonText: 'Cancel',
    inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
    inputErrorMessage: 'Invalid Email'
    }).then(({ value }) => {
    this.$message({
        type: 'success',
        message: 'Your email is:' + value
    });
    }).catch(() => {
    this.$message({
        type: 'info',
        message: 'Input canceled'
    });       
    });
}