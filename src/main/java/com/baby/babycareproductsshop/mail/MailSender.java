package com.baby.babycareproductsshop.mail;

import com.baby.babycareproductsshop.mail.model.EmailMessageDto;

public interface MailSender {
    void send(EmailMessageDto emailMessageDto);
}
