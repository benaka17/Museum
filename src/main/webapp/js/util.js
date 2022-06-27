/**
 * für mehrere pages zuständig
 *
 * @author  Marcel Suter
 * @since   2022-05-30
 * @version 1.0
 */

/**
 * holt den Wert eines URL Parameters
 * Quelle: https://www.sitepoint.com/get-url-parameters-with-javascript/
 * @param key
 * @returns werte als String oder null
 */
function getQueryParam (key) {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);

    return urlParams.get(key);
}

/**
 * holt den Wert eines Cookies mit bestimmtem Namen
 * Quelle: https://www.w3schools.com/js/js_cookies.asp
 * @param name
 * @returns {string}
 */
function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let cookieArray = decodedCookie.split(';');
    for(let i = 0; i <cookieArray.length; i++) {
        let cookie = cookieArray[i];
        while (cookie.charAt(0) == ' ') {
            cookie = cookie.substring(1);
        }
        if (cookie.indexOf(name) == 0) {
            return cookie.substring(name.length, cookie.length);
        }
    }
    return "";
}