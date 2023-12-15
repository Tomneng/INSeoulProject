// 공공데이터 서울시 문화행사 정보 api를 Jquery ajax로 json파일
const baseUrl = 'http://openapi.seoul.go.kr:8088'
const apiKey = '6b426777777368693630474b695441'
const fileType = 'json'
const serviceName = 'culturalEventInfo'
const startIndex = 1
const endIndex = 16
const codeName = '축제-문화/예술'
// CODENAME에는
// 문화교양/강좌,전시/미술,뮤지컬/오페라,기타,연극,무용,영화,국악,콘서트,축제-문화/예술,축제-전통/역사,축제-시민화합,클래식,축제-기타,축제-자연/경관,독주/독창회로 총16개 있습니다.
const apiUrl = `${baseUrl}/${apiKey}/${fileType}/${serviceName}/${startIndex}/${endIndex}/${codeName}`
console.log('apiUrl은(는) ' + apiUrl)

const posterContainer = document.getElementById('posterContainer'); // 포스터 이미지들을 넣을 부모 요소 선택
console.log('posterContainer는 ' + posterContainer)
fetch(apiUrl)
    .then(response => response.json())
    .then(jsonData => {
        var rowItems = jsonData.culturalEventInfo.row;
        // console.log('imgUrl = ' + rowItems)
        // console.log('imgUrl길이 = ' + rowItems.length)

        rowItems.forEach(rowItem => {
            // console.log('forEach rowItem은(는)' + rowItem)

            var cultureLink = rowItem.ORG_LINK;
            // console.log('cultureLink은(는) ' + cultureLink); // 링크 출력 확인.

            var cultureImg = rowItem.MAIN_IMG;
            // console.log('cultureImg은(는) ' + cultureImg); // 이미지 출력 확인.

            // 새로운 div 엘리먼트 생성
            var newDiv = document.createElement('div');
            newDiv.className = 'swiper-slide tranding-slide';
            // newDiv.className = 'swiper-slide tranding-slide swiper-slide-visible swiper-slide-active swiper-slide-duplicate-next swiper-slide-duplicate-prev';

            // div 내용 설정
            newDiv.innerHTML = `
          <div class="tranding-slide-img">
          <img src="${cultureImg}" alt="Tranding">
          </div>
          `;

            // 클릭 이벤트 추가
            newDiv.addEventListener('click', function () {
                window.open(cultureLink);
            });

            // posterContainer에 새로운 div 추가
            posterContainer.appendChild(newDiv);

        });

        // 슬라이더를 모양을 만들어주는 함수 호출
        makeTrandingSlider();

    });


// 슬라이더를 만들어 주는 함수
const makeTrandingSlider = () => {
    var TrandingSlider = new Swiper('.tranding-slider', {
        effect: 'coverflow',
        grabCursor: true,
        centeredSlides: true,
        loop: true,
        slidesPerView: 'auto',
        coverflowEffect: {
            rotate: 90,
            stretch: 0,
            depth: 200,
            modifier: 1.5,
        },
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
        },
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        // autoplay: {
        //   delay: 1500,
        //   disableOnInteraction: false,
        //   // pauseOnMouseEnter: true,
        // },
        // freeMode: {
        //   enabled: true,
        //   momentum: false,
        //   momentumBounce: true,
        //   momentumBounceRatio: 10,
        //   momentumRatio: 10,
        //   momentumVelocityRatio	: 10,
        //   sticky: true,
        // },

    });

};
