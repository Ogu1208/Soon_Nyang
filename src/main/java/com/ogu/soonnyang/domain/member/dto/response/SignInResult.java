package com.ogu.soonnyang.domain.member.dto.response;

import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class SignInResult extends SignUpResult {

    private String token;
}
