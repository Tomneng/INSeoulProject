// $(document).ready(function() {
//     // $('isfj_btn').click(function (){ }),
//     // $('isfp_btn').click(function (){}),
//     // $('isnj_btn').click(function (){}),
//     // $('isnp_btn').click(function (){})
//
//     $(".mbti_class").on('click','input',function (){
//         const data = {"data" : $(this).val()}
//         $.ajax({
//             url: '/board/list', // 요청을 보낼 URL
//             method: 'POST', // HTTP 요청 메서드 (GET, POST, PUT, DELETE 등)
//             data:data,
//             success: function(response) {
//
//         },error: function(jqXHR, textStatus, errorThrown) {
//                 console.error('HTTP 응답 코드:', jqXHR.status,'에러 메시지:', errorThrown);
//             }
//         });
//     })
//
// });

$(function(){
    $("[name='pageRows']").change(function(){
        let frm = $("[name='frmPageRows']");
        frm.attr("method", "POST")
        frm.attr("action", "pageRows")
        frm.submit();
    });
});


// 여기부터 철희가 추가 12/19
// 헤더에 active class 추가
$("#tabBoard").addClass("active");
