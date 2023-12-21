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
    // });
});




$(document).ready(function () {
    //첫번째 배열(지우의 좌표에서 꺼낸 전화번호)
    const apiTel = [];
    $.ajax({
        url: "https://dapi.kakao.com/v2/local/search/category",
        type: "GET",
        headers: {
            Authorization: 'KakaoAK 61751b5d791d1ce89ad7cb69233f3ee0'
        },
        data: {
            sort: 'distance',
            category_group_code: 'FD6',
            x: $('#centralLongitude').val(),    //지우의 좌표
            y: $('#centralLatitude').val(),
            radius: 20000,
        },
        success: function (data, status){
            if(status == "success"){
                console.log("api" + data);
            }
        }, error: function (error){
            console.log(error);
        }
        // 배열에 전화번호를 추가
    })

    var storeTel = $(this).val();

    var data = {
        "store_tel" : storeTel,
    };
    $.ajax({
        url: "/tour/tourDetail/",
        type: "GET",
        cache: false,
        data: data,
        success: function(data, status){
            if(status == "success"){
                // if (data.includes(foodTel[i])){
                //
                // }
                console.log(data);
            }

        },
        error: function (request, status, error){
            console.log("code: " + request.status);
            console.log("message: " + request.responseText);
            console.log("error: " + error);
        }
    })

    // $.ajax({
    //     url: "/tour/foodTel",
    //     type: "GET",
    //     cache: false,
    // })

})



        // success: function (response) {
        //     response.documents.forEach(function (place) {
        //         console.log("플레이스 사이즈 = " + place.size);
        //         if (place.phone.length > 2) {
        //             const locationInfo = {
        //                 phone: place.phone
        //             };
        //             apiTel.push(locationInfo);
        //         }
        //     });
        //     console.log(apiTel); // 전화번호 배열 확인!
        //
        //     //두번째 배열(내 db에 있는 전화번호 배열)
        //     const foodTel = []; //new 배열
        //     //store_tel의 값
        //     $('.store_tel').each(function () {
        //         foodTel.push($(this).val());   //현재의 값을 배열에 추가
        //     })
        //     console.log("!!" + foodTel);
        //     let count = 0;
        //
        //     for (let i = 0; i < apiTel.length; i++) {
        //         if (apiTel.includes(foodTel[i])) {
        //             count++;
        //             if (count == 3) {
        //                 break;
        //             }
        //         }
        //         console.log("?" + foodTel);
        //     }
        //     console.log("??" + foodTel);
        // },
        // error: function (error) {
        //     console.log(error);
        // }
//     })
// })


//음식점
// $(document).ready(function () {
//     const foodId = $('#foodId').val();
//     const data = {
//         "food_id": foodId,
//     }
//     $.ajax({
//         url: "/tour/tourDetail",
//         type: "POST",
//         data: data,
//         cache: false,
//         success: function (data, status) {
//             if (status == "success") {
//                 if (data.status == "OK") {
//                     alert(data.status);
//                     return;
//                 }
//             }
//         },
//         error: function (error) {
//             console.log(error);
//             alert("error");
//         }
//     });
// })

