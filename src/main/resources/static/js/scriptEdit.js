const contains = (list, addText) => {
    for (let i = 0; i < list.length; i++) {
        if (list[i].textContent.toLowerCase() === addText.toLowerCase()) {
            return true;
        }
    }
    return false;
};

var addButton = document.querySelector(".add-button");
var mainImage = document.querySelector(".main-image");
var editItems = document.querySelector(".editing");

editItems.style.display = "block";

addButton.addEventListener('click', function () {
    mainImage.style.display = (mainImage.dataset.toggled ^= 1) ? "block" : "none";
    editItems.style.display = (editItems.dataset.toggled ^= 1) ? "none" : "block";

})

var exists = document.getElementById("exists");
var addSubmit = document.getElementById("add-submit");
var list = document.querySelectorAll("section ul li");

document.getElementById("add-text").addEventListener('keyup', function () {

    if (contains(list, document.getElementById("add-text").value)) {
        addSubmit.disabled = true;
        exists.style.display = "block";
    } else {
        addSubmit.disabled = false;
        exists.style.display = "none";
    }
})
