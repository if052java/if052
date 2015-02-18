$(document).ready(function() {
    $('#registrationForm').validate({
        rules: {
            "firstName": {
                required : true,
                minlength: 2,
                maxlength:32
            },
            "surname": {
                required : true,
                minlength: 2,
                maxlength:32
            },
            "middleName": {
                required : true,
                minlength: 2,
                maxlength:32
            },
            "login": {
                required : true,
                minlength: 8,
                maxlength:32
            },
            "password": {
                required : true,
                minlength: 8,
                maxlength:32
            },
            "confirmPassword":{
                required : true,
                minlength: 8,
                maxlength:32,
                equalTo:"#password"
            }
        },
        messages: {
        }
    });
});