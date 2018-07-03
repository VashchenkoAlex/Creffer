//$('#errorData').

$('#loginSubmit').click(function () {
    const url = "/login";
    let email = $('#email').value;
    let password = $('#password').value;
    let data = {email:email,password:password};
    alert(data);
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