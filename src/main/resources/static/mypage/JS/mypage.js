$(document).ready(function () {
    $(".mypageupdate button").click(function () {
        const value = $(this).val();
        if (value === "마이페이지") {
            $(".mypagelist form").show();
            $(".userinfo form").hide();
        } else if (value === "회원정보") {
            $(".mypagelist form").hide();
            $(".userinfo form").show();
        }
    });

    $(".house").change(function () {
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

    $(".tour").change(function () {
        const tourId = $(this).val();
        const data = {
            "tour_id": tourId,
            "user_id": logged_id,
        };

        $.ajax({
            url: "/house/scraptTour",
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
});
