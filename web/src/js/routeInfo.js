var $inputFrom = $('#origin');
var $inputTo = $('#destination');
var $button = $('#getRouteInfoBtn');
var $routeClientInfo = $('#routeClientInfo');
$button.attr("disabled", true);


$($inputFrom).add($inputTo).on("change keyup", function () {
    if ($inputFrom.val() === '') {
        $button.attr("disabled", true);
        $routeClientInfo.hide();
        return;
    }
    if ($inputTo.val() === '') {
        $button.attr("disabled", true);
        $routeClientInfo.hide();
        return;
    }
    $button.attr("disabled", false);
    showDirection();
    calculateRouteInfo();
    $routeClientInfo.show();
});


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
        url: "/userServlet?action=getRouteInfo&from=" + $("#pointFrom").val() + "&to=" + $("#pointTo").val() + "&fromLat=" + markers[0].lat() + "&fromLng=" + markers[0].lng() + "&toLat=" + markers[1].lat() + "&toLng=" + markers[1].lng(),
        data: {name: 'abc'},
        type: 'post',
        cache: false,
        success: function () {
            alert("w");
            $('#routeClientInfo').load("/home.jsp" + ' #routeClientInfo');
        },
        error: function () {
            alert('error');
        }
    });
}

function showDirection() {
    directionsService.route({
        origin: markers[0],
        destination: markers[1],
        travelMode: 'DRIVING'
    }, function (response, status) {
        if (status === 'OK') {
            directionsDisplay.setDirections(response);
        } else {
            window.alert('Directions request failed due to ' + status);
        }
    });
}