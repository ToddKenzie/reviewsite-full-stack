var addButton = document.querySelector(".add-button");
var mainImage = document.querySelector(".main-image");
var addItem = document.querySelector(".add-item");


mainImage.style.display = "none";
addItem.style.display = "block";

addButton.addEventListener('click', function () {
    mainImage.style.display = (mainImage.dataset.toggled ^= 1) ? "block" : "none";
    addItem.style.display = (addItem.dataset.toggled ^= 1) ? "none" : "block";

})
