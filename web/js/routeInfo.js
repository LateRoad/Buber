var $inputFrom = $('#pointFrom');
var $inputTo = $('#pointTo');
var $button = $('#getRouteInfoBtn');
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