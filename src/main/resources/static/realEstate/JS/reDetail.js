// $(document).ready(function (){
//     $("#putScore").click(function (){
//         const scores = {
//             "contractScore" : $("#contract_ScorePut").val(),
//             "placeScore" : $("#place_ScorePut").val(),
//             "houseId" : $("#houseId").val(),
//             "userid" : logged_id
//         }
//         $.ajax({
//             url: "/realEstate/putScore",
//             type: "POST",
//             data: scores,
//             cache: false,
//             success: function(data, status) {
//                 if(status == "success"){
//                     console.log(data)
//                 }
//             }
//         })
//     })
// })