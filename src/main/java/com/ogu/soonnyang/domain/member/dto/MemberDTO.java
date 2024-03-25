package com.ogu.soonnyang.domain.member.dto;

import com.ogu.soonnyang.domain.member.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class MemberDTO {

    private Long memberId;
    private String password;
    private String email;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;

    public static MemberDTO from(Member entity) {
        return MemberDTO.builder()
                .memberId(entity.getMemberId())
                .password(entity.getPassword())
                .email(entity.getEmail())
                .nickname(entity.getNickname())
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .memberId(memberId)
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();
    }
}
