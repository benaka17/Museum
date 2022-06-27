/**
 * javascript forms für das login (evaluierung)
 */
$(document).ready(function (){
   $("#loginForm").submit(sendLogin);
   $("#logoff").click(sendLogoff);
});

/**
 * für das login zuständig
 * @param form
 */
function sendLogin(form){
    form.preventDefault();
    $
        .ajax({
            url: "./resource/user/login",
            dataType: "text",
            type: "POST",
            data: $("#loginForm").serialize()
        })
        .done(function (){
            window.location.href = "./museum.html";
        })
        .fail(function (xhr, status, errorThrown){
            if (xhr.status == 404){
                $("#message").text("Benutzername/Passwort unbekannt")
            } else {
                $("#message").text("Es ist ein Fehler aufgetreten")
            }
        })
}

/**
 * für das ausloggen zuständig
 */
function sendLogoff(){
    $
        .ajax({
            url: "./resource/user/logoff",
            dataType: "text",
            type: "DELETE"
        })
        .done(function (){
            window.location.href = "./index.html";
        })
        .fail(function (xhr, status, errorThrown){

        })
}