if (locale == "uk" ) {
    var langProp = "//cdn.datatables.net/plug-ins/f2c75b7247b/i18n/Ukranian.json"
    var localDateFormat = "dd-mm-yy"
    var paginAll = "Всі"
} else {
    var localDateFormat = "mm/dd/yy"
    var paginAll = "All"
}
$(document).ready(function() {
    $('#paginated').dataTable({
        "lengthMenu":[ [5, 10, 15, 25, -1], [5, 10, 15, 25, paginAll]],
        "info":     false,
        "columns": [
            null,
            null,
            null,
            null,
            { "orderable": false },
            { "orderable": false },
            { "orderable": false }
        ],
        "order": [[ 0, 'desc' ], [ 1, 'desc' ]],
        "language": {
            "url": langProp
        }
    });
});
$(function() {
    $.datepicker.setDefaults($.datepicker.regional[ locale ]);
    $("#datepicker").datepicker({ dateFormat: localDateFormat }).datepicker("setDate", new Date());
});

