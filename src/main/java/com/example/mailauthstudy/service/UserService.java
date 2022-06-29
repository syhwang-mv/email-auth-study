package com.example.mailauthstudy.service;

import com.example.mailauthstudy.entity.User;
import com.example.mailauthstudy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    public User signUp(String emailAddress, String loginId, String password) {
//        duplicateEmailCheck(emailAddress);

        User user = new User(emailAddress, loginId, password);
        userRepository.save(user);

        emailService.sendAuthEmail(user);


        return user;
    }



    // 이메일 중복 체크
//    public void duplicateEmailCheck(String emailAddress) {
//        if (userRepository.findByEmailAddress(emailAddress).isPresent()) {
//            throw new RuntimeException("이미 가입된 이메일입니다");
//        }
//    }

    // 로그인
    public void login(String loginId, String password) {
        User user = userRepository.findByLoginIdAndPassword(loginId, password)
                .orElseThrow(() -> new RuntimeException("아이디 또는 비밀번호가 잘못되었습니다"));
        if (user.getEmailVerified()) {
            // 토큰 발급 및 로그인 로직 작성 필요

        } else {
            throw new RuntimeException("이메일 인증이 필요합니다");
        }

    }
}
