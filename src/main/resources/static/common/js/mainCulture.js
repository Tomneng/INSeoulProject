// 공공데이터 서울시 문화행사 정보 api를 Jquery ajax로 xml파일




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


