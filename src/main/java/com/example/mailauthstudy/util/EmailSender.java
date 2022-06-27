package com.example.mailauthstudy.util;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.SendEmailResult;
import com.example.mailauthstudy.dto.EmailSenderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailSender {

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    public void send(EmailSenderDto senderDto){

        if(senderDto.getTo().size() == 0) {
            log.error("메일을 전송할 대상이 없습니다: [{}]", senderDto.getSubject());
            return;
        }

        SendEmailResult sendEmailResult = amazonSimpleEmailService.sendEmail(senderDto.toSendRequestDto());

        if(sendEmailResult.getSdkHttpMetadata().getHttpStatusCode() == 200) {
            log.info("[AWS SES] 메일전송완료 => " + senderDto.getTo());
        }else {
            log.error("[AWS SES] 메일전송 중 에러가 발생했습니다: {}", sendEmailResult.getSdkResponseMetadata().toString());
            log.error("발송실패 대상자: " + senderDto.getTo() + " / subject: " + senderDto.getSubject());
        }
    }
}