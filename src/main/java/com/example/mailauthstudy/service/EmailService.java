package com.example.mailauthstudy.service;

import com.example.mailauthstudy.entity.EmailAuth;
import com.example.mailauthstudy.entity.User;
import com.example.mailauthstudy.repository.EmailAuthRepository;
import com.example.mailauthstudy.repository.UserRepository;
import com.example.mailauthstudy.util.MailUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableAsync
public class EmailService {
//
//    private final JavaMailSender sender;
//    private final EmailAuthRepository emailAuthRepository;
//    private final UserRepository userRepository;
//
//    @Async
//    public void sendMail(String email, String authToken) {
//
//        try {
//            MailUtils mailUtils = new MailUtils(sender);
//
//            mailUtils.setFrom("movements@movements.kr", "테스트");
//            mailUtils.setTo(email);
//            mailUtils.setSubject("회원가입 이메일 인증메일입니다");
////            mailUtils.setText("회원가입 인증 링크 - <a href='http://localhost:8080/user/email-verify?email=" + email
////                    + "&authToken=" + authToken
////                    +"' target='_blank'> 이메일 인증 확인 </a>");
//            mailUtils.setText("인증번호 [" + authToken + "]");
//
//
//            log.info("[인증메일] 전송 시도");
//            mailUtils.send();
//            log.info("[인증메일] 전송 완료");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void confirmEmail(String authToken, String email) {
//        EmailAuth emailAuth = emailAuthRepository.findByEmailAndAuthTokenAndExpireDateAfterAndExpired(email, authToken, LocalDateTime.now(), false)
//                .orElseThrow(() -> new RuntimeException("유효하지 않은 토큰입니다."));
//        User user = userRepository.findByEmailAddress(email)
//                .orElseThrow(() -> new RuntimeException("등록되지 않은 회원입니다"));
//        user.emailVerifySuccess();
//        emailAuth.useToken();
//    }


}
