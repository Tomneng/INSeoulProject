var api_key = "J8PrTBaAdSOCeLnyBjw9bmy%2FfwolJYN7gzspJ4V1lDdkcXkn91IE%2FY2OZrFhewei%2FiwdJBssG%2FLCMGhnoRMnSA%3D%3D";
$(document).ready(function () {
//    $("#tourImage1").click(function(){
    var tourContentid = $("#tourContentid").val();
    var url = `http://apis.data.go.kr/B551011/KorService1/detailCommon1?serviceKey=${api_key}&MobileOS=ETC&MobileApp=Inseoul&contentId=${tourContentid}&contentTypeId=12&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&_type=json`;
    console.log(url);
    $.ajax({
        url: url,
        type: "GET",
        success: function (data, status) {
            if (status === "success") {
                parseJSON(data);
            }
        },
        error: function (xhr, status, error) {
            console.log(error);
        }
    });

    function parseJSON(jsonObj) {
        const table = [];
        for (item of jsonObj.response.body.items.item) {
            table.push(`
                    <tr>
                        <td>${item.homepage}</td>
                    </tr>
                    <tr>
                        <td>${item.overview}</td>
                    </tr>
		        `);
        }
        $("#demoJSON_tour").html(table.join('\n'));
    }

    //첫번째 배열(지우의 좌표에서 꺼낸 전화번호)
    let dataid = null;
    const apiTel = [];
    $.ajax({
        url: "https://dapi.kakao.com/v2/local/search/category",
        type: "GET",
        headers: {
            Authorization: 'KakaoAK 61751b5d791d1ce89ad7cb69233f3ee0'
        },
        data: {
            // sort: 'distance',   //거리순
            category_group_code: 'FD6', //음식점
            x: $('#centralLongitude').val(),    //지우의 좌표
            y: $('#centralLatitude').val(),
            radius: 20000,              //20키로
        },
        success: function (response) {
            response.documents.forEach(function (place) {
                console.log("플레이스 사이즈 = " + place.size);
                if (place.phone.length > 2) {
                    apiTel.push(place.phone);
                }
            });
            console.log(apiTel); // 전화번호 배열 확인!
            console.log(typeof apiTel);
            console.log(typeof apiTel[1]);

            var data = {
                "tel": apiTel
            }
            $.ajax({
                url: "/apisave/getThree",
                type: "POST",
                dataType: "json",
                data: data,
                cache: false,
                success: function (data, status) {
                    $("#foodImage1 input").val(data[0])
                    $("#foodImage2 input").val(data[1])
                    $("#foodImage3 input").val(data[2])
                    if (status == "success") {
                        $.ajax({
                            type: "POST",
                            url: "https://dapi.kakao.com/v2/search/image",
                            headers: {
                                Authorization: 'KakaoAK 61751b5d791d1ce89ad7cb69233f3ee0'
                            },
                            data: {
                                query: data[0],
                                sort: 'accuracy'
                            },
                        })
                            .done(function (res) {
                                $("div#foodImage1").append('<img style="width:380px; height:250px;" class="cardsee"  src="' + res.documents[1].thumbnail_url + '"/>');                              // $("div#foodImage").append('<img src="' + res.documents[0].thumbnail_url + '"/>');
                            })
                        $.ajax({
                            type: "POST",
                            url: "https://dapi.kakao.com/v2/search/image",
                            headers: {
                                Authorization: 'KakaoAK 61751b5d791d1ce89ad7cb69233f3ee0'
                            },
                            data: {
                                query: data[1],
                                sort: 'accuracy'
                            },
                        })
                            .done(function (res) {
                                $("div#foodImage2").append('<img style="width:380px; height:250px;" class="cardsee" src="' + res.documents[1].thumbnail_url + '"/>');
                            })
                        $.ajax({
                            type: "POST",
                            url: "https://dapi.kakao.com/v2/search/image",
                            headers: {
                                Authorization: 'KakaoAK 61751b5d791d1ce89ad7cb69233f3ee0'
                            },
                            data: {
                                query: data[2],
                                sort: 'accuracy'
                            },
                        })
                            .done(function (res) {
                                $("div#foodImage3").append('<img style="width:380px; height:250px;" class="cardsee" src="' + res.documents[1].thumbnail_url + '"/>');                              // $("div#foodImage").append('<img src="' + res.documents[0].thumbnail_url + '"/>');
                            })
                    }
                }
            });
        },
        error: function (error) {
            console.log(error);
        }
    })

    $(".gfi").off().on('click', function () {

        openBtn();
        const tel2 = $(this).find('input').val();
        console.log(tel2);
        const storetel = {
            "store_tel": tel2,
        }
        //전화번호로 음식점 아이디 갖고오기
        $.ajax({
            url: "/apisave/tourDetail3",
            type: "GET",
            data: storetel,
            cache: false,
            success: function (data, status) {
                $("#foodid").val(data);
                dataid = data
                const foodId = {
                    "food_id": data
                }
                //가져온 음식점 아이디로 정보 나타내기
                // $.ajax({
                //     url: "/apisave/tourDetail1",
                //     type: "POST",
                //     data: foodId,
                //     cache: false,
                //     success: function (data, status) {
                //         if (status == "success") {
                //             // $("#store_name").val(data.storeName)
                //             $("#store_name").text(data.POST_SJ)
                //             $("#store_tel").val(data.CMMN_TELNO)
                //             $("#store_tel").text(data.CMMN_TELNO)
                //             // $("#store_address").val(data.storeAddress)
                //             $("#store_address").text(data.ADDRESS)
                //         }

                tourDetail1(foodId)
                // 음식점 이미지 보여주기
                $.ajax({
                    type: "POST",
                    url: "https://dapi.kakao.com/v2/search/image",
                    headers: {
                        Authorization: 'KakaoAK 61751b5d791d1ce89ad7cb69233f3ee0'
                    },
                    data: {
                        // query: '02-3443-8834',
                        // query: '음식점 감촌',
                        query: tel2,
                        sort: 'accuracy'
                    },
                })
                    .done(function (res) {
                        console.log("모달푸드이미지소스 = " + res.documents[1].image_url)
                        $("#modalFoodImageTag").remove();
                        $("div#foodImage").append('<img id="modalFoodImageTag" referrerpolicy="no-referrer" src="' + res.documents[1].image_url + '" style="width:380px; height:250px;" />')
                    })

                const foodId2 = {
                    "food_id" : data
                }
                $.ajax({
                    url: "/apisave/tourDetail2",
                    type: "POST",
                    data: foodId2,
                    cache: false,
                    success: function (data, status) {
                        $('#reviewCategory').text(data)
                        console.log(data.reviewContent)
                    }, error: function (error) {
                        console.log(error);
                    }
                })
                $.ajax({
                    url: "/apisave/tourDetail4",
                    type: "POST",
                    data: foodId2,
                    cache: false,
                    success: function (data, status) {
                        $('#reviewStarOne').text(data)
                        console.log(data.reviewContent)
                    }, error: function (error) {
                        console.log(error);
                    }
                })
            }, error: function (error) {
                console.log(error);
            }

        })

    })




        $('#getShow').off().on('click',function () {
            const dataw = {
                "user_id": logged_id,
                "food_id": dataid
            };

            //리뷰 여부 확인하기
            $.ajax({
                url: "/food/foodReview",
                type: "POST",
                data: dataw,
                cache: false,
                success: function (data, status) {
                    if (status == "success") {
                        if (data > 0) {
                            if (confirm("기존 리뷰는 작성시 삭제됩니다") == true) {    //확인
                                document.getElementById('content').style.display = "";
                                document.getElementById('showContent').style.display = "none";
                            } else {
                                return;
                            } //취소
                        } else {
                            alert("리뷰를 남겨요 😃");
                            document.getElementById('content').style.display = "";
                            document.getElementById('showContent').style.display = "none";
                        }
                    }
                }
            })

            var reviewCategory = "";
            var reviewContent = "";
            var selectedRating = 0.0;
            //선택된 star의 값 가져오기
            $("#reviewStar").change(function () {
                // const selectedRating = $(this).val();
                selectedRating = $('input[name="reviewStar"]:checked').val();
                console.log("별" + selectedRating);
                const selectRat = $('#rating').val(selectedRating);
                console.log("??" + selectRat);
            })
            //리뷰 저장
            $('#ok').click(function () {
                // const reviewStar = $('#rating').val();
                reviewCategory = $('.checkComment:checked').val();  //리뷰 카테고리
                reviewContent = $('.reviewComment').val();    //글 내용

                const data2 = {
                    "food_id": dataid,
                    "user_id": logged_id,
                    "review_star": selectedRating,
                    "review_category": reviewCategory,
                    "review_content": reviewContent
                };
                if (!$('input[name="reviewStar"]:checked').val()) {
                    alert('별점을 선택해주세요');
                    return;
                }
                // 리뷰
                $.ajax({
                    url: "/food/food_review",
                    type: "POST",
                    data: data2,
                    cache: false,
                    success: function (data, status) {
                        // 별점은 NN로 필수 선택, 선택되지 않은 경우

                        alert("리뷰를 남겼어요 😊");
                        document.getElementById('content').style.display = "none";
                        document.getElementById('showContent').style.display = "";

                    },
                    // 서버와 통신중 에러가 발생한 경우
                    error: function (error) {
                        console.log(error);
                        alert("error");
                    }
                })
            })
            $('#cancel').click(function () {
                alert("리뷰를 남기고 싶지 않아요 😓")
                document.getElementById('content').style.display = "none";
                document.getElementById('showContent').style.display = "";
            })
        });
})

// 모달
function openBtn() {
    document.getElementById('modal').style.display = "";
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
    // removeModalFoodImageTag()
})

// function removeModalFoodImageTag() {
//     $('div#foodImage').remove();
// }


//
function tourDetail1(foodId){
    $.ajax({
        url: "/apisave/tourDetail1",
        type: "POST",
        data: foodId,
        cache: false,
        success: function (data, status) {
            if (status == "success") {
                // $("#store_name").val(data.storeName)
                $("#store_name").text(data.POST_SJ)
                $("#store_tel").val(data.CMMN_TELNO)
                $("#store_tel").text(data.CMMN_TELNO)
                // $("#store_address").val(data.storeAddress)
                $("#store_address").text(data.ADDRESS)
            }
        }
    })
}
// function modalImage(){
//     $.ajax({
//         type: "POST",
//         url: "https://dapi.kakao.com/v2/search/image",
//         headers: {
//             Authorization: 'KakaoAK 61751b5d791d1ce89ad7cb69233f3ee0'
//         },
//         data: {
//             // query: '02-3443-8834',
//             // query: '음식점 감촌',
//             query: $('#store_tel').val(),
//             sort: 'accuracy'
//         },
//     })
//         .done(function (res) {
//             console.log("모달푸드이미지소스 = " + res.documents[1].image_url)
//             $("#modalFoodImageTag").remove();
//             $("div#foodImage").append('<img id="modalFoodImageTag" referrerpolicy="no-referrer" src="' + res.documents[1].image_url + '" style="width:380px; height:250px;" />')
//         })
// }