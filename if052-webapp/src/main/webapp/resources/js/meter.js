/**
 * Created by valentyn on 4/16/15.
 */
/**
 * Created by valentyn on 4/15/15.
 */
$(document).ready(function() {
    $('#addMeter').validate({
        rules: {
            "name": {
                required: true,
                maxlength:32
            },
            "description": {
                required: true,
                maxlength:64
            }
        },
        messages: {
            "name": {
                required: messages['required.field'],
                maxlength: messages['maxlength.name']
            },
            "description": {
                required: messages['required.field'],
                maxlength: messages['maxlength.description']
            },
            "building": {
                required: messages['required.field'],
                maxlength: messages['maxlength.building']
            }
        }
    });
});