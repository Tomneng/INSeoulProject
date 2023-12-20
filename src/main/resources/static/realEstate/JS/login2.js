$(document).ready(function () {
    let value;
    $("#getpassword").click(function (event) {
        const data = {
            "email": $("#email").val()
        }
        $.ajax({
            url: "/authConfirm/getPass",
            type: "GET",
            data: data,
            cache: false,
            success: function (data, status) {
                if (status == "success") {
                    alert("이메일 발송완료")
                }
            }
        })
    })
})