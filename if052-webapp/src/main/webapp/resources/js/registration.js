$(document).ready(function() {
    $('#registrationForm').validate({
        rules: {
            "name": {
                required : true,
                rangelength :[2, 32]
            },
            "surname": {
                required : true,
                rangelength : [2, 32]
            }, 
            "middleName": {
                required : true,
                rangelength : [2, 32]
            },
            "login": {
                required : true,
                rangelength : [8, 32]
            },
            "email":{
                required : true,
                email : true
            },
            "password": {
                required : true,
                rangelength : [8, 32]
            },
            "confirmPassword":{
                required : true,
                rangelength : [8, 32],
                equalTo : "#password"
            }
        },
        messages: {
            "name": {
                required : strings['required.field'],
                rangelength : strings['rangelength.name']
            },
            "surname": {
                required  : strings['required.field'],
                rangelength : strings['rangelength.surname']
            },
            "middleName": {
                required : strings['required.field'],
                rangelength : strings['rangelength.middleName']
            },
            "login": {
                required : strings['required.field'],
                rangelength : strings['rangelength.login']
            },
            "email":{
                required : strings['required.field'],
                email : strings['email.email']
            },
            "password": {
                required : strings['required.field'],
                rangelength : strings['rangelength.password']
            },
            "confirmPassword":{
                required : strings['required.field'],
                rangelength : strings['rangelength.confirmPassword'],
                equalTo : strings['equalTo.confirmPassword']
            }
        }
    });

    var delay = (function(){
        var timer = 0;
        return function(callback, ms){
            clearTimeout (timer);
            timer = setTimeout(callback, ms);
        };
    })();

        // Username check
        $('#login').keyup(function() {
            delay(
                function() {
                    if ($('#login').val().length >= 8) {
                        $.ajax({
                            url: restURL + 'users/login/' + $('#login').val(),
                            success: function (Xhr) {
                                $('#login-errors').html(
                                    'Такий логін вже існує'
                                );
                                $("#submit").attr('disabled', 'disabled');
                            },
                            error: function (Xhr) {
                                $('#login-errors').html("");
                                $("#submit").removeAttr('disabled');
                            }
                        });
                    }
                }, 
                500
            );
            if ($('#login').val().length < 8) {
                $('#login-errors').html("");
            }
        });
    });