package com.example.mailauthstudy.service;

import com.example.mailauthstudy.dto.EmailSenderDto;
import com.example.mailauthstudy.entity.EmailAuth;
import com.example.mailauthstudy.entity.User;
import com.example.mailauthstudy.repository.UserRepository;
import com.example.mailauthstudy.util.EmailUtil;
import com.example.mailauthstudy.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableAsync
@Transactional
public class EmailService {
    private final EmailUtil emailUtil;
    private final RedisUtil redisUtil;
    private final UserRepository userRepository;


    @Async
    public void sendAuthEmail(User user) {

        String emailAddress = user.getEmailAddress();

        //        String authToken = UUID.randomUUID().toString();
        Random rand = new Random();
        String authToken = String.valueOf(rand.nextInt(888888) + 111111);

        EmailAuth emailAuth = EmailAuth.builder()
                .email(emailAddress)
                .authKey(authToken)
                .build();

//        emailAuthRepository.save(emailAuth);

        ArrayList<String> to = new ArrayList<>();
        to.add(emailAddress);

        String content = "메일 발송 테스트입니다";
        EmailSenderDto dto = EmailSenderDto.builder()
                .from("syhwang.mv@movements.kr")
                .to(to)
                .subject("회원가입 이메일 인증")
                .content("인증번호는 " + authToken + "입니다")
                .build();

        emailUtil.send(dto);
        // redis 에 authToken - user 저장
        redisUtil.setValueWithExpireTime(authToken, emailAddress, 3L);

    }

    public void confirmEmail(String authToken, String email) {

        // redis 에서 authToken 확인
        String value = redisUtil.getValue(authToken);
        if (!value.equals(email)) {
            throw new RuntimeException("유효하지 않은 토큰입니다.");
        }

        User user = userRepository.findByEmailAddress(email)
            .orElseThrow(() -> new RuntimeException("등록되지 않은 회원입니다"));

        user.emailVerifySuccess();
        redisUtil.deleteData(authToken);

    }


}
