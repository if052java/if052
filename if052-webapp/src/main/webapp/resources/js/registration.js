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
                required : strings['required.name'],
                rangelength : strings['rangelength.name']
            },
            "surname": {
                required  : strings['required.surname'],
                rangelength : strings['rangelength.surname']
            },
            "middleName": {
                required : strings['required.middleName'],
                rangelength : strings['rangelength.middleName']
            },
            "login": {
                required : strings['required.login'],
                rangelength : strings['rangelength.login']
            },
            "email":{
                required : strings['required.email'],
                email : strings['email.email']
            },
            "password": {
                required : strings['required.password'],
                rangelength : strings['rangelength.password']
            },
            "confirmPassword":{
                required : strings['required.confirmPassword'],
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