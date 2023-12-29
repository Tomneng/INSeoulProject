// 공공데이터 서울시 문화행사 정보 api를 Jquery ajax로 xml파일
const baseUrl = 'http://openapi.seoul.go.kr:8088'
const apiKey = '6b426777777368693630474b695441'
const fileType = 'json'
const serviceName = 'culturalEventInfo'
let startIndex = 1
let endIndex = 20
const apiUrl = `${baseUrl}/${apiKey}/${fileType}/${serviceName}/${startIndex}/${endIndex}/`
// console.log('apiUrl = ' + apiUrl)

fetch(apiUrl)
    .then(response => response.json())
    .then(jsonData => {
        const rowItems = jsonData['culturalEventInfo']['row'];
        // console.log('imgUrl = ' + rowItems)
        // console.log('imgUrl길이 = ' + rowItems.length)
        rowItems.forEach(rowItem => {
            const cultureLink = rowItem['ORG_LINK'];
            console.log('cultureLink = ' + cultureLink);
            const cultureImg = rowItem['MAIN_IMG'];
            console.log('cultureImg = ' + cultureImg);
        })
    })

// // xml parsing
// $.ajax({
//     url: apiUrl,
//     method: 'get',
//     success: function (response) {
//         console.log(response)
//         $(response).find('row').each(function () {
//             var strDOM = '';
//
//             strDOM += `<div class="posterImg">`;
//
//             $(this).find('ORG_LINK').each(function () {
//                 const cultureLink = $(this).text();
//                 // console.log('link = ' + cultureLink)
//                 strDOM += `<a href="${cultureLink}">`
//             });
//
//             $(this).find('MAIN_IMG').each(function () {
//                 const culturePosterUrl = $(this).text();
//                 // console.log('img = ' + culturePosterUrl)
//                 strDOM += `<img src="${culturePosterUrl}" alt="${culturePosterUrl}">`
//             });
//
//             strDOM += `</a>`
//             strDOM += `</div>`
//             console.log(strDOM);
//
//             var $imgSection = $('#eventSection');
//             $imgSection.append(strDOM);
//
//         })
//
//     },
//     error: function (error) {
//         console.log('에러메시지가 나올까? ' + error);
//     }
// })


// 메인의 행사파트를 스크롤에 위치에 따라 애니메이션 효과 주기
let elementTop = 1400;
let elementBottom = 1900;

// 글자 부분을 애니메이션 효과
// 포스터 부분을 애니메이션 효과
window.addEventListener('scroll', function () {
    var eventText = document.getElementById('eventText'); // 글자가 있는 요소 가져오기
    var eventPoster = document.getElementById('eventPoster'); // 포스터가 있는 요소 가져오기

    let scrollY = window.scrollY;

    console.log("scrollY값:", scrollY)

    if (scrollY <= elementTop) {
        eventText.style.animation = 'leftRightReverse 1s ease-out';
        eventText.style.opacity = 0;

        eventPoster.style.animation = 'rightLeftReverse 1s ease-out';
        eventPoster.style.opacity = 0;

    } else if (scrollY > elementTop && scrollY <= elementBottom) {
        eventText.style.animation = 'leftRightSliding 1s ease-out';
        eventText.style.opacity = 1;

        eventPoster.style.animation = 'rightLeftSliding 1s ease-out';
        eventPoster.style.opacity = 1;

    } else if (scrollY > elementBottom) {
        eventText.style.animation = 'leftRightReverse 1s ease-out';
        eventText.style.opacity = 0;

        eventPoster.style.animation = 'rightLeftReverse 1s ease-out';
        eventPoster.style.opacity = 0;

    }
});

