const xhr = new XMLHttpRequest();
xhr.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        const res = JSON.parse(xhr.response);
        const container = document.querySelector(".ajax-container");
        // Use the data that came back
        console.log(xhr.responseText);

        res.forEach(function(tag) {
            const tagItem = document.createElement('div');
            const tagName = document.createElement('p');
            tagName.innerText = tag.name;

            container.appendChild(tagItem);
            tagItem.appendChild(tagName);



        })
    }
}

// Choose the request method (GET, POST, etc.) and URL
xhr.open('GET', 'http://localhost:8080/edit-tags/', true);
xhr.send();