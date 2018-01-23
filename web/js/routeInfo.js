var $inputFrom = $('#pointFrom');
var $inputTo = $('#pointTo');
var $button = $('#getRouteInfoBtn');
var $routeClientInfo = $('#routeClientInfo');
$button.attr("disabled", true);


$($inputFrom).add($inputTo).on("change keyup", function () {
    if ($inputFrom.val() === '') {
        $button.attr("disabled", true);
        return;
    }
    if ($inputTo.val() === '') {
        $button.attr("disabled", true);
        return;
    }
    $button.attr("disabled", false);
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