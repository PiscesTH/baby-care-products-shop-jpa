package com.baby.babycareproductsshop.mail;

import com.baby.babycareproductsshop.exception.CommonErrorCode;
import com.baby.babycareproductsshop.exception.RestApiException;
import com.baby.babycareproductsshop.mail.model.EmailMessageDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Slf4j
@Service
@RequiredArgsConstructor
public class HtmlMailService implements MailSender {
    private final JavaMailSender javaMailSender;
    private final RedisTemplate<String, String> redisTemplate;

    @Value("${spring.mail.properties.mail.auth-code-expiration-millis}")
    private String expirationDuration;
    private final String AUTH_CODE_PREFIX = "AuthCode_";
//    @Value("${spring.mail.username}")
//    private String MY_EMAIL;

    @Override
    public void send(EmailMessageDto emailMessageDto) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setSubject(emailMessageDto.getSubject());
            mimeMessageHelper.setText(emailMessageDto.getMessage(), true);
//            mimeMessageHelper.setFrom(MY_EMAIL);
            for (String to : emailMessageDto.getTo()) {
                mimeMessageHelper.setTo(to);
                javaMailSender.send(mimeMessage);
            }
        } catch (MessagingException e) {
            log.error("email send was failed", e);
            throw new RestApiException(CommonErrorCode.TO_SEND_EMAIL_FAIL);
        }
    }

    public String createEmailAuthCode() {
        SecureRandom sr = new SecureRandom();
        return String.valueOf(sr.nextInt(100000, 999999));
    }

    public void sendAuthCode(String email) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        String authCode = createEmailAuthCode();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setSubject("나나빛 인증코드 메일입니다.");
            mimeMessageHelper.setText(authCode);
            mimeMessageHelper.setTo(email);
        } catch (MessagingException e) {
            log.error("email send was failed", e);
            throw new RestApiException(CommonErrorCode.TO_SEND_EMAIL_FAIL);
        }

    }
}
