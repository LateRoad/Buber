let $originInput = $('#originInput');
let $destinationInput = $('#destinationInput');

$($originInput).add($destinationInput).on("focusout", function () {
    if ($originInput.val() === '') {
        $routeClientInfo.hide();
        return;
    }
    if ($destinationInput.val() === '') {
        $routeClientInfo.hide();
        return;
    }
    calculateRouteInfo();
    $routeClientInfo.show();
    showDirection();
});