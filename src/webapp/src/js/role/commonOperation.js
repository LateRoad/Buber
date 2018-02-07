function signIn(role) {
    let login = $("#inputLogin").val();
    let password = $("#inputPassword").val();
    $.ajax({
        url: "/servlet?action=signIn&login=" + login + "&password=" + password + "&role=" + role,
        type: 'post',
        async: true,
        success: function (data, response, options) {
            if (response.status = 200) {
                if (data === "ADMIN") {
                    window.location.assign("/clients.jsp");
                }
                else {
                    window.location.assign("/home.jsp");
                }
            }
        },
        error: function () {
            alert("fuck");
        }
    });
    event.preventDefault();
}

function changeLang(lang) {
    $.ajax({
        url: "/servlet?action=changeLanguage&lang=" + lang,
        type: 'GET',
        success: function () {
            location.reload();
        }
    });
}

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
    showDirection(origin, destination);
});