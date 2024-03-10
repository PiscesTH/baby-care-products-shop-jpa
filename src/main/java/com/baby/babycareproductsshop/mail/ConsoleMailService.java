package com.baby.babycareproductsshop.mail;

import com.baby.babycareproductsshop.mail.model.EmailMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsoleMailService implements MailSender {
    @Override
    public void send(EmailMessageDto emailMessageDto) {
        log.info("email - to : {}, subject : {}, message : {}",
                emailMessageDto.getTo().toString(), emailMessageDto.getSubject(), emailMessageDto.getMessage());
    }
}
