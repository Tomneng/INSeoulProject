$(function(){
    // [추가] 버튼 누르면 될 첨부파일 추가 가능
    var i = 0;
    $("#btnAdd").click(function(){
        $("#files").append(`
                <div class="input-group mb-2">
                    <input class="form-control col-xs-3" type="file" name="upfile${i}"/>
                    <button type="button" class="btn btn-outline-danger" onclick="$(this).parent().remove()">삭제</button>
                </div>`);
        i++;
    });

// [삭제] 버튼 누르면 삭제될 첨부파일 id 담기
    $("[data-fileid-del]").click(function(){
        let fileId = $(this).attr('data-fileid-del');
        deleteFiles(fileId);
        $(this).parent().remove();
    });
});

// 썸머노트
// $("#content").summernote({
//     height: 300,
//     // width: 1000,
//     // minHeight: null,
//     // focus: true,
//     // lang: "ko-KR",
//     // placeholder: '최대 2000자까지 쓸 수 있습니다',
//     // toolbar: [
//     //     ['style', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
//     //     ['insert', ['picture', 'link']],
//     //     ['para', ['ul', 'ol', 'paragraph']],
//     //     ['height', ['height']]
//     // ],
//     // fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', '맑은 고딕', '궁서', '굴림체', '굴림', '돋음체', '바탕체'],
//     // // 추가한 폰트사이즈
//     // fontSizes: ['8', '9', '10', '11', '12', '14', '16', '18', '20', '22', '24', '28', '30', '36', '50', '72'],
// });

function deleteFiles(fileId) {
    $("#delFiles").append(`<input type='hidden' name='delfile' value='${fileId}'>`);
}
