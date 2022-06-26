package com.example.mailauthstudy.util;

import com.example.mailauthstudy.dto.EmailSenderDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailSenderTest {

    @Autowired
    private EmailSender emailSender;

    @Test
    void mailSendTest() throws Exception {
        // given
        ArrayList<String> to = new ArrayList<>();
        to.add("syhwang.mv@movements.kr");
        String subject = "AWS SES 메일 발송 테스트";
        String content = "메일 발송 테스트입니다";
        EmailSenderDto dto = EmailSenderDto.builder()
                .from("syhwang.mv@movements.kr")
                .to(to)
                .subject(subject)
                .content(content)
                .build();

        // when
        emailSender.send(dto);

        // then

    }

}