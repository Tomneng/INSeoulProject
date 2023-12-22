$(document).ready(function () {
    $(".mypageupdate button").click(function () {
        const value = $(this).val();
        if (value === "마이페이지") {
            $(".mypagelist form").show();
            $(".userinfoform form").hide();
        } else if (value === "회원정보") {
            $(".mypagelist form").hide();
            $(".userinfoform form").show();
        }
    });

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

    $(".divider input").change(function () {
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

// const frm1 = document.forms['frm1'];
// const frm2 = document.forms['frm2'];
//
// let btns = document.querySelectorAll('button[type=button][name="button"]');
// console.log("btns = " + btns)
//
//
// btns.forEach(btn => btn.addEventListener('click', () => {
//         console.log("btn = " + btn)
//         if (btn.value == "마이페이지") {
//             frm1.style.display = 'block';
//             frm2.style.display = 'none';
//         }
//         if (btn.value == "회원정보") {
//             frm1.style.display = 'none';
//             frm2.style.display = 'block';
//         }
//
//     }
// ));

