<%--
  Created by IntelliJ IDEA.
  User: Java
  Date: 27.02.2015
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="body">
            <div class="container">
                <script src="https://maps.googleapis.com/maps/api/js"></script>

                <script>

                    function initialize()
                    {
                        geocoder = new google.maps.Geocoder();
                        var mapOptions =
                        {
                            zoom: 12,
                            mapTypeId: google.maps.MapTypeId.ROADMAP
                        }
                        map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

                        codeAddress("Івано-Франківськ, вул. Пулюя 7")

                        $.each(addressesAsStrings, function(index, value) {
                            codeAddress(value)
                        });

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
                </script>
                <div id="map-canvas" style="width: 500px; height: 400px;"></div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>