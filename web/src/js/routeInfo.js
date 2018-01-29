var $originInput = $('#originInput');
var $destinationInput = $('#destinationInput');

$($originInput).add($destinationInput).on("focusout", function () {
    alert("1");
    if ($inputFrom.val() === '') {
        $routeClientInfo.hide();
        return;
    }
    if ($inputTo.val() === '') {
        $routeClientInfo.hide();
        return;
    }
    //showDirection();
    calculateRouteInfo();
    $routeClientInfo.show();
});