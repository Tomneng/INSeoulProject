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




// 리뷰 남기기 클릭시
function getShow(){
    alert("리뷰를 남겨요 😃");
    document.getElementById('content').style.display="";
    document.getElementById('showContent').style.display="none";
}

// 작성완료 클릭시
function getHide(){
    const foodId = $('input[type="hidden"]').val(); //음식점 아이디 값을 가져옴
    const reviewCategory = []
    $('input[type="checkbox"]:checked').each(function (){
        reviewCategory.push($(this).val());
    });
    const reviewContent = $('.reviewComment').val();
    const reviewStar = $('input[type="radio"]:checked').val();
    console.log("리뷰내용 = " + reviewContent)

    if(!reviewStar){
        alert('별점을 선택해주세요');
        return;
    }

    const data = {
        "food_id" : foodId,
        "review_star" : reviewStar,
        "review_category" : reviewCategory,
        "review_content" : reviewContent,
    };

    $.ajax({
        url: "/food/food_review/",
        type: "POST",
        data : data,
        cache : false,
        success : function (data, status){
            if (status == "success"){
                if (data.status !== "OK"){
                    alert(data.status);
                    return;
                }
                $('#reviewStar').val();
                // $('#reviewCategory').val();
                $('.checkComment').val();
                $('.reviewComment').val();
            }
                console.log(response);
                alert("성공");
        },
        error :function (error){
            console.log(error);
            alert("error");
        }
    });
                alert("리뷰를 남겼어요 😊");
                document.getElementById('content').style.display="none";
                document.getElementById('showContent').style.display="";
}
// 취소 클릭시
function getCancel(){
    alert("리뷰를 남기고 싶지 않아요 😓")
    document.getElementById('content').style.display="none";
    document.getElementById('showContent').style.display="";
}


// 이미지 업로드
function getImageFiles(e){
    const uploadFiles = [];
    const files = e.currentTarget.files;
    const imagePreview = document.querySelector('.image-preview');
    const docFrag = new DocumentFragment();
    // 이미지 파일 유효성검사
    [...files].forEach(file => {    //fileList 배열 변환
        if(!file.type.match("image/.*")){
           alert("이미지 파일만 업로드 가능합니다");
        return;
        }
    })
    console.log(typeof files, files);
}

// function createElement(e, file){
//     const li = document.createElement('li');
//     const img = document.createElement('img');
//     img.setAttribute('src', e.target.result);
//     img.setAttribute('data-file', file.name);
//     li.appendChild(img);
//     return li;
// }

//

// 이미지 파일 선택창
const realUpload = document.querySelector('.real-upload');
const upload = document.querySelector('.upload');

upload.addEventListener('click', e => realUpload.click());
realUpload.addEventListener('change', getImageFiles);



//평점
function clickStar(){

}

//리뷰 남기기
// const review = document.getElementById("reviewCategory")

// const cancelBtn = review.querySelector(".")
// cancelBtn.addEventListener("click", e=> {
//     alert("리뷰작성을 취소하시겠습니까?");
// });

// 지도
