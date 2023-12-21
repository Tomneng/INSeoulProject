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
