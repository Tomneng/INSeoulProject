const frm1 = document.forms['frm1'];
const frm2 = document.forms['frm2'];
const frm3 = document.forms['frm3'];
console.log("frm1 = " + frm1)
console.log("frm2 = " + frm2)
console.log("frm3 = " + frm3)

let btns = document.querySelectorAll('input[type=button][name="option"]');
console.log("btns = " + btns)


btns.forEach(btn => btn.addEventListener('click', () => {
        console.log("btn = " + btn)
        if (btn.value == "소개") {
            frm1.style.display = 'block';
            frm2.style.display = 'none';
            frm3.style.display = 'none';
            console.log("소개 페이지 버튼 클릭이벤트 동작")
        }
        if (btn.value == "FAQ") {
            frm1.style.display = 'none';
            frm2.style.display = 'block';
            frm3.style.display = 'none';
            console.log("FAQ 페이지 버튼 클릭이벤트 동작")
        }
        if (btn.value == "Contact Us") {
            frm1.style.display = 'none';
            frm2.style.display = 'none';
            frm3.style.display = 'block';
            console.log("contact us 페이지 버튼 클릭이벤트 동작")
        }
    }
));

$(document).ready(function () {
    $(".faqlist a").click(function () {
        var faqcontent = $(this).next("ul");

        if (faqcontent.is(":visible")) {
            faqcontent.slideUp();
        } else {
            faqcontent.slideDown();
        }
    });
});

