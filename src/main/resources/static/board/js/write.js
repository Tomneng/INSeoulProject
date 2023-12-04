$(function(){
    // TODO
    // 검증코드등...

    // [추가] 버튼 누르면 첨부파일 추가
    var i = 0;
    $("#btnAdd").click(function(){
        $("#files").append(`
            <div class="input-group mb-2">
               <input class="form-control col-xs-3" type="file" name="upfile${i}"/>
               <button type="button" class="btn btn-outline-danger" onclick="$(this).parent().remove()">삭제</button>
            </div>
        `);
        i++;
    });
    $("#content").summernote({
        width: 800,
        height: 300,
        minHeight: null,
        focus: true,
        lang: "ko-KR",
        placeholder: '최대 2000자까지 쓸 수 있습니다',
        toolbar: [
            // // 글꼴 설정
            // ['fontname', ['fontname']],
            // // 글자 크기 설정
            // ['fontsize', ['fontsize']],
            // 굵기, 기울임꼴, 밑줄,취소 선, 서식지우기
            ['style', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
            // // 표만들기
            // ['table', ['table']],
            // 글머리 기호, 번호매기기, 문단정렬
            ['para', ['ul', 'ol', 'paragraph']],
            // 줄간격
            ['height', ['height']],
            // 코드보기, 확대해서보기, 도움말
            ['view', ['codeview', 'fullscreen', 'help']]
        ],
        fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', '맑은 고딕', '궁서', '굴림체', '굴림', '돋음체', '바탕체'],
        // 추가한 폰트사이즈
        fontSizes: ['8', '9', '10', '11', '12', '14', '16', '18', '20', '22', '24', '28', '30', '36', '50', '72'],
    });
});