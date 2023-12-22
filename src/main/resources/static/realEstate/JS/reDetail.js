$(document).ready(function (){
    const houseId = $("#houseId").val();
    const data = {
        "houseId" : houseId
    }
    $.ajax({
        url: "/scores/avgs",
        type: "GET",
        data: data,
        cache: false,
        success: function(data, status) {
            if(status == "success"){
                $("#avgCscore").text(data[0])
                $("#avgPscore").text(data[1])
            }
        },
    })
})