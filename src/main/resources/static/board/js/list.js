$(document).ready(function () {

    let selectedPageRow;

    $(function () {
        $("[name='pageRows']").change(function () {
            let frm = $("[name='frmPageRows']");
            frm.attr("method", "POST")
            frm.attr("action", "pageRows")
            frm.submit();
        });
    });

    let page = $("[name='page']").val(); // 페이지의 번호
    console.log('"[name=\'page\']").val() = ', page);

    $("[name='mbti']").change(function () {
        let selectedMbti = $("[name='mbti']").val();
        console.log('선택된 mbti = ', selectedMbti)

        let pageRows = $("[name='pageRows']").val();
        console.log('선택된 페이지행수 = ', pageRows);

        let frm = $("[name='frmPageRows']");
        frm.attr("method", "POST")
        frm.attr("action", "/board/pageRows")
        frm.submit();
    })

// 여기부터 철희가 추가 12/19
// 헤더에 active class 추가
    $("#tabBoard").addClass("active");


})
