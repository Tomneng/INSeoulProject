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


// 12/23일 추가
// jQuery를 사용하여 행 클릭 및 이동 이벤트 처리
$(document).ready(function () {
    $('.post-row').click(function () {
        // 데이터 속성에서 게시물 ID 가져오기
        let postId = $(this).data('post-id');

        // 상세 페이지를 위한 URL 생성
        let url = '/board/detail/' + postId;

        // 상세 페이지로 이동
        window.location.href = url;
    });
});
