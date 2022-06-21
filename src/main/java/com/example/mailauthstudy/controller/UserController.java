package com.example.mailauthstudy.controller;

import com.example.mailauthstudy.dto.SignUpRequestDto;
import com.example.mailauthstudy.service.EmailService;
import com.example.mailauthstudy.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final EmailService emailService;


    /*
    0. 이메일 중복 확인
    1. 가회원가입
    2. 인증 메일 발송
    3. 이메일 인증 완료
     */

    
    
    @PostMapping("/signup")
    public ResponseEntity<HashMap> signUp(@RequestBody SignUpRequestDto dto) {
        log.info("[회원가입] email = {}", dto.getEmailAddress());
        
        userService.signUp(dto.getEmailAddress(), dto.getLoginId(), dto.getPassword());

        HashMap<String, String> result = new HashMap<>();
        result.put("message", dto.getEmailAddress() + "로 인증 메일을 발송했습니다.");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/email-verify")
    public ResponseEntity<HashMap> emailVerify(@RequestParam String email, @RequestParam String authToken) {

        log.info("[메일인증] email = {}, authToken = {}", email, authToken);

        emailService.confirmEmail(authToken, email);

        HashMap<String, String> result = new HashMap<>();
        result.put("message", "메일 인증이 완료되었습니다.");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/login")
    public void login(String loginId, String password) {
//        userService.login(loginId, password);
    }


}
