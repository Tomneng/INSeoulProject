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
                if (status === "success") {
                    if (data.status !== "DELETED") {
                        alert(data.status);
                        return;
                    }
                }
            },
        });
    });

    // $(".divider input").change(function () {
    //     const tourId = $(this).val();
    //     const data = {
    //         "tour_id": tourId,
    //         "user_id": logged_id,
    //     };
    //
    //     $.ajax({
    //         url: "/tour/scrapt",
    //         type: "POST",
    //         data: data,
    //         cache: false,
    //         success: function (data, status) {
    //             if (status === "success") {
    //                 if (data.status !== "DELETED") {
    //                     alert(data.status);
    //                     return;
    //                 }
    //             }
    //         },
    //     });
    // });
});
