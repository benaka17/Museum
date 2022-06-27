/**
 * view-controller for bookshelf.html
 * @author Alexander Benak
 */
document.addEventListener("DOMContentLoaded", () => {
    readBooks();
});

/**
 * reads all books
 */
function readBooks() {
    fetch("./resource/bild/list")
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showBooklist(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * shows the booklist as a table
 * @param data  the books
 */
function showBooklist(data) {
    let tBody = document.getElementById("bildList");
    data.forEach(bild => {
        let row = tBody.insertRow(-1);
        row.insertCell(-1).innerHTML = bild.bildID;
        row.insertCell(-1).innerHTML = bild.name;
        row.insertCell(-1).innerHTML = bild.kuenstler;
        row.insertCell(-1).innerHTML = bild.datum;
        row.insertCell(-1).innerHTML = bild.art;
        row.insertCell(-1).innerHTML = bild.preis;

        let button = document.createElement("button");
        button.innerHTML = "Bearbeiten ...";
        button.type = "button";
        button.name = "editBook";
        button.setAttribute("data-bilduuid", bild.bildID);
        button.addEventListener("click", editBild);
        row.insertCell(-1).appendChild(button);

        button = document.createElement("button");
        button.innerHTML = "Löschen ...";
        button.type = "button";
        button.name = "deleteBook";
        button.setAttribute("data-bilduuid", bild.bildID);
        button.addEventListener("click", deleteBild);
        row.insertCell(-1).appendChild(button);

    });
}

/**
 * redirects to the edit-form
 * @param event  the click-event
 */
function editBild(event) {
    const button = event.target;
    const bookUUID = button.getAttribute("data-bookuuid");
    window.location.href = "./bildedit.html?uuid=" + bildID;
}

/**
 * deletes a book
 * @param event  the click-event
 */
function deleteBild(event) {
    const button = event.target;
    const bookUUID = button.getAttribute("data-bookuuid");

    fetch("./resource/book/delete?uuid=" + bildID,
        {
            method: "DELETE"
        })
        .then(function (response) {
            if (response.ok) {
                window.location.href = "./museum.html";
            } else {
                console.log(response);
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}