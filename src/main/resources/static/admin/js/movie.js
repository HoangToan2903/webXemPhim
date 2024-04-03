$(document).ready(function() {
    $(".selec").change(function() {
        var thisVal = $(this).val();
        var thisText = $(this).find(":selected").text();

        if(thisVal !== "") {
            // Create a new div with the selected option and a remove button
            var newEl = $("<div>").attr("data-value", thisVal).text(thisText);
            var removeButton = $("<button>").text("Remove").attr("data-target", thisVal);

            // Append the new elements to the container
            $(this).next(".container").append(newEl);
            $(this).next(".container").append(removeButton);

            // Remove the selected option from the dropdown
            $(this).find("option:selected").remove();
        }
    });

    $(".container").on("click", "button", function() {
        var targetVal = $(this).data("target");
        var targetText = $(this).prev("div").text();

        // Create a new option and append it back to the select dropdown
        var newOption = $("<option>").attr("value", targetVal).text(targetText);
        $(this).closest(".row").find(".selec").append(newOption);

        // Remove the div and the button
        $(this).prev("div").remove();
        $(this).remove();
    });
});
