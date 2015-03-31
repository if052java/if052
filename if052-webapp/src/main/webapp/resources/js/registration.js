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
            "name": {
                required : "Це поле обов'язкове для запису",
                rangelength:"Від 2 до 32 символів"
            },
            "surname": {
                required :"Це поле обов'язкове для запису",
                rangelength:"Від 2 до 32 символів"
            },
            "middleName": {
                required : "Це поле обов'язкове для запису",
                rangelength: "Від 2 до 32 символів"
            },
            "login": {
                required : "Це поле обов'язкове для запису",
                rangelength: "Від 8 до 32 символів"
            },
            "email":{
                required : "Це поле обов'язкове для запису",
                email: "Невалідний емайл"
            },
            "password": {
                required : "Це поле обов'язкове для запису",
                rangelength: "Від 8 до 32 символів"
            },
            "confirmPassword":{
                required : "Це поле обов'язкове для запису",
                rangelength: "Від 8 до 32 символів",
                equalTo:"Пароль підтверджено невірно"
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