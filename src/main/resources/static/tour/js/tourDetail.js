var api_key = "J8PrTBaAdSOCeLnyBjw9bmy%2FfwolJYN7gzspJ4V1lDdkcXkn91IE%2FY2OZrFhewei%2FiwdJBssG%2FLCMGhnoRMnSA%3D%3D";

$(document).ready(function () {
//    $("#tourImage1").click(function(){
    var tourContentid = $("#tourContentid").val();
    var url = `http://apis.data.go.kr/B551011/KorService1/detailCommon1?serviceKey=${api_key}&MobileOS=ETC&MobileApp=Inseoul&contentId=${tourContentid}&contentTypeId=12&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&_type=json`;
    console.log(url);
    $.ajax({
        url: url,
        type: "GET",
        success: function (data, status) {
            if (status === "success") {
                parseJSON(data);
            }
        },
        error: function (xhr, status, error) {
            console.log(error);
        }
    });

    function parseJSON(jsonObj) {
        const table = [];
        for (item of jsonObj.response.body.items.item) {
            table.push(`
                    <tr>
                        <td>${item.homepage}</td>
                    </tr>
                    <tr>
                        <td>${item.overview}</td>
                    </tr>
		        `);
        }
        $("#demoJSON_tour").html(table.join('\n'));
    }

    // });
});


// 여기부터 철희가 추가 12/18
// 헤더에 active class 추가
$("#tabTour").addClass("active");

























