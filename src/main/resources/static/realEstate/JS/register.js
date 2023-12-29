$(document).ready(function (){

    $('.login-form .form-control').on('input', function () {
        // Check if the input has a value
        if ($(this).val().length > 0) {
            // If it has a value, add a class to the label for styling
            $(this).prev('label').addClass('has-value');
        } else {
            // If it doesn't have a value, remove the class
            $(this).prev('label').removeClass('has-value');
        }
    });

    // Trigger the input event initially for any pre-filled values
    $('.login-form .form-control').trigger('input');

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
                        return;
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
            // $("#submitThis").removeAttr("type").attr("type", "submit");
        }
    })
    // document.getElementById("submitThis").getAttribute("type").toString()==="button"
    $("#submitThis").click(function (){
        if ($("#authcode").val() !== value){
            alert("이메일 인증을 완료해주세요")
        }else {
        openModal()
        }
    })
    $("#skip").click(function (){
        let form = document.forms['submitForm'];
        for (i=0; i< document.getElementsByName("mbti").length; i++){
            document.getElementsByName("mbti")[i].value=null;
        }
        form.submit();
    })

    $("#done").click(function (){
        let form = document.forms['submitForm'];
        form.submit();
    })
})

function openModal() {
    document.getElementById('myModalWrapper').style.display = 'flex';
}



