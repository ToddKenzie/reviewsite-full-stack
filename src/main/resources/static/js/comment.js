var addUsername = document.getElementById("add-username");
var addText = document.getElementById("add-text");
var submitComment = document.getElementById("submit-comment");

document.addEventListener("keyup", function() {
    if(addUsername.value !== "" && addText.value !== "") {
        submitComment.disabled = false;
    } else {
        submitComment.disabled = true;
    }
})