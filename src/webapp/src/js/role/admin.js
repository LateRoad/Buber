function setMuted(user, status) {
    $.ajax({
        url: "/servlet?action=setMuted&status=" + status + "&user=" + user,
        type: 'GET',
        success: function () {
            location.reload();
        }
    });
}
