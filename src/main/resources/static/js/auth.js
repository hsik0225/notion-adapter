import * as properties from './properties.js'

$(document).ready(function () {
    $("#login-button").click(login);
    $("#logout-button").click(logout);

    if (localStorage.getItem("accessToken") === null) {
        $("#login").css("display", "");
        $("#logout").css("display", "none");
    } else {
        $("#login").css("display", "none");
        $("#logout").css("display", "");
    }
});

function login() {
    location.href = properties.LOGIN_OAUTH_URI;
    localStorage.setItem("accessToken", Cookies.get('accessToken'));
}

function logout() {
    localStorage.removeItem("accessToken");
    window.location.reload();
}


