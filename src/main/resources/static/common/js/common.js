$(document).ready(function () {


// 추천카드 상단 탭
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

// 카드 상단 탭
    let caroucelElments = document.querySelectorAll("#cardTab")

// 변수의 요소만큼 반복해서 observe() 호출
    for (let i = 0; i < caroucelElments.length; i++) {
        carouselElement.observe(caroucelElments[i])
    }

    function hideRealcard() {
        $('#cardPart2').css("display", "none");
    }

    hideRealcard()

    $('#clickReadMore').attr("href", "/tour/tourList");

    $('#clickTourTab').click(function () {
        $('#cardPart2').css("display", "none");
        $('#cardPart1').css("display", "");
        $('#clickReadMore').attr("href", "/tour/tourList");
    })

    $('#clickRealTab').click(function () {
        $('#cardPart1').css("display", "none");
        $('#cardPart2').css("display", "");
        $('#clickReadMore').attr("href", "/realEstate/infoList");
    })


})
