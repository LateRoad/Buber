//
//    Services
//
let directionsService;
let directionsDisplay;
//
//    General variables
//
var map;
//
//    Input fields
//

var originInput = document.getElementById('originInput');
var destinationInput = document.getElementById('destinationInput');
//
//    Markers
//
var origin;
var destination;

var $routeClientInfo = $('#routeClientInfo');

$routeClientInfo.hide();

var image = {
    icon: '/src/image/map/marker.svg',
    // This marker is 20 pixels wide by 32 pixels high.
    size: new google.maps.Size(20, 32),
    // The origin for this image is (0, 0).
    origin: new google.maps.Point(0, 0),
    // The anchor for this image is the base of the flagpole at (0, 32).
    anchor: new google.maps.Point(0, 32)
};


function updateActiveOrders() {
    $.ajax({
        url: "/userServlet?action=updateActiveOrders",
        data: {name: 'abc'},
        type: 'post',
        cache: false,
        success: function () {
            $('#activeOrders').load("/home.jsp" + ' #activeOrders');
        },
        error: function () {
            alert('error');
        }
    });
}

function calculateRouteInfo() {
    $.ajax({
        url: "/userServlet?action=getRouteInfo&from=" + $originInput.val() + "&to=" + $destinationInput.val() + "&fromLat=" + origin.position.lat() + "&fromLng=" + origin.position.lng() + "&toLat=" + destination.position.lat() + "&toLng=" + destination.position.lng(),
        data: {name: 'abc'},
        type: 'post',
        cache: false,
        success: function () {
            $('#routeClientInfo').load("/home.jsp" + ' #routeClientInfo');
        },
        error: function () {
            alert('error');
        }
    });
}

function showDirection() {
    directionsService.route({
        origin: origin.position,
        destination: destination.position,
        travelMode: 'DRIVING'
    }, function (response, status) {
        if (status === 'OK') {
            directionsDisplay.setDirections(response);
        } else {
            window.alert('Directions request failed due to ' + status);
        }
    });
}


//
//    Map handler
//
function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 54, lng: 28},
        zoom: 6
    });
    directionsService = new google.maps.DirectionsService();
    directionsDisplay = new google.maps.DirectionsRenderer();
    var searchBoxOrigin = new google.maps.places.SearchBox(originInput);
    var searchBoxDestination = new google.maps.places.SearchBox(destinationInput);

    addInputToMarkerListenerOrigin(searchBoxOrigin);
    addInputToMarkerListenerDestination(searchBoxDestination);

    getCurrentLocation();

    directionsDisplay.setMap(map);
    directionsDisplay.setOptions({suppressMarkers: true});

    // google.maps.event.addListener(map, 'click', function (event) {
    //     addMarker(event.latLng, map);
    // });
    // addMarker(latLng, map);
    // $.ajax({
    //     url: "/userServlet?action=setCurrentLocation&lat=" + latLng.lat() + "&lng=" + latLng.lng(),
    //     data: {name: 'abc'},
    //     type: 'get',
    //     cache: false,
    //     success: function () {
    //         alert("success");
    //     },
    //     error: function () {
    //         alert('error');
    //     }
    // });
}

function addInputToMarkerListenerDestination(searchBox) {
    searchBox.addListener('places_changed', function () {
        let places = searchBox.getPlaces();

        if (places.length === 0) {
            return;
        }

        // Clear out the old markers.
        if (destination != null) {
            destination.setMap(null);
        }

        // For each place, get the icon, name and location.
        let bounds = new google.maps.LatLngBounds();
        // places.forEach(function (place) {
        if (!places[0].geometry) {
            console.log("Returned place contains no geometry");
            return;
        }

        // Create a marker for each place.
        setDestination({
            lat: places[0].geometry.location.lat(),
            lng: places[0].geometry.location.lng()
        });
        $destinationInput.keyup();


        if (places[0].geometry.viewport) {
            // Only geocodes have viewport.
            bounds.union(places[0].geometry.viewport);
        } else {
            bounds.extend(places[0].geometry.location);
        }
        map.fitBounds(bounds);
    });
}

function addInputToMarkerListenerOrigin(searchBox) {
    searchBox.addListener('places_changed', function () {
        let places = searchBox.getPlaces();

        if (places.length === 0) {
            return;
        }

        // Clear out the old markers.
        if (origin != null) {
            origin.setMap(null);
        }

        // For each place, get the icon, name and location.
        let bounds = new google.maps.LatLngBounds();
        if (!places[0].geometry) {
            console.log("Returned place contains no geometry");
            return;
        }

        // Create a marker for each place.
        setOrigin({
            lat: places[0].geometry.location.lat(),
            lng: places[0].geometry.location.lng()
        });
        $originInput.keyup();


        if (places[0].geometry.viewport) {
            // Only geocodes have viewport.
            bounds.union(places[0].geometry.viewport);
        } else {
            bounds.extend(places[0].geometry.location);
        }
        map.fitBounds(bounds);
    });
}

function setOrigin(location) {
    origin = new google.maps.Marker({
        position: location,
        icon: {
            url: '/src/image/map/marker.svg',
            scaledSize: new google.maps.Size(32, 32)
        },
        draggable: true,
        map: map
    });
    map.setCenter(origin.position);
    map.setZoom(14);
    origin.addListener('dragend', setOriginInput);
}

function setDestination(location) {
    destination = new google.maps.Marker({
        position: location,
        icon: {
            url: '/src/image/map/marker.svg',
            scaledSize: new google.maps.Size(32, 32)
        },
        draggable: true,
        map: map
    });
    map.setCenter(destination.position);
    map.setZoom(14);
    destination.addListener('dragend', setDestinationInput);
}

function setOriginInput(event) {
    origin.setPosition({
        lat: event.latLng.lat(),
        lng: event.latLng.lng()
    });
    fillInput(origin.position, $originInput);
}

function setDestinationInput(event) {
    destination.setPosition({
        lat: event.latLng.lat(),
        lng: event.latLng.lng()
    });
    fillInput(destination.position, $destinationInput);
}


function fillInput(coordinates, input) {
    let geocoder = new google.maps.Geocoder;
    // alert(coordinates.lat);
    // alert(coordinates.lat());
    let latlng = {lat: coordinates.lat(), lng: coordinates.lng()};
    geocoder.geocode({'location': latlng}, function (results, status) {
        if (status === 'OK') {
            if (results[0]) {
                let result =
                    results[0].address_components[4].long_name + ", " +
                    results[0].address_components[3].long_name + ", " +
                    results[0].address_components[1].long_name + ", " +
                    results[0].address_components[0].long_name;
                input.val(result);
                input.focusout();
            } else {
                alert('No results found');
            }
        } else {
            alert('Geocoder failed due to: ' + status);
        }
    });
}


//
//    Handle current location
//
function getCurrentLocation() {
    let currentLocation;
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            currentLocation = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            setOrigin(currentLocation);
            fillInput(origin.position, $originInput);
        }, function () {
            handleLocationError(true, map.getCenter());
        });
    } else {
        // Browser doesn't support Geolocation
        handleLocationError(false, map.getCenter());
    }
}

// let curPosTimer = setInterval(showCurrentLocation, 5000);


//
//    Error handling
//
function handleLocationError(browserHasGeolocation, pos) {
    let infoWindow = new google.maps.InfoWindow({map: map});
    infoWindow.setPosition(pos);
    infoWindow.setContent(browserHasGeolocation ?
        'Error: The Geolocation service failed.' :
        'Error: Your browser doesn\'t support geolocation.');
}
