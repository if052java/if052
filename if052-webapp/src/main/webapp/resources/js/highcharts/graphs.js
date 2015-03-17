/**
 * Created by valentyn on 3/2/15.
 */
$(function () {
    $('#container').highcharts({
        chart: {
            type: 'spline'
        },
        title: {
            text: 'Schedule of water consumption for all time'
        },
        subtitle: {
            text: 'From 2015 year'
        },
        xAxis: {
            type: 'datetime',
            dateTimeLabelFormats: { // don't display the dummy year
                month: '%e. %b',
                year: '%e. %b'
            },
            title: {
                text: 'Date'
            }
        },
        yAxis: {
            title: {
                text: 'Show value (m^3)'
            }
        },
        tooltip: {
            //headerFormat: '<b>{series.name}</b><br>',
            pointFormat: '{point.x:%e. %b}: {point.y:.2f} m^3'
        },

        plotOptions: {
            spline: {
                marker: {
                    enabled: true
                }
            }
        },

        series: [{
            name: 'Indicator',
            data: indicatorsData
        }
        ]
    });

});

$   (function () {
    var selectIndicators = document.getElementsById("indicator");
    for(var i = 0; i< indicators.length; i++) {
        var option = document.createElement("option");
        option.text = indicators[i];
        selectIndicators.appendChild(option)
    }
});
