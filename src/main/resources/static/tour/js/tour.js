$(document).ready(function () {
    $("[name='pageRows']").change(function (){
        let frm = $("[name='frmPageRows']");
        frm.attr("method", "POST")
        frm.attr("action", "pageRows")
        frm.submit();
    })
    const userId = {
        "user_id": logged_id,
    };
    $.ajax({
        url: "/tour/scrapted",
        type: "GET",
        data: userId,
        cache: false,
        success: function (data, status){
            if (status == "success"){
                // firstElementChild : 선택된 객체의 첫 번째 자식 요소를 반환
                const init = document.querySelector("#container1").firstElementChild
                const val = parseInt(init.firstElementChild.value);
                console.log(init)
                console.log(val)
                for (i = val; i > val - 12; i--) {
                    if (data.includes(i)) {
                        document.getElementById(`${i}`).checked=true;
                    }
                }
            }
        },
    })

    $(".divider input").change(function (){
        const tourId = $(this).val();
        const data = {
            "tour_id" : tourId,
            "user_id" : userId,
        };
        if ($(".divider input").is(":checked")){
            $.ajax({
                url: "/tour/scrapt",
                type: "POST",
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
        } else {
            $.ajax({
                url: "tour/scrapt",
                type: "POST",
                data: data,
                cache: false,
                success: function (data, status){
                    if (status == "success"){
                        if (data.status !== "DELETED"){
                            alert(data.status);
                            return;
                        }
                    }
                },
            });
        }
    });
});
