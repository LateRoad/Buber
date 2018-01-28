//
//    General variables
//
let map;
let currentLocation;
let origin;
let destination;
let originInput = document.getElementById('origin');
let destinationInput = document.getElementById('destination');

let image = {
    icon: '/src/image/map/marker.svg',
    // This marker is 20 pixels wide by 32 pixels high.
    size: new google.maps.Size(20, 32),
    // The origin for this image is (0, 0).
    origin: new google.maps.Point(0, 0),
    // The anchor for this image is the base of the flagpole at (0, 32).
    anchor: new google.maps.Point(0, 32)
};


let directionsService;
let directionsDisplay;
let markers;

//
//    Map handler
//
function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 54, lng: 28},
        zoom: 6
    });
    // // directionsService = new google.maps.DirectionsService();
    // let searchBoxOrigin = new google.maps.places.SearchBox(originInput);
    // let searchBoxDestination = new google.maps.places.SearchBox(destinationInput);
    // // directionsDisplay = new google.maps.DirectionsRenderer();
    // markers = [];
    // alert("3");
    // addInputToMarkerListener(searchBoxDestination);

    showCurrentLocation();

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

function addInputToMarkerListener(searchBox) {
    alert("1");
    searchBox.addListener('places_changed', function() {
        alert("2");
        let places = searchBox.getPlaces();

        if (places.length === 0) {
            return;
        }

        // Clear out the old markers.
        markers.forEach(function (marker) {
            marker.setMap(null);
        });

        // For each place, get the icon, name and location.
        let bounds = new google.maps.LatLngBounds();
        places.forEach(function (place) {
            if (!place.geometry) {
                console.log("Returned place contains no geometry");
                return;
            }

            // Create a marker for each place.
            markers.push(new google.maps.Marker({
                map: map,
                icon: {
                    url: '/src/image/map/marker.svg',
                    scaledSize: new google.maps.Size(32, 32)
                },
                title: place.name,
                position: place.geometry.location
            }));

            if (place.geometry.viewport) {
                // Only geocodes have viewport.
                bounds.union(place.geometry.viewport);
            } else {
                bounds.extend(place.geometry.location);
            }
        });
        map.fitBounds(bounds);
    });
}

function setOriginInput(event) {
    origin = {
        lat: event.latLng.lat(),
        lng: event.latLng.lng()
    };
    fillInput(origin, "#origin");
}

function showOrigin() {
    origin = new google.maps.Marker({
        position: currentLocation,
        icon: {
            url: '/src/image/map/marker.svg',
            scaledSize: new google.maps.Size(32, 32)
        },
        draggable: true,
        map: map
    });
    origin.addListener('dragend', setOriginInput);
}



function fillInput(coordinates, input) {
    let geocoder = new google.maps.Geocoder;
    let latlng = {lat: coordinates.lat, lng: coordinates.lng};
    let result;
    geocoder.geocode({'location': latlng}, function (results, status) {
        if (status === 'OK') {
            if (results[0]) {
                result = results[0].address_components[4].long_name + ", " + results[0].address_components[3].long_name + ", " + results[0].address_components[1].long_name + ", " + results[0].address_components[0].long_name;
                $(input).val(result);
                $(input).change();
            } else {
                window.alert('No results found');
            }
        } else {
            window.alert('Geocoder failed due to: ' + status);
        }
    });
}


//
//    Handle current location
//
function setCurrentLocation(location) {
    currentLocation = location;
}

function showCurrentLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            setCurrentLocation({
                lat: position.coords.latitude,
                lng: position.coords.longitude
            });
            map.setCenter(currentLocation);
            map.setZoom(13);
            showOrigin();
            fillInput(currentLocation, "#origin");
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


