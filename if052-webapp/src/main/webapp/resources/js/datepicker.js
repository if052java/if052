/**
 * Created by Max on 3/23/2015.
 */
$(function() {
    $.datepicker.setDefaults($.datepicker.regional[ "uk" ]);
    $( "#datepicker").datepicker({dateFormat:"yy/mm/dd"}).datepicker("setDate", new Date());

});