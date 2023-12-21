// 주소를 좌표로 바꾸기
// 지도의 중심이 될 주소, 주소를 좌표로 변경할때 사용됨.
// 현재 임의의 값으로 코리아it아카데미 2관의 주소가 들어있음(서울 강남구 테헤란로26길 12)
let centralAddress = '';
centralAddress = $('.store_address').text();

// 서울특별시 강남구 테헤란로26길 12
console.log("주소:" + centralAddress);

// // 지도의 중심이 될 위도, 경도를 입력
// // 현재 임의의 값으로 들어가 있음(코리아it 2관)
let centralLatitude = 0; // 지도의 중심이 될 위도 37.4994465349977
let centralLongitude = 0; // 지도의 중심이 될 경도 127.035856382455
console.log("경도" + centralLatitude)
console.log("위도" + centralLongitude)

if (centralAddress) {
// 여기부터 '주소를 좌표로 변환하기' 시작
// 주소-좌표 변환 객체를 생성합니다
    const geocoder = new kakao.maps.services.Geocoder();

// 주소를 좌표로 변환하는 함수
    const addressSearch = address => {
        return new Promise((resolve, reject) => {
            geocoder.addressSearch(address, function (result, status) {
                if (status === kakao.maps.services.Status.OK) {
                    resolve(result);
                } else {
                    reject(status);
                }
            });
        });
    };

// async-await
    (async () => {
        try {
            const result = await addressSearch(centralAddress);
            centralLatitude = result[0].y; // 지도의 중심이 될 위도 37.4994465349977
            centralLongitude = result[0].x; // 지도의 중심이 될 경도 127.035856382455
            console.log(centralLatitude)
            console.log(centralLongitude)
            console.log("async try centralLatitude, centralLongitude = " + centralLatitude, centralLongitude);

            foodMap.setCenter(new kakao.maps.LatLng(centralLatitude, centralLongitude)); // 지도의 중심 표시하기

            setCentralMarker(result); // 중심마커 표시하기

            setCustomOverlay(result); // 중심마커 위에 커스텀오버레이 표시하기

            const data ={
                centralLatitude: "centralLatitude",
                centralLongitude : "centralLongitude"
            }

        } catch (e) {
            console.log(e);
        }
    })();
}