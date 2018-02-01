function signIn(role) {
    let login = $("#inputLogin").val();
    let password = $("#inputPassword").val();
    $.ajax({
        url: "/commonOperation?action=signIn&login=" + login + "&password=" + password + "&role=" + role,
        type: 'POST',
        async: false,
        success: function (data, response, options) {
            alert("1");
            if (response.status = 200) {
                if (data === "admin") {
                    window.location.assign("home.jsp");
                }
                else {
                    alert("home");
                    window.location.assign("home.jsp");
                }
            }
        },
        error: function () {
            alert("fuck");
        }
    });
}

function changeLang(lang) {
    $.ajax({
        url: "/commonOperation?action=changeLanguage&lang=" + lang,
        data: {name: 'abc'},
        type: 'POST',
        cache: false,
        success: function () {
            location.reload();
        },
        error: function (value) {
            alert(value);
        }
    });
}

