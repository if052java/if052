/**
 * Created by Максим on 3/25/2015.
 */
function initialize()
{
    geocoder = new google.maps.Geocoder();
    var mapOptions =
    {
        zoom: 12,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

    var arrayData = $('#gMapData').val().split("~")

    for (var i=0; i<arrayData.length; i++){
        codeAddress(arrayData[i])
    }

}

function codeAddress(address)
{
    geocoder.geocode( {address:address}, function(results, status)
    {
        if (status == google.maps.GeocoderStatus.OK)
        {
            map.setCenter(results[0].geometry.location);//center the map over the result
            //place a marker at the location
            var marker = new google.maps.Marker(
                {
                    map: map,
                    position: results[0].geometry.location
                });
        } else {
            alert('Geocode was not successful for the following reason: ' + status);
        }
    });
}
google.maps.event.addDomListener(window, 'load', initialize)
