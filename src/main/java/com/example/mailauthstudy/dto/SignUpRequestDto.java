package com.example.mailauthstudy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpRequestDto {
    private String emailAddress;
    private String loginId;
    private String password;
}
