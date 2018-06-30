//$('#errorData').
$('#loginSubmit1').click(function () {
    const url = "/signup";
    let email = $('#email1').value;
    let password = $('#password1').value;
    let data = {email:email,password:password};
    console.log(data);
    /*$.ajax({
        url:url,
        method:post,
        data:data,
        success:function () {
            console.log("Success")
        },
        error:function (msg) {
            console.log(msg);
        }
    })*/
});