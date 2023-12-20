$(function(){
    // 글 [삭제] 버튼
    $("#btnDel").click(function(){
        let answer = confirm("삭제하시겠습니까?");
        if(answer){
            $("form[name='frmDelete']").submit();
        }
    });

    // 현재 글의 id 값
    const postId = $("input[name='postId']").val().trim();

    // 현재 글의 댓글을 불러온다
    loadComment(postId);

    // 댓글 작성 버튼 누르면 댓글 등록 하기.
    // 1. 어느글에 대한 댓글인지? --> 위에 id 변수에 담겨있다
    // 2. 어느 사용자가 작성한 댓글인지? --> logged_id 값
    // 3. 댓글 내용은 무엇인지?  --> 아래 content


    $("#btn_comment").click(function(){
        // 입력한 댓글
        const content = $("#input_comment").val().trim();

        // 검증
        if(!content){
            alert("댓글 입력을 하세요");
            $("#input_comment").focus();
            return;
        }

        // submit 할 parameter 들 준비
        const data = {
            "post_id": postId,
            "user_id": logged_id,
            "content": content,
        };

        $.ajax({
            url: "/comment/write",
            type: "POST",
            data: data,
            cache: false,
            success: function(data, status) {
                if(status == "success"){
                    if(data.status !== "OK"){
                        alert(data.status);
                        return;
                    }
                    loadComment(id);  // 댓글 목록 다시 업데이트
                    $("#input_comment").val('');   // 입력칸 리셋
                }
            },
        });

    });

});

// 특정 글(post_id) 의 댓글 목록 읽어오기
function loadComment(postId){
    $.ajax({
        url: "/comment/list?postId=" + postId,
        type: "GET",
        cache: false,
        success: function(data, status){
            if(status == "success"){

                // 서버쪽에서 에러가 있는 경우.
                if(data.status !== "OK"){
                    alert(data.status);
                    return;
                }

                // 댓글 화면 렌더링
                buildComment(data);

                // 댓글 목록을 불러오고 난 뒤에 삭제에 대한 이벤트 리스너 등록해야 한다.
                addDelete();
            }
        },
    });
}

function buildComment(result) {
    $("#cmt_cnt").text(result.count);   // 댓글 총 개수
    console.log(result);

    const out = [];

    result.data.forEach(comment => {
        let id = comment.id;
        let content = comment.content;
        let regdate = comment.regdate;

        let user_id = comment.user.id;
        let username = comment.user.username;
        let name = comment.user.name;

        // 삭제버튼 여부: 작성자 본인인 경우만 삭제 버튼 보이게 하기
        const delBtn = (logged_id !== user_id) ? '' : `
            <i class="btn fa-solid fa-delete-left text-danger" data-bs-toggle="tooltip"
                            data-cmtdel-id="${id}" title="삭제"></i>
        `;

        const row =
            `
            <tr>
            <td><span><strong>${username}</strong><br><small class="text-secondary">(${name})</small></span></td>
            <td>
               <span>${content}</span>${delBtn}
            </td>
            <td><span><small class="text-secondary">${regdate}</small></span></td>
            </tr>
        `;
        out.push(row);
    });

    $("#cmt_list").html(out.join("\n"));
}

// 댓글 삭제 버튼이 눌렸을때.  해당 댓글 삭제하는 동작을 이벤트 핸들러로 등록
function addDelete(){
    // 현재 글의 id
    const id = $("input[name='id']").val().trim();

    $("[data-cmtdel-id]").click(function(){
        if(!confirm("댓글을 삭제하시겠습니까?")) return;

        // 삭제할 댓글의 comment_id
        const comment_id = $(this).attr("data-cmtdel-id");

        $.ajax({
            url: "/comment/delete",
            type: "POST",
            cache: false,
            data: {"id": comment_id},
            success: function(data, status){
                if(status == "success"){
                    if(data.status !== "OK"){
                        alert(data.status);
                        return;
                    }

                    // 삭제후에도 다시 목록을 불러와야 한다 (갱신)
                    loadComment(id);
                }
            },
        });
    });
}