$("#registerForm").submit(function (e) {
    $.ajax({
        url: "/register",
        method: "POST",
        data: $("#registerForm").serialize(),
        success: function (data) {
            if (data != "success") {
                $("#register").popover('show');
                setTimeout(function () {
                    $("#register").popover('hide');
                }, 2000);
            } else {
                document.location = "/";
            }
        }
    });
    return false;
});
$("#loginForm").submit(function (e) {
    $.ajax({
        url: "/login",
        method: "POST",
        data: $("#loginForm").serialize(),
        success: function (data) {
            console.log(typeof(data));
            if (data != "success") {
                $("#login").popover('show');
                setTimeout(function () {
                    $("#login").popover('hide');
                }, 2000);
            } else {
                document.location = "/";
            }
        }
    });
    return false;
});