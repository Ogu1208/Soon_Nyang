package com.ogu.soonnyang.domain.member.dto;

import com.ogu.soonnyang.domain.member.entity.Authority;
import com.ogu.soonnyang.domain.member.entity.Member;
import lombok.*;

import java.util.Set;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDetailResponse {

    private Long memberId;
    private String email;
    private String nickname;
    private String introduction;
    private Set<Authority> authorities;

    public static MemberDetailResponse from(Member member) {
        return MemberDetailResponse.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .introduction(member.getIntroduction())
                .authorities((member.getRawAuthorities()))
                .build();
    }
}
