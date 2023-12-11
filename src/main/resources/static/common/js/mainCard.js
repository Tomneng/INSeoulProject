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
