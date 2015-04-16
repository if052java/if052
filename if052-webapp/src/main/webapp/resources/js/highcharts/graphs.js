/**
 * Created by valentyn on 3/2/15.
 */
// A $( document ).ready() block.
$( document ).ready(function() {
    $(function () {
        $('#container').highcharts({
            chart: {
                type: 'spline'
            },
            title: {
                text: messages['graphs.consumption'] + ' ' + year + '. ' + messages['graphs.meter'] + ' ' + meterName
                                          + ', ' +  messages['graphs.type'] + ' ' + meterType + '.'
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                type: 'datetime',
                dateTimeLabelFormats: {
                    month: '%e. %b',
                    year: '%e. %b'
                },
                title: {
                    text: 'Date'
                }
            },
            yAxis: {
                title: {
                    text: messages['graphs.value']
                }
            },
            tooltip: {
                pointFormat: '{point.x:%e. %b}: {point.y:.2f}'
            },

            plotOptions: {
                spline: {
                    marker: {
                        enabled: true
                    }
                }
            },

            series: [{
                name: messages['graphs.value'],
                data: indicatorsData
            }
            ]
        });

    });

    $("#address").change(function() {
        $('#waterMeter').empty();
        addressId = $("#address option:selected").val();
        var selected = $('#address').find(":selected").text();
        if (selected == "Виберіть адресу"){
            $("#submit").attr('disabled', 'disabled');
            $("#waterMeter").attr('disabled', 'disabled');
        } else {
            $("#submit").removeAttr('disabled');
            $("#waterMeter").removeAttr('disabled');
        }
         $.ajax({
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            url: window.location.origin + '/watermeterlist?addressId=' + addressId,
            dataType: 'json',
            success:(function(data){
                if(data[0] == undefined){
                    $("#submit").attr('disabled', 'disabled');
                    $("#waterMeter").attr('disabled', 'disabled');
                }
                $.each(data, function(index, value) {
                    $('#waterMeter')
                        .append($("<option></option>")
                            .attr("value",value.waterMeterId)
                            .text(value.name));
                });
            })
        });
    });

        $("#submit").attr('disabled', 'disabled');
        $("#waterMeter").attr('disabled', 'disabled');

});

