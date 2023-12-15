$(document).ready(function () {
    selyear()
    selssg()
    $("#selectSSG").change(function (){
        let value = $(this).val().trim();
        selbjdong(value)
    })
    selHKind()
    $("[name='pageRows']").change(function (){
        let frm = $("[name='frmPageRows']");
        frm.attr("method", "Post")
        frm.attr("action", "pageRows")
        frm.submit();
    })
    const userId = {
        "user_id": logged_id,
    };
    $.ajax({
        url: "/house/scrapted",
        type: "GET",
        data: userId,
        cache: false,
        success: function(data, status) {
            if(status == "success"){
                const init = document.querySelector("#container1").firstElementChild
                const val = parseInt(init.firstElementChild.value);
                console.log(init)
                console.log(val)
                for (i = val; i<val+12; i++){
                    if (data.includes(i)){
                        document.getElementById(`${i}`).checked=true;
                    }
                }
            }
        },
    })


    $(".divider input").change(function(){
        const houseId = $(this).val();
        const data = {
            "house_id": houseId,
            "user_id": logged_id,
        };
        if ($(".divider input").is(":checked")){
        $.ajax({
            url: "/house/scrapt",
            type: "POST",
            data: data,
            cache: false,
            success: function(data, status) {
                if(status == "success"){
                    if(data.status !== "OK"){
                        alert(data.status);
                        return;
                    }
                }
            },
        });
        }
        else {
            $.ajax({
                url: "/house/scrapt",
                type: "POST",
                data: data,
                cache: false,
                success: function(data, status) {
                    if(status == "success"){
                        if(data.status !== "DELETED"){
                            alert(data.status);
                            return;
                        }
                    }
                },
            });
        }

    });



});

function repaging(){
}


function selyear() {
    for (k = 2023; k > 2017; k--) {
        if (k == 2023){
            $(`#selectYear`).append(`
                        <option th:value="${k}" selected>${k}</option>
            `)
        }else {
        $(`#selectYear`).append(`
                        <option th:value="${k}">${k}</option>
            `)
        }
    }
}


//강남구
const gangnamGu = ["논현동", "대치동", "역삼동", "압구정동", "일원동", "개포동", "삼성동", "수서동", "신사동", "세곡동", "자곡동", "도곡동", "청담동", "율현동"];
const gangnamGuCodes = [10800, 10600, 10100, 11000, 11400, 10300, 10500, 11500, 10700, 11100, 11200, 11800, 10400, 11300];

// 강동구
const gangdongGu = ["천호동", "고덕동", "성내동", "암사동", "상일동", "명일동", "길동", "강일동", "둔촌동"];
const gangdongGuCodes = [10900, 10200, 10800, 10700, 10300, 10100, 10500, 11000, 10600];

// 강북구
const gangbukGu = ["미아동", "수유동", "번동", "우이동"];
const gangbukGuCodes = [10100, 10300, 10200, 10400];

// 강서구
const gangseoGu = ["방화동", "공항동", "등촌동", "염창동", "내발산동", "화곡동", "마곡동", "가양동", "개화동", "외발산동"];
const gangseoGuCodes = [10900, 10800, 10200, 10100, 10600, 10300, 10500, 10400, 11000, 10700];

// 관악구
const gwanakGu = ["봉천동", "신림동", "남현동"];
const gwanakGuCodes = [10100, 10200, 10300];

// 광진구
const gwangjinGu = ["화양동", "중곡동", "자양동", "구의동", "군자동", "능동", "광장동"];
const gwangjinGuCodes = [10700, 10100, 10500, 10300, 10900, 10200, 10400];

// 구로구
const guroGu = ["구로동", "오류동", "개봉동", "가리봉동", "천왕동", "고척동", "신도림동", "온수동", "궁동", "항동"];
const guroGuCodes = [10200, 10800, 10700, 10300, 11100, 10600, 10100, 11000, 10900, 11200];

// 금천구
const geumcheonGu = ["가산동", "독산동", "시흥동"];
const geumcheonGuCodes = [10100, 10200, 10300];

// 노원구
const nowonGu = ["공릉동", "중계동", "하계동", "상계동", "월계동"];
const nowonGuCodes = [10300, 10600, 10400, 10500, 10200];

// 도봉구
const dobongGu = ["창동", "쌍문동", "방학동", "도봉동"];
const dobongGuCodes = [10700, 10500, 10600, 10800];

// 동대문구
const dongdaemunGu = ["이문동", "휘경동", "전농동", "용두동", "답십리동", "회기동", "장안동", "제기동", "청량리동", "신설동"];
const dongdaemunGuCodes = [11000, 10900, 10400, 10200, 10500, 10800, 10600, 10300, 10700, 10100];

// 동작구
const dongjakGu = ["노량진동", "상도1동", "상도동", "본동", "대방동", "사당동", "동작동", "신대방동", "흑석동"];
const dongjakGuCodes = [10100, 10300, 10200, 10400, 10800, 10700, 10600, 10900, 10500];

// 마포구
const mapoGu = [
    "성산동", "서교동", "노고산동", "공덕동", "상암동", "도화동", "염리동", "신공덕동", "아현동",
    "중동", "신수동", "합정동", "창전동", "연남동", "망원동", "용강동", "구수동", "상수동", "동교동",
    "당인동", "마포동", "현석동", "대흥동", "토정동", "하중동"
];
const mapoGuCodes = [
    12500, 12000, 11000, 10200, 12700, 10400, 10900, 10300, 10100,
    12600, 11100, 12200, 11400, 12400, 12300, 10500, 11300, 11500, 12100,
    11800, 10700, 11200, 10800, 10600, 11600
];

// 서대문구

const seodaemunGu = [
    "연희동", "대현동", "창천동", "북가좌동", "홍은동", "홍제동", "남가좌동", "북아현동", "천연동",
    "영천동", "현저동", "충정로3가", "합동", "대신동", "충정로2가", "냉천동", "미근동", "신촌동",
    "옥천동", "봉원동"
];
const seodaemunGuCodes = [
    11700, 11200, 11600, 11900, 11800, 11100, 12000, 11000, 10600,
    10800, 10900, 10200, 10300, 11300, 10100, 10500, 10400, 11400,
    10700, 11500
];

// 서초구
const seochoGu = [
    "서초동", "방배동", "잠원동", "양재동", "신원동", "반포동", "우면동", "내곡동", "염곡동", "원지동"
];
const seochoGuCodes = [
    10800, 10100, 10600, 10200, 11100, 10700, 10300, 10900, 11000, 10400
];

// 성동구
const seongdongGu = [
    "용답동", "홍익동", "행당동", "성수동2가", "마장동", "옥수동", "응봉동", "금호동3가", "송정동", "금호동1가",
    "사근동", "하왕십리동", "성수동1가", "금호동2가", "도선동", "금호동4가", "상왕십리동"
];
const seongdongGuCodes = [
    12200, 10300, 10700, 11500, 10500, 11300, 10800, 11100, 11800, 10900,
    10600, 10200, 11400, 11000, 10400, 11200, 10100
];

// 성북구
const seongbukGu = [
    "정릉동", "석관동", "하월곡동", "삼선동1가", "안암동5가", "돈암동", "안암동4가", "성북동1가", "종암동", "성북동",
    "삼선동3가", "동선동5가", "동소문동5가", "동선동3가", "동선동1가", "삼선동2가", "장위동", "보문동2가", "안암동2가",
    "안암동1가", "동선동2가", "삼선동5가", "안암동3가", "동소문동4가", "보문동6가", "보문동5가", "동소문동1가", "상월곡동",
    "길음동", "삼선동4가", "동소문동6가", "동소문동3가", "동선동4가", "보문동4가", "동소문동7가", "보문동1가", "보문동7가",
    "보문동3가", "동소문동2가"
];
const seongbukGuCodes = [
    13300, 13900, 13600, 11100, 12500, 10300, 12400, 10200, 13500, 10100,
    11300, 12000, 10800, 11800, 11600, 11200, 13800, 13100, 12200, 12100,
    11700, 11500, 12300, 10700, 12800, 12700, 10400, 13700, 13400, 11400,
    10900, 10600, 11900, 12600, 11000, 13000, 12900, 13200, 10500
];

// 송파구
const songpaGu = [
    "마천동", "풍납동", "잠실동", "가락동", "송파동", "거여동", "오금동", "방이동", "장지동", "삼전동",
    "문정동", "석촌동", "신천동"
];
const songpaGuCodes = [
    11400, 10300, 10100, 10700, 10400, 11300, 11200, 11100, 10900, 10600,
    10800, 10500, 10200
];

// 양천구
const yangcheonGu = [
    "신정동", "목동", "신월동"
];
const yangcheonGuCodes = [
    10100, 10200, 10300
];

// 영등포구
const yeongdeungpoGu = [
    "양평동2가", "도림동", "양평동3가", "당산동1가", "대림동", "신길동", "영등포동",
    "당산동", "당산동3가", "여의도동", "영등포동7가", "영등포동1가", "양평동4가", "영등포동5가",
    "영등포동2가", "당산동4가", "양평동1가", "당산동6가", "양평동6가", "당산동2가", "당산동5가",
    "영등포동8가", "문래동3가", "영등포동3가", "문래동6가", "영등포동6가", "영등포동4가", "양평동5가",
    "문래동5가", "문래동2가", "문래동4가"
];
const yeongdeungpoGuCodes = [
    12600, 11800, 12700, 11100, 13300, 13200, 10100, 11700, 11300, 11000, 10800,
    10200, 12800, 10600, 10300, 11400, 12500, 11600, 13000, 11200, 11500, 10900,
    12100, 10400, 12400, 10700, 10500, 12900, 12300, 12000, 12200
];

// 용산구
const yongsanGu = [
    "청파동3가", "청파동2가", "이촌동", "원효로1가", "청파동1가", "이태원동", "용산동2가", "원효로4가",
    "신창동", "문배동", "동자동", "효창동", "갈월동", "한남동", "한강로2가", "서빙고동", "신계동", "후암동",
    "용문동", "청암동", "원효로2가", "산천동", "보광동", "서계동", "한강로3가", "도원동", "주성동",
    "동빙고동", "한강로1가", "원효로3가", "남영동", "용산동5가", "용산동3가"
];
const yongsanGuCodes = [
    11100, 11000, 12900, 11200, 10900, 13000, 10200, 11800, 11400, 12200, 10700, 11900,
    10400, 13100, 12500, 13300, 12300, 10100, 12100, 11600, 11300, 11500, 13600, 10800,
    12800, 12000, 13400, 13200, 12400, 11700, 10500, 12700, 12600
];

// 은평구
const eunpyeongGu = [
    "역촌동", "응암동", "대조동", "녹번동", "구산동", "불광동", "진관동", "갈현동", "증산동", "수색동"
];
const eunpyeongGuCodes = [
    10800, 10700, 10600, 10200, 10500, 10300, 11400, 10400, 11000, 10100
];

// 종로구
const jongroGu = [
    "숭인동", "창신동", "동숭동", "명륜1가", "사직동", "명륜3가", "신영동", "평동", "원서동", "신교동",
    "연지동", "충신동", "옥인동", "누하동", "필운동", "혜화동", "무악동", "홍파동", "내수동", "효제동",
    "인의동", "평창동", "가회동", "연건동", "명륜2가", "행촌동", "체부동", "누상동", "구기동", "통인동",
    "교북동", "이화동", "통의동", "명륜4가", "부암동", "관수동", "신문로2가", "청운동", "원남동", "창성동",
    "경운동", "낙원동", "익선동", "삼청동", "계동", "홍지동", "신문로1가", "수송동", "운니동", "안국동",
    "화동", "당주동", "효자동", "관철동", "종로6가", "견지동", "종로1가", "권농동", "팔판동", "돈의동",
    "사간동", "재동", "종로5가"
];

const jongroGuCodes = [
    17500, 17400, 16800, 17000, 11500, 17300, 18600, 17700, 14900, 10200,
    16000, 16700, 11100, 11000, 11300, 16900, 18700, 17900, 11800, 16200,
    15700, 18300, 14600, 16600, 17100, 18100, 11200, 10900, 18200, 10800,
    18000, 16500, 10600, 17200, 18400, 15500, 12100, 10100, 15900, 10500,
    13400, 13700, 13300, 14000, 14800, 18500, 12000, 12400, 13200, 14100,
    14300, 11700, 10400, 13500, 16400, 12900, 12600, 13100, 13900, 15300,
    14400, 14700, 16300
];

// 중구
const jungGu = [
    "신당동", "중림동", "황학동", "장충동1가", "의주로1가", "흥인동", "충무로2가", "필동2가",
    "만리동2가", "장충동2가", "남대문로5가", "회현동1가", "필동3가", "무학동", "예장동", "쌍림동",
    "묵정동", "오장동", "충무로3가", "충무로5가", "회현동2가", "저동2가", "충무로4가", "정동", "광희동2가",
    "광희동1가", "남산동2가", "만리동1가", "순화동", "을지로5가", "북창동", "남산동1가", "남창동", "을지로4가",
    "인현동2가", "필동1가", "회현동3가", "산림동", "을지로6가", "남학동", "주교동"
];

const jungGuCodes = [
    16200, 17100, 16500, 14300, 16900, 16300, 12500, 13800, 17400, 14400, 11800,
    12100, 13900, 16400, 14200, 14700, 13600, 15400, 15800, 13300, 12200, 16100,
    13200, 16700, 14600, 14500, 12900, 17300, 16800, 15100, 11300, 12800, 11200,
    15000, 13400, 13700, 12300, 15700, 14800, 14000, 15200
];

// 중랑구
const jungnangGu = [
    "면목동", "신내동", "묵동", "망우동", "중화동", "상봉동"
];

const jungnangGuCodes = [
    10100, 10600, 10400, 10500, 10300, 10200
];

// 2차원 배열 노가다, 각 구마다 동이름:동코드 key-value형식으로 만듬
let mergedgangnamGu = Object.fromEntries(gangnamGu.map((key, index) => [key, gangnamGuCodes[index]])); // 강남구
let mergedgangdongGu = Object.fromEntries(gangdongGu.map((key, index) => [key, gangdongGuCodes[index]])); // 강동구
let mergedgangbukGu = Object.fromEntries(gangbukGu.map((key, index) => [key, gangbukGuCodes[index]])); // 강북구
let mergedgangseoGu = Object.fromEntries(gangseoGu.map((key, index) => [key, gangseoGuCodes[index]])); // 강서구
let mergedgwanakGu = Object.fromEntries(gwanakGu.map((key, index) => [key, gwanakGuCodes[index]])); // 관악구
let mergedgwangjinGu = Object.fromEntries(gwangjinGu.map((key, index) => [key, gwangjinGuCodes[index]])); // 광진구
let mergedguroGu = Object.fromEntries(guroGu.map((key, index) => [key, guroGuCodes[index]])); // 구로구
let mergedgeumcheonGu = Object.fromEntries(geumcheonGu.map((key, index) => [key, geumcheonGuCodes[index]])); // 금천구
let mergednowonGu = Object.fromEntries(nowonGu.map((key, index) => [key, nowonGuCodes[index]])); // 노원구
let mergeddobongGu = Object.fromEntries(dobongGu.map((key, index) => [key, dobongGuCodes[index]])); // 도봉구
let mergeddongdaemunGu = Object.fromEntries(dongdaemunGu.map((key, index) => [key, dongdaemunGuCodes[index]])); // 동대문구
let mergeddongjakGu = Object.fromEntries(dongjakGu.map((key, index) => [key, dongjakGuCodes[index]])); // 동작구
let mergedmapoGu = Object.fromEntries(mapoGu.map((key, index) => [key, mapoGuCodes[index]])); // 마포구
let mergedseodaemunGu = Object.fromEntries(seodaemunGu.map((key, index) => [key, seodaemunGuCodes[index]])); // 서대문구
let mergedseochoGu = Object.fromEntries(seochoGu.map((key, index) => [key, seochoGuCodes[index]])); // 서초구
let mergedseongdongGu = Object.fromEntries(seongdongGu.map((key, index) => [key, seongdongGuCodes[index]])); // 성동구
let mergedseongbukGu = Object.fromEntries(seongbukGu.map((key, index) => [key, seongbukGuCodes[index]])); // 성북구
let mergedsongpaGu = Object.fromEntries(songpaGu.map((key, index) => [key, songpaGuCodes[index]])); // 송파구
let mergedyangcheonGu = Object.fromEntries(yangcheonGu.map((key, index) => [key, yangcheonGuCodes[index]])); // 양천구
let mergedyeongdeungpoGu = Object.fromEntries(yeongdeungpoGu.map((key, index) => [key, yeongdeungpoGuCodes[index]])); // 영등포구
let mergedyongsanGu = Object.fromEntries(yongsanGu.map((key, index) => [key, yongsanGuCodes[index]])); // 용산구
let mergedeunpyeongGu = Object.fromEntries(eunpyeongGu.map((key, index) => [key, eunpyeongGuCodes[index]])); // 은평구
let mergedjongroGu = Object.fromEntries(jongroGu.map((key, index) => [key, jongroGuCodes[index]])); // 종로구
let mergedjungGu = Object.fromEntries(jungGu.map((key, index) => [key, jungGuCodes[index]])); // 중구
let mergedjungnangGu = Object.fromEntries(jungnangGu.map((key, index) => [key, jungnangGuCodes[index]])); // 중랑구



const ssg = [
    ["영등포구", mergedyeongdeungpoGu], ["광진구", mergedgwangjinGu], ["관악구", mergedgwanakGu], ["중구", mergedjungGu], ["용산구", mergedyongsanGu], ["송파구", mergedsongpaGu],
    ["강북구", mergedgangbukGu], ["동대문구", mergeddongdaemunGu], ["성북구", mergedseongbukGu], ["금천구", mergedgeumcheonGu], ["중랑구", mergedjungnangGu],
    ["강서구", mergedgangseoGu], ["구로구", mergedguroGu], ["성동구", mergedseongdongGu], ["동작구", mergeddongjakGu], ["서대문구", mergedseodaemunGu], ["강남구", mergedgangnamGu],
    ["노원구", mergednowonGu], ["종로구",  mergedjongroGu], ["마포구", mergedmapoGu], ["강동구", mergedgangdongGu], ["서초구", mergedseochoGu], ["은평구", mergedeunpyeongGu],
    ["양천구", mergedyangcheonGu], ["도봉구", mergeddobongGu]]

const housekind = ["아파트", "단독다가구", "연립다세대", "오피스텔"]

function selssg() {
    for (i = 0; i < 25; i++) {
            $(`#selectSSG`).append(`
               <option th:value=${ssg[i][0]}>${ssg[i][0]}</option>
           `)
    }
}

function selbjdong(value){
    $(`#selectBJDONG option`).remove()
    for (i = 0; i< ssg.length; i++){
        if (value == ssg[i][0]){
            for (j = 0; j<Object.keys(ssg[i][1]).length; j++)
            $(`#selectBJDONG`).append(`
                        <option value="${Object.values(ssg[i][1])[j]}">${Object.keys(ssg[i][1])[j]}</option>
            `)
        }
    }
}

function selHKind() {
    for (i = 0; i < 4; i++) {
            $(`#selectHouseKind`).append(`
                        <option th:value="${housekind[i]}">${housekind[i]}</option>
            `)
    }
}


