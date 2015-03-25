/**
 * Created by Danylo Tiahun on 25.03.2015.
 */
$(function() {
    $( "#startDate" ).datepicker({dateFormat:"yy/mm/dd"});
});
$(function() {
    $( "#endDate" ).datepicker({dateFormat:"yy/mm/dd"}).datepicker("setDate", new Date());
});
$(function() {
    var startDate = $("#startDate").val();
    var endDate = $("#endDate").val();

});