package com.example.mailauthstudy.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
public class User{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String emailAddress;
    private String loginId;
    private String password;

    private Boolean emailVerified;

    @Builder
    public User(String emailAddress, String loginId, String password) {
        this.emailAddress = emailAddress;
        this.loginId = loginId;
        this.password = password;
        emailVerified = false;
    }

    // 이메일 인증 성공
    public void emailVerifySuccess() {
        emailVerified = true;
    }


}
