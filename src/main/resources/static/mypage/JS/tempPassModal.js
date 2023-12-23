$(document).ready(function () {
    $("#changed").click(function () {
        const password = $("#password").val();
        const userId = $("#userId").val();
        const data = {
            "password" : password,
            "userId" : userId
        }

        $.ajax({
            url: "/house/chpass",
            type: "POST",
            data: data,
            cache: false,
            success: function (data, status) {
                if (status == "success") {
                    return
                }
            }
        })
    })

})