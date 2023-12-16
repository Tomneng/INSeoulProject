package com.inseoul.mail.service;

import com.inseoul.mail.domain.AuthCodeQryResult;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {

    //의존성 주입을 통해서 필요한 객체를 가져온다.(gradle에 있는거임)
    private final JavaMailSender emailSender;
    // 타임리프를사용하기 위한 객체를 의존성 주입으로 가져온다 (gradle에 있는거임)
    private final SpringTemplateEngine templateEngine;
    private String authNum; //랜덤 인증 코드

    /*
     * 랜덤 코드 생성기
     * 0일때 : 숫자와 알파벳 대소문자 중에 1개
     * 1일때 : 숫자와 알파벳 대문자 중에 1개
     * 2일때 : 숫자만
     */
    public void createCode() {
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(3);

            switch (index) {
                case 0:
                    key.append((char) ((int) random.nextInt(26) + 97));
                    break;
                case 1:
                    key.append((char) ((int) random.nextInt(26) + 65));
                    break;
                case 2:
                    key.append(random.nextInt(9));
                    break;
            }
        }
        authNum = key.toString();
    }

    /**
     * 이 메소드는 매개변수로 보낼 이메일 주소를 받아서 실제로 내용을 담을 메세지 객체를 생성하고,
     * message에 보내는 주소와 받는 주소, 제목을 서술해서 만들어 준다.
     * createMimeMessage()로 객체 생성, 참고로 이 객체생성은 JavaMailSender에서 생성해주는 것.
     * 그걸 MimeMessage 타입 변수에 담아서 리턴해줄 뿐
     *
     * @MimeMessage MimePart 를 구현하고 Message를 상속받은 것인데 그냥 쉽게 말해 ASCII를 제외한 모든 다른 정보를 보내기 위해
     * 만들어진 message 객체라고 보면된다(이게 MimeType)
     * @MimeType ASCII 문자를 제외한 다른 정보(예: 사진이나 한국어 등등) 를 전자우편으로 보내기 위해 만들어진 타입.
     * type 과 subtype으로 나누어져 있다. Mime typing system : type/subtype => text/javascript, application/pdf 이런식으로 표현함
     * @param email 받는 사람 이메일 주소
     * @return MimeMessage
     * @throws MessagingException Message class에서 가장 기본적인 Exception
     * @throws UnsupportedEncodingException encoding 실패시 발생하는 에러
     */
    public MimeMessage createEmailForm(String email) throws MessagingException, UnsupportedEncodingException {

        createCode(); //인증 코드 생성
        String setFrom = "kchanghee59@gmail.com"; //email-config에 설정한 자신의 이메일 주소(보내는 사람)
        String toEmail = email; //받는 사람
        String title = "CODEBOX 회원가입 인증 번호"; //제목

        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email); // 받는 사람 주소 지정
        message.setSubject(title); //제목 설정
        message.setFrom(setFrom); // 보내는 사람 주소 입력
        // 위에서 서술했듯이, setContext(authNum)은 String 이며 text이다. 결론적으로 아래는 text/html 인 Mimetype 메세지
        message.setText(setContext(authNum), "utf-8", "html");

        return message;
    }

    /**
     * 매개변수로 보낼 이메일 주소를 받아오면, 그걸 바탕으로 createEmailFrom()메소드에 넘겨줘서 Mime type 메세지를 생성하고
     * JavaMailSender의 send()메소드로 이메일을 보내준다.
     * @param toEmail
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public AuthCodeQryResult sendEmail(String toEmail) throws MessagingException, UnsupportedEncodingException {

        //메일전송에 필요한 정보 설정
        MimeMessage emailForm = createEmailForm(toEmail);
        //실제 메일 전송 참고로 send()메소드에 담긴 매개변수는 createMimeMessage()로 만들어진 MimeMessage타입이여만 함 (당연히 둘은 같은 클래스에있기 때문일듯?)
        emailSender.send(emailForm);
        AuthCodeQryResult authCodeQryResult = AuthCodeQryResult.builder()
                .authCode(authNum)
                .status("OK")
                .build();
        return authCodeQryResult; //인증 코드 및 status 반환
    }

    /** setContext로 내가 보내고 싶은 내용을 html 파일에 담아서 실행을 시키고 실행결과를 리턴받음
     * 이 리턴값이 MimeType에 type부분에 text로 들어가는거임
     * @Context templateEngine을 돌리기 위한 값(context variables)과 Locale 들을 담은 것
     * @Locale 특정 국가에 대한 언어, 출력방식, 지역설정들을 담고있는 문자열(한마디로 지역정보같은거임)
     * @param code 이 code가 실제로 context에 담기는 보내고 싶은 내용임
     * @return String
     */
    //타임리프를 이용한 context 설정
    public String setContext(String code) {
        Context context = new Context();
        context.setVariable("code", code);
        return templateEngine.process("mail/mail.html", context); //mail.html, process는 템플릿을 실행 후 실행결과를 리턴
    }

}