/**
* Created by valentyn on 4/15/15.
*/
$(document).ready(function() {
    $('#addAddress').validate({
        rules: {
            "city": {
                required: true,
                maxlength:32
            },
            "street": {
                required: true,
                maxlength:32
            },
            "building": {
                required: true,
                maxlength:10
            },
            "apartment": {
                number: true,
                min: 0
            }
        },
        messages: {
            "city": {
                required: messages['required.field'],
                maxlength: messages['maxlength.city']
            },
            "street": {
                required: messages['required.field'],
                maxlength: messages['maxlength.street']
            },
            "building": {
                required: messages['required.field'],
                maxlength: messages['maxlength.building']
            },
            "apartment": {
                number: messages['number.field'],
                min: messages['number.min']
            }
        }
    });
});