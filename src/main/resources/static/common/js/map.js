// 주소를 좌표로 바꾸기
// 지도의 중심이 될 주소, 주소를 좌표로 변경할때 사용됨.
// 현재 임의의 값으로 코리아it아카데미 2관의 주소가 들어있음(서울 강남구 테헤란로26길 12)
let centralAddress = '';
centralAddress = $('.store_address').text()

// 서울특별시 강남구 테헤란로26길 12
console.log("주소:" + centralAddress);



// 지도의 중심이 될 위도, 경도를 입력
// 현재 임의의 값으로 들어가 있음(코리아it 2관)
let centralLatitude = 0; // 지도의 중심이 될 위도 37.4994465349977
let centralLongitude = 0; // 지도의 중심이 될 경도 127.035856382455


// 지도 중심에 표시되는 마커의 정보들
var centerMarkerName = '장소명 data를 넣으세요',
    centerMarkerImg = 'https://firebasestorage.googleapis.com/v0/b/inseoul-bcec2.appspot.com/o/free-icon-heart-4551298.png?alt=media&token=f5c167ae-87ab-4401-863b-7c974a859b06', // 장소 이미지 url를 넣으세요.
    centerMarkerJuso = '주소 data를 넣으세요.', // 예) 제주특별자치도 제주시 첨단로 242
    centerMarkerJibun = '상세주소(지번) data를 넣으세요', // 예) (우) 63309 (지번) 영평동 2181
    centerMarkerWeb = '홈페이지link를 넣으세요.'; // 예) https://www.kakaocorp.com/main


// 지도 표시하기
// 최초로 map을 표시할 컨테이너와 맵의 옵션을 설정합니다.
// 맵의 옵션에서 지도의 센터 좌표와 지도의 확대 수준을 설정합니다.
var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(centralLatitude, centralLongitude), // 지도의 중심좌표(위도, 경도)
        level: 3, // 지도의 확대 레벨
    };
// console.log("mapContainer = " + mapContainer)
// console.log("mapOption = " + mapOption)


// 지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);
console.log("(좌표만 있을때)지도 표시하기 = " + centralLatitude, centralLongitude);
// 지도 표시하기 끝


// 여기서부터 '카테고리별 장소 검색하기' 시작
// 마커를 클릭했을 때 해당 장소의 상세정보를 보여줄 커스텀오버레이입니다
var placeOverlay = new kakao.maps.CustomOverlay({zIndex: 1}),
    contentNode = document.createElement('div'), // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다
    markers = [], // 마커를 담을 배열입니다
    currCategory = ''; // 현재 선택된 카테고리를 가지고 있을 변수입니다

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places(map);

// 지도에 idle 이벤트를 등록합니다
// kakao.maps.event.addListener(map, 'idle', searchPlaces);

// 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다
contentNode.className = 'placeinfo_wrap';

// 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
// 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다
addEventHandle(contentNode, 'mousedown', kakao.maps.event.preventMap);
addEventHandle(contentNode, 'touchstart', kakao.maps.event.preventMap);

// 커스텀 오버레이 컨텐츠를 설정합니다
placeOverlay.setContent(contentNode);

// 각 카테고리에 클릭 이벤트를 등록합니다
// addCategoryClickEvent();

// 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
function addEventHandle(target, type, callback) {
    if (target.addEventListener) {
        target.addEventListener(type, callback);
    } else {
        target.attachEvent('on' + type, callback);
    }
}

// 카테고리 검색을 요청하는 함수입니다
// function searchPlaces() {
//     if (!currCategory) {
//         return;
//     }
//
//     // 커스텀 오버레이를 숨깁니다
//     placeOverlay.setMap(null);
//
//     // 지도에 표시되고 있는 마커를 제거합니다
//     removeMarker();
//
//     ps.categorySearch(currCategory, placesSearchCB, {useMapBounds: true});
// }

// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {

        // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
        displayPlaces(data);
    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요

    } else if (status === kakao.maps.services.Status.ERROR) {
        // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요

    }
}

// 지도에 선택한 카테고리의 마커를 표출하는 함수입니다
function displayPlaces(places) {

    // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
    // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
    var order = document.getElementById(currCategory).getAttribute('data-order');


    for (var i = 0; i < places.length; i++) {

        // 마커를 생성하고 지도에 표시합니다
        var marker = addMarker(new kakao.maps.LatLng(places[i].y, places[i].x), order);

        // 마커와 검색결과 항목을 클릭 했을 때
        // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
        (function (marker, place) {
            kakao.maps.event.addListener(marker, 'click', function () {
                displayPlaceInfo(place);
            });
        })(marker, places[i]);
    }
}

// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, order) {
    var imageSrc = "https://firebasestorage.googleapis.com/v0/b/inseoul-bcec2.appspot.com/o/free-icon-restaurant-4551357.png?alt=media&token=df77b245-abab-4110-a756-db6b88915b96", // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(30, 30),  // 마커 이미지의 크기
        imgOptions = {
            spriteSize: new kakao.maps.Size(30, 30), // 스프라이트 이미지의 크기
            spriteOrigin: new kakao.maps.Point(0, 0), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new kakao.maps.Point(14.4, 30) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        }

    // console.log("현재 order = " + order)
    switch (order) {
        case "1":
            // console.log("스위치 케이스 1 수행")
            imageSrc = "https://firebasestorage.googleapis.com/v0/b/inseoul-bcec2.appspot.com/o/free-icon-restaurant-4551357.png?alt=media&token=df77b245-abab-4110-a756-db6b88915b96";
            break;
        case "2":
            // console.log("스위치 케이스 2 수행")
            imageSrc = "https://firebasestorage.googleapis.com/v0/b/inseoul-bcec2.appspot.com/o/free-icon-coffee-4551276.png?alt=media&token=671f27b2-5975-467a-b9a0-2ed6e57b2e9b";
            break;
        case "3":
            // console.log("스위치 케이스 3 수행")
            imageSrc = "https://firebasestorage.googleapis.com/v0/b/inseoul-bcec2.appspot.com/o/free-icon-camera-4551261.png?alt=media&token=8e23ef9e-43af-4532-9a03-00d1dc97e0ec";
            break;
    }
    // console.log("현재 이미지url = " + imageSrc);

    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
        marker = new kakao.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage
        });


    marker.setMap(map); // 지도 위에 마커를 표출합니다
    markers.push(marker);  // 배열에 생성된 마커를 추가합니다

    return marker;
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
    markers = [];
}

// 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
// function displayPlaceInfo(place) {
//     var content =
//         '<div class="placeinfo">' +
//         '   <div class="placeinfoTitle">' +
//         '       <a class="title" href="' + place.place_url + '" target="_blank" title="' + place.place_name + '">' + place.place_name + '</a>' +
//         '   </div>';
//
//     if (place.road_address_name) {
//         content +=
//             '<span title="' + place.road_address_name + '">' + place.road_address_name + '</span>' +
//             '<span class="jibun" title="' + place.address_name + '">(지번 : ' + place.address_name + ')</span>';
//     } else {
//         content +=
//             '<span title="' + place.address_name + '">' + place.address_name + '</span>';
//     }
//
//     content +=
//         '<span class="tel">' + place.phone + '</span>' +
//         '</div>' +
//         '<div class="after"></div>';
//
//     contentNode.innerHTML = content;
//     placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
//     placeOverlay.setMap(map);
// }



// 각 카테고리에 클릭 이벤트를 등록합니다
// function addCategoryClickEvent() {
//     var category = document.getElementById('category'),
//         children = category.children;
//
//     for (var i = 0; i < children.length; i++) {
//         children[i].onclick = onClickCategory;
//     }
// }

// 카테고리를 클릭했을 때 호출되는 함수입니다
function onClickCategory() {
    var id = this.id,
        className = this.className;

    placeOverlay.setMap(null);

    if (className === 'on') {
        currCategory = '';
        changeCategoryClass();
        removeMarker();
    } else {
        currCategory = id;
        changeCategoryClass(this);
        searchPlaces();
    }
}

// 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
function changeCategoryClass(el) {
    var category = document.getElementById('category'),
        children = category.children,
        i;

    for (i = 0; i < children.length; i++) {
        children[i].className = '';
    }

    if (el) {
        el.className = 'on';
    }
}

// 여기까지 '카테고리별 장소 검색하기' 끝


// 여기부터 '닫기가 가능한 커스텀 오버레이' 시작
// 마커의 이미지 정보들
var imageSrc = 'https://firebasestorage.googleapis.com/v0/b/inseoul-bcec2.appspot.com/o/free-icon-heart-4551298.png?alt=media&token=f5c167ae-87ab-4401-863b-7c974a859b06', // 마커이미지의 주소입니다
    imageSize = new kakao.maps.Size(50, 50), // 마커이미지의 크기(가로 * 세로)입니다
    imageOption = {offset: new kakao.maps.Point(24.5, 50)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
// console.log("커스텀 오버레이 마커의 이미지 정보들 = " + imageSrc, imageSize, imageOption)

// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
    markerPosition = new kakao.maps.LatLng(centralLatitude, centralLongitude); // 마커가 표시될 위치입니다
// console.log("커스텀 오버레이 마커이미지 생성 = " + markerImage, markerPosition)


// 지도에 센터 좌표로 마커를 표시합니다
var marker = new kakao.maps.Marker({
    position: markerPosition,
    image: markerImage, // 마커의 이미지정보를 가지고 있는 변수로 이미지 설정
    zIndex: 3
});
console.log("(좌표만 있을때)지도에 센터 좌표로 마커 표시 = " + marker.getPosition())


// 커스텀 오버레이에 표시할 컨텐츠 입니다
// 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
// 별도의 이벤트 메소드를 제공하지 않습니다
// var content =
//     '<div class="wrap">' +
//     '    <div class="info">' +
//     '        <div class="title">' + centerMarkerName +
//     '            <div class="close" onclick="closeOverlay()" title="닫기"></div>' +
//     '        </div>' +
//     '        <div class="body">' +
//     '           <div class="img">' +
//     '                <img id src="' + centerMarkerImg + '" width="73" height="70">' +
//     '           </div>' +
//     '           <div class="desc">' +
//     '                <div class="ellipsis">' + centerMarkerJuso + '</div>' +
//     '                <div class="jibun ellipsis">' + centerMarkerJibun + '</div>' +
//     '                <div><a href="' + centerMarkerWeb + '" target="_blank" class="link">홈페이지</a></div>' +
//     '           </div>' +
//     '        </div>' +
//     '    </div>' +
//     '</div>';


// 마커 위에 커스텀오버레이를 표시합니다
// 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
// var overlay = new kakao.maps.CustomOverlay({
//     content: content,
//     position: marker.getPosition(),
//     zIndex: 3
// });

// marker.setMap(map); // 상세페이지 장소에 마커 표시
console.log("(좌표만 있을 때)상세페이지 장소에 마커 표시")
// overlay.setMap(map); // 상세페이지 장소에 커스텀 오버레이 표시
console.log("(좌표만 있을 때)상세페이지 장소에 커스텀 오버레이 표시")

// 커스텀 오버레이를 닫기 위해 호출되는 함수입니다
function closeOverlay() {
    overlay.setMap(null);
    console.log("(주소->좌표)closeOverlay() 실행")
}

// 여기까지 '닫기가 가능한 커스텀 오버레이' 끝


// 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
kakao.maps.event.addListener(marker, 'click', function () {
    console.log("(???)지도 중심 마커 클릭 이벤트 리스너 실행됨")
    overlay.setMap(map); // 중심마커 위에 커스텀오버레이 표시하기
});

const onClickCentralMarker = (result) => {
    // 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
    kakao.maps.event.addListener(marker, 'click', function () {
        console.log("(주소->좌표)지도 중심 마커 클릭 이벤트 리스너 실행됨")
        setCustomOverlay(result); // 중심마커 위에 커스텀오버레이 표시하기
    });
};

const setCentralMarker = result => {
    const coords = new kakao.maps.LatLng(result[0].y, result[0].x);
    console.log("(주소->좌표)중심마커를 표시하는 함수의 좌표값 = " + coords)

    marker = new kakao.maps.Marker({
        map: map,
        position: coords,
        image: markerImage, // 마커의 이미지정보를 가지고 있는 변수로 이미지 설정
        zIndex: 3
    });
    onClickCentralMarker(result);
}


// 주소를 좌표로 변환 후 그 좌표값으로 지도에 커스텀 오버레이를 표시하는 함수
const setCustomOverlay = result => {
    // console.log("result[0].y, result[0].x = " + result[0].y, result[0].x);

    const coords = new kakao.maps.LatLng(result[0].y, result[0].x);
    console.log("(주소->좌표)중심마커의 커스텀오버레이 표시하는 함수의 좌표값 = " + coords);

    // 마커 위에 커스텀오버레이를 표시합니다
    // 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
    overlay = new kakao.maps.CustomOverlay({
        content: content,
        map: map,
        position: coords,
        zIndex: 3
    });
};

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
            console.log("async try centralLatitude, centralLongitude = " + centralLatitude, centralLongitude);

            map.setCenter(new kakao.maps.LatLng(centralLatitude, centralLongitude)); // 지도의 중심 표시하기

            setCentralMarker(result); // 중심마커 표시하기

            setCustomOverlay(result); // 중심마커 위에 커스텀오버레이 표시하기

        } catch (e) {
            console.log(e);
        }
    })();
}

// 여기까지 '주소를 좌표로 변환하기' 끝

function panTo() {
    // 이동할 위도 경도 위치를 생성합니다
    var moveLatLon = new kakao.maps.LatLng(centralLatitude, centralLongitude);

    // 지도 중심을 부드럽게 이동시킵니다
    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
    map.panTo(moveLatLon);
}


