function change(item, button) {
    if (document.getElementById(button).firstChild.data === "Сохранить") {
        document.getElementById(button).firstChild.data = "Редактировать"
    } else {
        document.getElementById(button).firstChild.data = "Сохранить";
    }
}