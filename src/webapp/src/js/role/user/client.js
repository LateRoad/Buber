function calculateRouteInfo() {
    $.ajax({
        url: "/userOperation?action=getRouteInfo&from=" + $originInput.val() + "&to=" + $destinationInput.val() + "&fromLat=" + origin.position.lat() + "&fromLng=" + origin.position.lng() + "&toLat=" + destination.position.lat() + "&toLng=" + destination.position.lng(),
        data: {name: 'abc'},
        type: 'post',
        cache: false,
        success: function () {
            $('#routeClientInfo').load("/home.jsp" + ' #routeClientInfo');
        },
        error: function () {
            alert('errort');
        }
    });
}


function takeTaxi(driver, money) {
    $.ajax({
        url: "/userOperation?action=takeTaxi&driver=" + driver + "&money=" + money,
        data: {name: 'abc'},
        type: 'POST',
        cache: false,
        success: function () {
            $('#routeClientInfo').hide();
            $('#modal').modal('show');
        },
        error: function () {
            alert('error Taxi');
        }
    });
}
