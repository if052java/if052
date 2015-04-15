/**
 * Created by Danylo Tiahun on 25.03.2015.
 */

$(document).ready(function () {

    function verifyLogin() {
        if(logins.indexOf($('#users').val()) < 0 && !$('#allUsers').is(':checked')) {
            $('#noLoginLabel').prop('hidden', false);
            return false;
        }
        return true;
    }

    $("#users").autocomplete({
        source : logins,
        minLength: 0
    }).focus(function () {
        $(this).autocomplete("search");
    });

    $('#allUsers').change(function () {
        $('#users').val("");
        $('#users').prop('disabled', this.checked);
        if(this.checked) {
            $('#noLoginLabel').prop('hidden', true);
        }
    });

    $('#allTypes').change(function () {
        $('[name="types"]').prop("checked", this.checked);
        $('#subBtn').prop('disabled', !$('#allTypes').is(':checked'));
    });

    $('.checkType').change(function () {
        if($('#allTypes').is(':checked')) {
            this.checked = !this.checked;
        }
        var checkedAtLeastOne = false;
        $('.checkType').each(function() {
            if ($(this).is(":checked")) {
                checkedAtLeastOne = true;
            }
            $('#subBtn').prop('disabled', !checkedAtLeastOne);
        });
    });

    $('#subBtn').click(function () {
        return verifyLogin();
    });

    $('#users').change(function () {
        $('#noLoginLabel').prop('hidden', verifyLogin());
    });


})