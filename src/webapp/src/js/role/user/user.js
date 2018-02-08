function change(item, button) {
    if (document.getElementById(button).firstChild.data === "Save") {
        document.getElementById(button).firstChild.data = "Edit";
        var input = document.getElementById(item);
        var textEdited = document.getElementById(item + "Edited").value;
        input.innerText = textEdited;
        var xhttp = new XMLHttpRequest();
        xhttp.open("POST", "/userOperation?action=updateInfo&edited=" + item + "&value=" + textEdited, true);
        xhttp.send();
    }
    else {
        document.getElementById(button).firstChild.data = "Save";
        var td = document.getElementById(item);
        var text = td.innerHTML;
        var input = document.createElement("input");
        input.type = "text";
        input.value = text;
        input.id = item + "Edited";
        td.innerHTML = "";
        td.appendChild(input);
    }
}
