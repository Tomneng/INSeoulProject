// 렌더링 되어있는 부동산 주소값을 변수에 할당.
var address = $('#address').text().trim();
console.log("여기에 부동산 주소가 찍힐까? " + address) // 주소 출력되는 것 확인함.


// 로드뷰 지도 표시 - 신철희 12/22 저녁
var centerRealEstate = address;

var roadviewContainer = document.getElementById('roadview'); //로드뷰를 표시할 div
var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

// 좌표로 지도의 위치 담기
var position = "";

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

function showLoadView() {
    // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
    roadviewClient.getNearestPanoId(position, 100, function (panoId) {
        roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
    });
}

// 주소로 좌표를 검색합니다
geocoder.addressSearch(centerRealEstate, function (result, status) {

    // 정상적으로 검색이 완료됐으면
    if (status === kakao.maps.services.Status.OK) {

        position = new kakao.maps.LatLng(result[0].y, result[0].x);

    } else {
        alert("주소를 좌표로 검색하는데 실패했습니다.");
    }
    showLoadView();
});

