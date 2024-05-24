package com.ogu.soonnyang.domain.member.dto.request;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class UpdateMemberRequest {

    private String nickname;
}
