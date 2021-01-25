function init() {
    let res = null;
    $.ajaxSettings.async = false
    $.get("/index/init",function (response) {
        res = response.response
    })
    $.ajaxSettings.async = true
    return res;
}
function isLogin() {
    let login = false;
    $.ajaxSettings.async = false;
    $.get("/toLogin",function (response) {
        console.log("response:")
        console.log(response)
        if (response.response !==null && response.response!==""){
            login = true;
            console.log(response.response);
            $(".after-login-up").css("display","block");
            $(".avatar").attr("src",response.response.avatar_url);
            $(".username").html(response.response.login);
            $(".login-button").css("display","none");
        }
    })
    $.ajaxSettings.async = true;
    return login;
}