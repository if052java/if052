/**
 * Created by Maxwellt on 30.03.2015.
 */
$(function() {
    $.datepicker.setDefaults($.datepicker.regional[ "uk" ]);
    $( "#datepicker").datepicker({dateFormat:"yy/mm/dd"});

});