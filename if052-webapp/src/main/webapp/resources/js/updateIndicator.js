if (locale == "uk" ) {
    var localDateFormat = "dd-mm-yy"
} else {
    var localDateFormat = "mm/dd/yy"
}
$(function() {
    $.datepicker.setDefaults($.datepicker.regional[ locale ]);
    $("#datepicker").datepicker({dateFormat:localDateFormat});

});