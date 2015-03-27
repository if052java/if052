$(document).ready(function() {
    $('#registrationForm').validate({
        rules: {
            "name": {
                required : true,
                rangelength:[2, 32]
            },
            "surname": {
                required : true,
                rangelength:[2, 32]
            }, 
            "middleName": {
                required : true,
                rangelength:[2, 32]
            },
            "login": {
                required : true,
                rangelength:[8, 32]
            },
            "email":{
                required : true,
                email: true
            },
            "password": {
                required : true,
                rangelength:[8, 32]
            },
            "confirmPassword":{
                required : true,
                rangelength:[8, 32],
                equalTo:"#password"
            }
        },
        messages: {
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
                                    'This login has already exist'
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
        });
    });