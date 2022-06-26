package com.example.mailauthstudy.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class EmailAuth {

    private static final Long EMAIL_AUTH_EXPIRE_TIME = 3L; // 이메일 인증 유효기간 3분

    @Id
    private String email;
    private String authToken;
    private Boolean expired;
    private LocalDateTime expireDate;

    @Builder
    public EmailAuth(String email, String authKey) {
        this.email = email;
        this.authToken = authKey;
        this.expired = false;
        this.expireDate = LocalDateTime.now().plusMinutes(EMAIL_AUTH_EXPIRE_TIME);
    }

    public void useToken() {
        this.expired = true;
    }
}
