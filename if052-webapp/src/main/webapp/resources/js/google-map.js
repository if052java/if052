function initialize()
{
    geocoder = new google.maps.Geocoder();
    var mapOptions =
    {
        zoom: 12,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

    var arrayData = gMapData.split("~")
    for (var i=0; i<arrayData.length; i++){
        codeAddress(arrayData[i])
    }

    document.getElementById('addMarker').onclick = function() {
        google.maps.event.addListener(map, 'click', function(e) {
            map.setOptions({draggableCursor:''});
            placeMarker(e.latLng, map);
        });
    };
}

function placeMarker(position, map) {
    var marker = new google.maps.Marker({
        position: position,
        map: map
    });
    map.panTo(position);
    google.maps.event.clearListeners(map, 'click');
    var contentString =
        '<form action="/addStringAddress">' +
        '<table>'+
        '<tbody>'+
            '<tr>'+
                '<td>'+
                    '<input class="form-control" type="text" ' +
                         'name="addressString" id=' + position.lat() + '~' + position.lng() +'>' +
                '</td>'+
                '<td>'+
                    '<button class="btn btn-primary" type="submit">Зберегти</button>'+
                '</td>'+
            '</tr>'+
        '</tbody>'+
        '</table>'+
        '</form>';
    var infowindow = new google.maps.InfoWindow({
        content: contentString
    });
    infowindow.open(map,marker);
    document.getElementById("" + position.lat() + "~" + position.lng() + "").value = codeLatLng(position);
}
// geocode (returns location (X,Y))
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
                    position: results[0].geometry.location,
                    title: address
                });
            google.maps.event.addListener(marker, 'click', function() {
                map.setZoom(16);
                map.setCenter(marker.getPosition());
            });
        } else {
            $(".map-warning").fadeIn();
        }
    });
}
// reverse geocode (returns address)
function codeLatLng(position) {
    //var input = document.getElementById("latlng").value;
    //var latlngStr = input.split(",",2);
    //var lat = parseFloat(latlngStr[0]);
    //var lng = parseFloat(latlngStr[1]);
    //var latlng = new google.maps.LatLng(lat, lng);
    geocoder.geocode({'latLng': position}, function(results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            if (results[1]) {
                return results[1].formatted_address;
            }
        } else {
            alert("Geocoder failed due to: " + status);
        }
    });
}

google.maps.event.addDomListener(window, 'load', initialize);


