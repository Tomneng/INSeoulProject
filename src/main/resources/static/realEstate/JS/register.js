$(document).ready(function (){
    let value;
    $("#authCodeSend").click(function (event){
            const data = {
                "email" : $("#username").val()
            }
            $.ajax({
                url: "/authConfirm/authcode",
                type: "POST",
                data: data,
                cache: false,
                success: function(data, status) {
                    if(status == "success"){
                        value = data.authCode;
                        console.log(data);
                    }
                }
            })
    })

    $("#authorization").off("click").on("click", function (){
        if ($("#authcode").val() !== value){
            alert("인증 실패");
            document.getElementById("authcode").value = null;
            $("#authCodeSend").text("인증코드 재전송");
        }else {
            alert("인증 성공");
            $("#submitThis").removeAttr("type").attr("type", "submit");
        }
    })
    $("#submitThis").click(function (){
        if (document.getElementById("submitThis").getAttribute("type").toString()==="button"){
            alert("이메일 인증을 완료해주세요")
        }
    })
})