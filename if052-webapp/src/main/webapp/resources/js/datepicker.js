/**
 * Created by Danylo Tiahun on 15.04.2015.
 */

$(document).ready(function () {

    $.datepicker.setDefaults($.datepicker.regional[ "uk" ]);

    $("#startDate").datepicker({
        dateFormat: "yy/mm/dd",
        numberOfMonths: 2,
        onSelect: function (selected) {
            $("#endDate").datepicker("option", "minDate", selected)
        }
    });

    $("#endDate").datepicker({
        dateFormat: "yy/mm/dd",
        numberOfMonths: 2,
        onSelect: function (selected) {
            $("#startDate").datepicker("option", "maxDate", selected)
        }
    });

})