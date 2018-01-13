// In the following example, markers appear when the user clicks on the map.
// The markers are stored in an array.
// The user can then click an option to hide, show or delete the markers.
var labels = 'AB';
var labelIndex = 0;
var directionsService = new google.maps.DirectionsService;
var directionsDisplay = new google.maps.DirectionsRenderer;

function initMap() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (p) {
            var latLng = new google.maps.LatLng(p.coords.latitude, p.coords.longitude);
            var mapOptions = {
                center: latLng,
                zoom: 13,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            var map = new google.maps.Map(document.getElementById("map"), mapOptions);

            var marker = new google.maps.Marker({
                position: latLng,
                map: map,
                title: "<div style = 'height:60px;width:200px'><b>Your location:</b><br />Latitude: " + p.coords.latitude + "<br />Longitude: " + p.coords.longitude
            });


            google.maps.event.addListener(map, 'click', function (event) {
                addMarker(event.latLng, map);
            });
            addMarker(latLng, map);
        });
    } else {
        alert('Geo Location feature is not supported in this browser.');
    }
}

function addMarker(location, map) {

    if (labelIndex == 1) {
        var marker = new google.maps.Marker({
            position: location,
            label: labels[labelIndex++],
            draggable: true,
            map: map
        });
        geocodeLatLng("pointTo", location);
        document.getElementById("getRouteInfoBtn").disabled = false;
    }
    if (labelIndex == 0) {
        var marker = new google.maps.Marker({
            position: location,
            label: labels[labelIndex++],
            map: map
        });
        geocodeLatLng("pointFrom", location);
    }
    marker.addListener('dragend', handleEvent);
}

function geocodeLatLng(inputId, coords) {
    var geocoder = new google.maps.Geocoder;
    var input = document.getElementById(inputId);
    var latlng = {lat: coords.lat(), lng: coords.lng()};
    geocoder.geocode({'location': latlng}, function (results, status) {
        if (status === 'OK') {
            if (results[1]) {
                input.value = results[0].formatted_address;
            } else {
                window.alert('No results found');
            }
        } else {
            window.alert('Geocoder failed due to: ' + status);
        }
    });
}

function handleEvent(event) {
    geocodeLatLng("pointTo", event.latLng);
}

function calculateAndDisplayRoute() {
    directionsService.route({
        origin: document.getElementById('pointFrom').value,
        destination: document.getElementById('pointTo').value,
        travelMode: 'DRIVING'
    }, function (response, status) {
        if (status === 'OK') {
            alert("!");
            directionsDisplay.setDirections(response);
        } else {
            window.alert('Directions request failed due to ' + status);
        }
    });
}