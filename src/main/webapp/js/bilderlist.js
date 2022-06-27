/**
 * view-controller für bildlist.html
 * @author Alexander Benak
 */
document.addEventListener("DOMContentLoaded", () => {
    readBilder();
});

/**
 * liest alle Biler
 */
function readBilder() {
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
 * zeigt die Biler als Tabelle
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
 * lenkt zu bildedit um
 * @param event
 */
function editBild(event) {
    const button = event.target;
    const bookUUID = button.getAttribute("data-bookuuid");
    window.location.href = "./bildedit.html?uuid=" + bildID;
}

/**
 * löscht ein bild
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