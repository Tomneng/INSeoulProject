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
        url: "/tour/scrapted",
        type: "GET",
        data: userId,
        cache: false,
        success: function (data, status) {
            const those = [];
            for (let i = 0; i < document.getElementsByClassName("tours").length; i++) {
                those.push(document.getElementsByClassName("tours")[i].querySelector("input").value);
            }
            console.log(those)
            if (status === "success") {
                for (let j = 0; j < those.length; j++) {
                    if (data.includes(those[j])) {
                        const val = those[j]
                        document.getElementById(`${val}`).checked = true;
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

    // 체크박스 상태 관리 배열
    const checkedItems = [];

    // AJAX를 사용하여 서버에 스크랩 데이터 전송
    $(".divider input").change(function () {
        const tourId = $(this).val();
        const data = {
            "tour_id": tourId,
            "user_id": logged_id,
        };

        if ($(this).is(":checked")) {
            // 체크된 경우 배열에 추가
            if (!checkedItems.includes(tourId)) {
                checkedItems.push(tourId);
            }
        } else {
            // 체크 해제된 경우 배열에서 제거
            const index = checkedItems.indexOf(tourId);
            if (index !== -1) {
                checkedItems.splice(index, 1);
            }
        }

        // AJAX를 사용하여 서버에 데이터 전송
        $.ajax({
            url: "/tour/scrapt",
            type: "POST",
            data: data,
            cache: false,
            success: function (data, status) {
                if (status === "success") {
                    if (data.status !== "OK") {
                        alert(data.status);
                    }
                }
            },
            error: function (request, status, error) {
                console.log("code: " + request.status);
                console.log("message: " + request.responseText);
                console.log("error: " + error);
            },
        });
    });
});
