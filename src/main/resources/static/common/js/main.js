// 이미지 화면에 보일 때 샤~악 나타나게 하기
let carouselElement = new IntersectionObserver((e) => {
    // .col 을(를) 찾으면 실행되는 부분
    e.forEach((element) => {
        // 화면에 보일때만 동작하게 하기
        // .isIntersecting은 요소가 화면에 보일 때만 true, 보이지 않을 때는 false
        if (element.isIntersecting) {
            element.target.style.opacity = 1; // 요소가 화면에 보일 때 opacity을(를) 1(으)로 바꿔서 보이게 한다.
        } else {
            element.target.style.opacity = 0; // 요소가 화면에 보이지 않을 때 opacity을(를) 0(으)로 바꿔서 보이지 않게 한다.
        }
    })
})

// 요소를 선택해서 변수에 할당
let caroucelElments = document.querySelectorAll("#carouselExampleCaptions, #cardTab, #mbtiPart")

// 변수의 요소만큼 반복해서 observe() 호출
for (let i = 0; i < caroucelElments.length; i++) {
    carouselElement.observe(caroucelElments[i])
}

// 추천카드 애니메이션 추가&제거
// 이미지 화면에 보일 때 샤~악 나타나게 하기
let cardElement = new IntersectionObserver((e) => {
    // .col 을(를) 찾으면 실행되는 부분
    e.forEach((element) => {
        // 화면에 보일때만 동작하게 하기
        // .isIntersecting은 요소가 화면에 보일 때만 true, 보이지 않을 때는 false
        if (element.isIntersecting) {
            element.target.style.animation = 'cardAnimation 1s ease-out';
            element.target.style.opacity = 1;
        } else if (!element.isIntersecting) {
            element.target.style.animation = 'cardAnimationHidden 1ms';
        } else {
            element.target.style.opacity = 0;
        }
    })
})

// 요소를 선택해서 변수에 할당
let cardElments = document.querySelectorAll(".card")

// 변수의 요소만큼 반복해서 observe() 호출
for (let i = 0; i < cardElments.length; i++) {
    cardElement.observe(cardElments[i])
}


// 메인의 행사파트를 스크롤에 위치에 따라 애니메이션 효과 주기
// 글자 부분을 애니메이션 효과
window.addEventListener('scroll', function () {
    var eventText = document.getElementById('eventText');

    let scrollY = window.scrollY;

    console.log("scrollY값:", scrollY)

    let elementTop = 1200;
    let elementBottom = 1900;

    if (scrollY <= elementTop) {
        eventText.style.animation = 'leftRightReverse 1s ease-out';
        eventText.style.opacity = 0;
    } else if (scrollY > elementTop && scrollY < elementBottom) {
        eventText.style.animation = 'leftRightSliding 1s ease-out';
        eventText.style.opacity = 1;
    } else if (scrollY >= elementBottom) {
        eventText.style.animation = 'leftRightReverse 1s ease-out';
        eventText.style.opacity = 0;
    }
});

// 포스터 부분을 애니메이션 효과
window.addEventListener('scroll', function () {
    var eventPoster = document.getElementById('eventPoster');

    let scrollY = window.scrollY;

    console.log("scrollY값:", scrollY)

    let elementTop = 1200;
    let elementBottom = 1900;

    if (scrollY <= elementTop) {
        eventPoster.style.animation = 'rightLeftReverse 1s ease-out';
        eventPoster.style.opacity = 0;
    } else if (scrollY > elementTop && scrollY < elementBottom) {
        eventPoster.style.animation = 'rightLeftSliding 1s ease-out';
        eventPoster.style.opacity = 1;
    } else if (scrollY >= elementBottom) {
        eventPoster.style.animation = 'rightLeftReverse 1s ease-out';
        eventPoster.style.opacity = 0;
    }
});
