package com.ogu.soonnyang.domain.member.dto.response;

import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignUpResult {

    private boolean success;

    private int code;

    private String msg;
}
