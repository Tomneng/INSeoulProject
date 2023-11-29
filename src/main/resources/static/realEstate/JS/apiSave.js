$(document).ready(function(){
    var url = `http://openapi.seoul.go.kr:8088/5146444173746f6d32346f53767a56/json/tbLnOpendataRentV/1/12/2023/11170`;

    $.ajax({
        url: url,
        type: "GET",
        success : function(data, status){
            (status == "success") && parseJSONran1(data);
        }
    })

})


function parseJSONran1(jsonObj) {

    for(i = 0; i< 12; i++){

        $(`#card${i+1}`).html(`
        ${jsonObj.tbLnOpendataRentV.row[i].RENT_GBN}
            `);
    }
}