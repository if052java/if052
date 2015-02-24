    $(document).ready(function() {
        $('#registrationForm').validate({
            rules: {
                "firstName": {
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

        // Username check
        $('#login').focusout(function() {
            var restUrl = "${restUrl}";
            alert(restUrl);

            if($('#login').val().length >= 8) {
                $.ajax({
                    url: 'http://localhost:8080/user/login/' + $('#login').val(),
                    success: function (Xhr) {
                        alert("This login already exist");
                    },
                    error: function (Xhr) {
                        alert('OK');
                    }
                });
            }
        });
    });