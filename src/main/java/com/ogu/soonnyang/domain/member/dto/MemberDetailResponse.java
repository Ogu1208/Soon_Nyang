package com.ogu.soonnyang.domain.member.dto;

import com.ogu.soonnyang.domain.cat.dto.CatDetailResponse;
import com.ogu.soonnyang.domain.cat.entity.Cat;
import com.ogu.soonnyang.domain.member.entity.Member;
import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDetailResponse {

    private Long memberId;
    private String email;
    private String nickname;

    public static MemberDetailResponse from(Member member) {
        return MemberDetailResponse.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }
}
