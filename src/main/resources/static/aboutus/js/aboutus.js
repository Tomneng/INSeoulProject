const frm1 = document.forms['frm1'];
const frm2 = document.forms['frm2'];
const frm3 = document.forms['frm3'];

let btns = document.querySelectorAll('input[type=button][name="option"]');
btns.forEach(btn => btn.addEventListener('click', () => {
        if (btn.value == "소개") {
            frm1.style.display = 'block';
            frm2.style.display = 'none';
            frm3.style.display = 'none';
        }
        if (btn.value == "FAQ") {
            frm1.style.display = 'none';
            frm2.style.display = 'block';
            frm3.style.display = 'none';
        }
        if (btn.value == "Contact Us") {
            frm1.style.display = 'none';
            frm2.style.display = 'none';
            frm3.style.display = 'block';
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


// 여기부터 철희가 추가 12/18
// 헤더에 active class 추가
$("#tabService").addClass("active");

