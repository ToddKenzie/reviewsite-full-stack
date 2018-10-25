var addButton = document.querySelector(".add-button");
var mainImage = document.querySelector(".main-image");
var addItem = document.querySelector(".add-item");

addButton.addEventListener('click', function () {
    mainImage.style.display = (mainImage.dataset.toggled ^= 1) ? "none" : "block";
    addItem.style.display = (addItem.dataset.toggled ^= 1) ? "block" : "none";

})
