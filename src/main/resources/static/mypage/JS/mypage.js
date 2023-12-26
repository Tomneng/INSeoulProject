$(document).ready(function () {
    $(".divider input").change(function () {
        const houseId = $(this).val();
        const data = {
            "house_id": houseId,
            "user_id": logged_id,
        };

        $.ajax({
            url: "/house/scrapt",
            type: "POST",
            data: data,
            cache: false,
            success: function (data, status) {
                if (status == "success") {
                    if (data.status !== "DELETED") {
                        alert(data.status);
                        return;
                    }
                }
            },
        });
    });
})