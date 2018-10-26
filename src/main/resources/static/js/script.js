const contains = (list, checkText) => {
    for (let i = 0; i < list.length; i++) {
        if (list[i].textContent.toLowerCase() === checkText.toLowerCase()) {
            return true;
        }
    }
    return false;
};

var editButton = document.querySelector(".edit-button");
var mainImage = document.querySelector(".main-image");
var editItems = document.querySelector(".editing");


mainImage.style.display = "block";

editButton.addEventListener('click', function () {
    mainImage.style.display = (mainImage.dataset.toggled ^= 1) ? "none" : "block";
    editItems.style.display = (editItems.dataset.toggled ^= 1) ? "block" : "none";

})

var exists = document.getElementById("exists");
var addSubmit = document.getElementById("add-submit");
var list = document.querySelectorAll("section ul li");

var addText = document.getElementById("add-text");

addText.addEventListener('keyup', function () {
    if (contains(list, addText.value)) {
        addSubmit.disabled = true;
        exists.style.display = "block";
    } else if (addText.value === "") {
        addSubmit.disabled = true;
    } else {
        addSubmit.disabled = false;
        exists.style.display = "none";
    }
})

var deleteText = document.getElementById("delete-text");
var deleteSubmit = document.getElementById("delete-submit");

deleteText.addEventListener('keyup', function() {
    if (contains(list, deleteText.value)) {
        deleteSubmit.disabled = false;
    } else {
        deleteSubmit.disabled = true;
    }
})

