$(document).ready(function () {

    $("#answered").click(function () {
        let form = document.forms['answers'];
        form.submit();
        const data = {
            "email": $("#email").text(),
            "answer": $("#answer").val()
        }
        $.ajax({
            url: "/authConfirm/getAnswer",
            type: "GET",
            data: data,
            cache: false,
            success: function (data, status) {
                if (status == "success") {
                    if (data.status !== "OK") {
                        alert(data.status);
                        return;
                    }
                }
            },
        });
    })
})

