var api_key = 'C1kTT5SDomuASfikyoi68DoMygPZN35TX4oq5AsfuOvznPN2X3cciPhyU5HObfGBz7tyl83nmm%2Ba986uutA3yw%3D%3D'; //공공데이터 api
var req_url = "http://apis.data.go.kr/B551011/KorService1/searchKeyword1";

$(document).ready(function(){
    $("#btn_tour").click(function(){
        let sigunguCode = document.getElementById('sigunguCode')
        sigunguCode = sigunguCode.options[sigunguCode.selectedIndex].value;
        let keyword = encodeURIComponent(document.getElementsByName("keyword")[0].value.trim());

        // let url = `http://apis.data.go.kr/B551011/KorService1/searchKeyword1?serviceKey=${api_key}&MobileOS=ETC&MobileApp=Inseoul&keyword=${keyword}&areaCode=1&sigunguCode=${sigunguCode}&contentTypeId=12&numOfRows=12&_type=json`;
        let url = req_url + `?serviceKey=${api_key}&MobileOS=ETC&MobileApp=Inseoul&keyword=${keyword}&areaCode=1&sigunguCode=${sigunguCode}&contentTypeId=12&numOfRows=12&_type=json`;
        $.ajax({
            url: url,
            type: "GET",
            success: function(data, status){
                (status == "success") && parseJSON(data);
            },
        });
    });
});

function parseJSON(jsonObj) {
    const table = [];

    // 초기화
    let row = [];

    for(it of jsonObj.response.body.items.item){

        // firstimage url이 null 이 아닌 경우에만 출력
        if (it.firstimage) {

            let firstimage = `<img src="${it.firstimage}" alt="이미지" style="width:300px; height:400px;">`;
            row.push(`<td>${firstimage}</td>`);

            // 한 행이 완성되면 테이블에 추가하고 행 초기화
            if (row.length === 4) {
                table.push(`<tr>${row.join('')}</tr>`);
                row = [];
            }
        }
    }

    // 마지막 행이 남아있을 경우 추가
    if (row.length > 0) {
        table.push(`<tr>${row.join('')}</tr>`);
    }

    $("#demoJSON_tour").html(table.join('\n'));
}

$(document).ready(function () {
    // 이미지 클릭 이벤트
$('#demoJSON_tour').on('click', 'img', function () {
    var tourId = $(this).data('tourId');

    // 이미지 클릭하면 tourDetail 페이지로 이동하고 해당하는 tourId 번호가 url에 포함
    window.location.href = 'tourDetail.html?tourId=' + encodeURIComponent(tourId);
});

$("#btn_tour").click(function () {

$.ajax({
url: url,
type: "GET",
success: function (data, status) {
if (status == "success") {
parseJSON(data);
                }
            },
        });
    });
});

    function parseJSON(jsonObj) {
    const table = [];

    for (it of jsonObj.response.body.items.item) {
    if (it.firstimage) {
    let tourId = it.tourId;

    let firstimage = `<img src="${it.firstimage}" alt="이미지" style="width:300px; height:400px;" data-tourId="${tourId}">`;
    table.push(`<tr><td>${firstimage}</td></tr>`);
}
}

    $("#demoJSON_tour").html(table.join('\n'));
}