function setMuted(user, status) {
    $.ajax({
        url: "/userOperation?action=setMuted&status=" + status + "&user=" + user,
        type: 'GET',
        success: function () {
            alert("f");
            location.reload();
        }
    });
}
