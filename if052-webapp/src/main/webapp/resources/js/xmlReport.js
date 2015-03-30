/**
 * Created by Danylo Tiahun on 25.03.2015.
 */

$(document).ready(function () {


    $.datepicker.setDefaults($.datepicker.regional[ "uk" ]);
    $("#startDate").datepicker({dateFormat: "yy/mm/dd"});
    $("#endDate").datepicker({dateFormat: "yy/mm/dd"}).datepicker("setDate", new Date());


    $('#allUsers').change(function () {
        $('#users').prop('disabled', this.checked);
    });
    /*
    $('#allTypes').change(function () {
        $('.checkType').prop('disabled', this.checked);
        $('[name="types"]').prop("checked", this.checked);
    });
    */

})