function signIn(role) {
    let login = $("#inputLogin").val();
    let password = $("#inputPassword").val();
    $.ajax({
        url: "/commonOperation?action=signIn&login=" + login + "&password=" + password + "&role=" + role,
        type: 'post',
        async: true,
        success: function (data) {
            if (data === "ADMIN") {
                window.location.assign("/clients.jsp");
            }
            else {
                window.location.assign("/home.jsp");
            }
        },
        error: function (data) {
            $('.modal-title').text(data.responseText);
            $('#modal').modal('show');
        }
    });
    event.preventDefault();
}

function changeLang(lang) {
    $.ajax({
        url: "/commonOperation?action=changeLanguage&lang=" + lang,
        type: 'GET',
        success: function (data) {

            location.reload();
        }
    });
}

function register(role) {
    $.ajax({
        url: "/commonOperation?action=register&role=" + role + "&" + $("#registration_form").serialize(),
        type: 'post',
        async: true,
        success: function (data) {
            if (data === "ADMIN") {
                window.location.assign("/clients.jsp");
            }
            else {
                window.location.assign("/home.jsp");
            }
        },
        error: function (data) {
            $('.modal-title').text(data.responseText);
            $('#modal').modal('show');
        }
    });
    event.preventDefault();
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

