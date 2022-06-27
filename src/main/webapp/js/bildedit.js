/**
 * view-controller for bildedit.html
 * @author Marcel Suter
 */
document.addEventListener("DOMContentLoaded", () => {
    readAusstellungen();
    readBild();

    document.getElementById("save").addEventListener("submit", saveBook);
    document.getElementById("cancel").addEventListener("click", cancelEdit);
});

/**
 * saves the data of a book
 */
function saveBook(event) {
    event.preventDefault();

    const bildForm = document.getElementById("bildeditForm");
    const formData = new FormData(bildForm);
    const data = new URLSearchParams(formData);

    let method;
    let url = "./resource/bild/";
    const bildUUID = getQueryParam("uuid");
    if (bildUUID == null) {
        method = "POST";
        url += "create";
    } else {
        method = "PUT";
        url += "update";
    }

    fetch(url,
        {
            method: method,
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: data
        })
        .then(function (response) {
            if (!response.ok) {
                console.log(response);
            } else return response;
        })
        .then()
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * reads a book
 */
function readBild() {
    const bildUUID = getQueryParam("uuid");
    fetch("./resource/bild/read?uuid=" + bildUUID)
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showBild(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * show the data of a book
 * @param data  the book-data
 */
function showBild(data) {
    document.getElementById("bildUUID").value = data.bildID;
    document.getElementById("name").value = data.name;
    document.getElementById("kuenstler").value = data.kuenstler;
    document.getElementById("datum").value = data.datum;
    document.getElementById("art").value = data.art;
    document.getElementById("preis").value = data.preis;
}

/**
 * reads all publishers as an array
 */
function readAusstellungen() {

    fetch("./resource/ausstellung/list")
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showAusstellungen(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * shows all publishers as a dropdown
 * @param data
 */
function showAusstellungen(data) {
    let dropdown = document.getElementById("publisher");
    data.forEach(publisher => {
        let option = document.createElement("option");
        option.text = publisher.publisherName;
        option.value = publisher.publisherUUID;
        dropdown.add(option);
    })
}

/**
 * redirects to the bookshelf
 * @param event  the click-event
 */
function cancelEdit(event) {
    window.location.href = "./bilderlist.html";
}