$(document).ready(function () {
    
    // 페이지 크기 변경 시, 폼을 제출하는 코드 (기존 코드 유지)
    $("[name='pageRows']").change(function () {
        let frm = $("[name='frmPageRows']");
        frm.attr("method", "POST");
        frm.attr("action", "pageRows");
        frm.submit();
    });

    // 사용자 ID 정보
    const userId = {
        "user_id": logged_id,
    };

    // AJAX를 사용하여 서버에서 스크랩된 정보 가져오기
    $.ajax({
        url: "/house/scraptedTour",
        type: "GET",
        data: userId,
        cache: false,
        success: function (data, status) {
            // console.log(those);
            if (status === "success") {
                for (i = 0; i < 12; i++) {
                    var id = parseInt(document.getElementsByClassName("seeScrapted").item(i).id);
                    if(data.includes(id)){
                        document.getElementById(`${id}`).checked = true;
                    }
                }
            }
        },
        error: function (request, status, error) {
            console.log("code: " + request.status)
            console.log("message: " + request.responseText)
            console.log("error: " + error);
        },
    });

    $(".divider input").change(function (){
        const tourId = $(this).val();
        const data = {
            "tour_id" : tourId,
            "user_id" : logged_id,
        };
        if ($(".divider input").is(":checked")){
            $.ajax({
                url: "/house/scraptTour",
                type: "POST",
                data: data,
                cache: false,
                success: function (data, status) {
                    if (status === "success") {
                        if (data.status !== "OK") {
                            alert(data.status);
                            return;
                        }
                    }
                },
            });
        } else {
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
        }
    });
});

// 여기부터 철희가 추가 12/19
// 헤더에 active class 추가
$("#tabTour").addClass("active");
