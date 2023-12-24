//이미지api 불러오기
$(document).ready(function(){
    $.ajax({
        type:"POST",
        url:"https://dapi.kakao.com/v2/search/image",
        headers:{
            Authorization:'KakaoAK 61751b5d791d1ce89ad7cb69233f3ee0'
        },
        data:{
            // query: '02-3443-8834',
            // query: '음식점 감촌',
            query : $('.store_tel').text(),
            sort: 'accuracy'
        },
    })
    .done(function(res){
        $("div#foodImage").append('<img referrerpolicy="no-referrer" src="' + res.documents[1].image_url+'" style="width:380px; height:250px;" />');
        // $("div#foodImage").append('<img src="' + res.documents[0].thumbnail_url + '"/>');
    })
    const reviewId = $('input[name="reviewId"]').val();
    const userId  = $('input[name="userId"]').val(); //회원 아이디 값을 가져옴
    console.log('userId' + userId);
    $("#reviewStar").change(function (){
        // const selectedRating = $(this).val();
        const selectedRating = $('input[name="ratingStar"]:checked').val();
        // console.log("별" + selectedRating);
        const selectRat = $('#rating').val(selectedRating);
        // console.log(selectRat);
    })
})

// 모달
function openBtn(){
    document.getElementById('modal').style.display="";
}

const modal = document.getElementById("modal")
function modalOn() {
    modal.style.display = "flex"
}
function isModalOn() {
    return modal.style.display === "flex"
}
function modalOff() {
    modal.style.display = "none"
}

const closeBtn = modal.querySelector(".close-area")
closeBtn.addEventListener("click", e => {
    modalOff();
})

// 체크박스 하나만 선택
// function ckone(el){
//     const reviewCategory = []
//     // $('input[type="checkbox"]:checked').each(function (){
//     //     reviewCategory.push($(this).val());
//     // });
//     const ckboxes = document.querySelectorAll('checkComment:checked');
//     ckboxes.forEach((e) => {
//         e.checked = false;
//     })
//     el.checked = true;
// }

// 리뷰 남기기 클릭시
function getShow(){
    const userId  = $('input[name="userId"]').val(); //회원 아이디 값을 가져옴
    const data = {
        "user_id" : logged_id,
        "food_id": $("#foodid").val()
    };

    $.ajax({
        url: "/food/foodReview",
        type: "POST",
        data : data,
        cache : false,
        success : function (data, status){
        if (status == "success") {
            if (data > 0){
            if(confirm("기존 리뷰는 작성시 삭제됩니다") == true){    //확인
                document.getElementById('content').style.display="";
                document.getElementById('showContent').style.display="none";
            } else{ return; } //취소
            } else
            {
                alert("리뷰를 남겨요 😃");
                document.getElementById('content').style.display="";
                document.getElementById('showContent').style.display="none";
            }
        }
        }
    });
}
function getHide(){
    alert("리뷰를 남겼어요");
    document.getElementById('content').style.display="none";
    document.getElementById('showContent').style.display="";
}

// 취소 클릭시
function getCancel(){
    alert("리뷰를 남기고 싶지 않아요 😓")
    document.getElementById('content').style.display="none";
    document.getElementById('showContent').style.display="";
}
// 작성완료 클릭시
// function getHide(){
//     const foodId = $('input[name="foodId"]').val(); //음식점 아이디 값을 가져옴
//     // const reviewId = $('#reviewId').val();  //리뷰 아이디 값을 가져옴
//        //회원 아이디 값
//     // const reviewCategory = []
//     // $('input[type="checkbox"]:checked').each(function (){
//     //     reviewCategory.push($(this).val());
//     // });
//     const reviewCategory = $('input[type="checkbox"]:checked').val();
//     const reviewContent = $('.reviewComment').val();    //글 내용
//     const reviewStar = $('input[type="radio"]:checked').val();  //선택한 별점의 값
//     console.log("리뷰내용 = " + reviewContent);
//     console.log("키워드 = " + reviewCategory);
//     console.log("별점 값 = " + 7);
//     console.log("foodId = " + foodId);
//
//     // 별점은 NN로 필수 선택, 선택되지 않은 경우
//     if(!reviewStar){
//         alert('별점을 선택해주세요');
//         return;
//     }
//     // 넘길 변수
//     const data = {
//         "food_id" : foodId,
//         "user_id" : logged_id,
//         "review_star" : reviewStar,
//         "review_category" : reviewCategory,
//         "review_content" : reviewContent
//     };
//
//     // $.ajax({
//     //     url: "/food/food_review",
//     //     type: "POST",
//     //     data : data,
//     //     cache : false,
//     //     success : function (data, status){
//     //         // 서버와 성공적으로 통신한 경우
//     //         if (status == "success"){
//     //             if (data.status !== "OK"){
//     //                 alert(data.status);
//     //                 return;
//     //             }
//     //
//     //             // const selectedCategory = reviewCategory.length > 0 ? reviewCategory[0] : "";
//     //             $('#reviewStar').val();
//     //             //체크박스의 값 가져오기
//     //             // $('.reviewShowCategory').text(selectedCategory);
//     //             $('.category').val();
//     //             // 선택된 체크박스 를 해제
//     //             // $('.checkComment').prop('checked', false);
//     //             $('.reviewComment').val();
//     //
//     //
//     //         }
//     //         alert("리뷰를 남겼어요 😊");
//     //         document.getElementById('content').style.display="none";
//     //         document.getElementById('showContent').style.display="";
//     //     },
//     //     // 서버와 통신중 에러가 발생한 경우
//     //     error :function (error){
//     //         console.log(error);
//     //         alert("error");
//     //     }
//     // });
// }

// $(document).ready(function(){
//     const reviewCategory = $('input[type="checkbox"]:checked').val();
//     const data ={
//         "review_category" : reviewCategory,
//     }
//     $.ajax({
//         url:"/food/food_review/",
//         type:"GET",
//         data:data,
//         cache:false,
//         success:function (data, status){
//             if(status == "success"){
//                 if(data.status !== "OK"){
//                     alert(data.status);
//                     return;
//                 }
//             }
//         }
//     })
// })

// 이미지 업로드
// function getImageFiles(e){
//     const uploadFiles = [];
//     const files = e.currentTarget.files;
//     const imagePreview = document.querySelector('.image-preview');
//     const docFrag = new DocumentFragment();
//     // 이미지 파일 유효성검사
//     [...files].forEach(file => {    //fileList 배열 변환
//         if(!file.type.match("image/.*")){
//            alert("이미지 파일만 업로드 가능합니다");
//         return;
//         }
//     })
//     console.log(typeof files, files);
// }

// function createElement(e, file){
//     const li = document.createElement('li');
//     const img = document.createElement('img');
//     img.setAttribute('src', e.target.result);
//     img.setAttribute('data-file', file.name);
//     li.appendChild(img);
//     return li;
// }

// 이미지 파일 선택창
// const realUpload = document.querySelector('.real-upload');
// const upload = document.querySelector('.upload');
//
// upload.addEventListener('click', e => realUpload.click());
// realUpload.addEventListener('change', getImageFiles);

//스크랩
//카드 목록 리스트에서 보이는 목록(3) 만큼 for문
// $(document).ready(function(){
//     $.ajax({
//         url: "/food/scrapted",
//         type: "GET",
//         data: userId,
//         cache: false,
//         success: function (data, status) {
//             // 리스트-> 배열에 담아서 갯수만큼 for문
//             const those = [];
//             if (status == "success") {
//
//                 // for(let i = 0; i < document.getElementsByClassName())
//             }
//         },
//         error: function (request, status, error) {
//             console.log("code: " + request.status)
//             console.log("message: " + request.responseText)
//             console.log("error: " + error);
//         }
//     })
//     //
//     $("#scraptFood").change(function () {
//         const foodId = $('#foodid').val();
//         const data = {
//             "food_id": foodId,
//             "user_id": logged_id
//         };
//         if ($('input[type="checkbox"]:checked')) {
//             $.ajax({
//                 url: "/food/scrapt",
//                 type: "POST",
//                 data: data,
//                 cache: false,
//                 success: function (data, status) {
//                     if (status == "success") {
//                         if (data.status !== "OK") {
//                             alert(data.status);
//                             return;
//                         }
//                     }
//                 },
//             });
//         } else {
//             $.ajax({
//                 url: "/food/scrapt",
//                 type: "POST",
//                 data: data,
//                 cache: false,
//                 success: function (data, status) {
//                     if (status == "success") {
//                         if (data.status !== "DELETED") {
//                             alert(data.status);
//                             return;
//                         }
//                     }
//                 }
//             });
//         }
//     });
// });