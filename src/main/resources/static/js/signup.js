$(document).bind("ready",function () {
    $('select').simpleselect({
        fadespeed:0
    })
});
$('#signUpComplete').click(function () {
   const url = "http://localhost:8080/signup_send";
   let user = getUserDetails();
    alert(jQuery.fn.jquery);
   $.ajax({
       url:url,
       method:"post",
       data:user,
       success:function () {
           //Redirect to the main page
       }
   })
});
function getUserDetails() {
    let user = [];
    user[0] = {"company":document.getElementById('company').value};
    user[1] = {"address1":document.getElementById('address1').value};
    user[2] = {"address2":document.getElementById('address2').value};
    user[3] = {"city":document.getElementById('city').value};
    user[4] = {"country":document.getElementById('country').value};
    user[5] = {"region":document.getElementById('region').value};
    user[6] = {"zip":document.getElementById('zip').value};
    user[7] = {"phone":document.getElementById('phone').value};
    user[8] = {"email":document.getElementById('email').value};
    user[9] = {"first_name":document.getElementById('first_name').value};
    user[10] = {"last_name":document.getElementById('last_name').value};
    user[11] = {"password":document.getElementById('password').value};
    user[12] = {"skype":document.getElementById('skype').value};
    user[13] = {"traffic":document.getElementById('traffic').value};
    user[14] = {"type_traffic":document.getElementById('type_traffic').value};
    user[15] = {"company_url":document.getElementById('company_url').value};
    user[16] = {"info":document.getElementById('info').value};
    return user;
}