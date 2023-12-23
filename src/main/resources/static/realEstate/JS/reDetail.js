$(document).ready(function (){

    const houseId = $("#houseId").val();
    const data = {
        "houseId" : houseId
    }
    const chart = []
    $.ajax({
        url: "/scores/avgs",
        type: "GET",
        data: data,
        cache: false,
        success: function(data, status) {
            if(status == "success"){
                chart.push(data[0])
                chart.push(data[1])
                chart.push(data[2])
                chart.push(data[3])
            }
            const ctx = document.getElementById('myChart');

            new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: ['현재부동산계약점수', '평균부동산계약점수', '현재부동산점수', '평균부동산점수'],
                    datasets: [{
                        label: '# of Votes',
                        data: chart,
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        },
    })

    $("#putScore").click(function (){
    const putscore = {
        "houseId" : houseId,
        "cScore" : $("#contract_ScorePut").val(),
        "pScore" : $("#place_ScorePut").val(),
        "userId" : logged_id
    }

    $.ajax({
        url: "/scores/putScore",
        type: "POST",
        data: putscore,
        cache: false,
        success: function(data, status) {
            if(status == "success"){
                alert("점수 작성 완료")
                $("#contract_ScorePut").val(null)
                $("#place_ScorePut").val(null)
            }
        },
    })
    })
})