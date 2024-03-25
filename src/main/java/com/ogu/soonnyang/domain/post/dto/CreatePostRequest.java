package com.ogu.soonnyang.domain.post.dto;

import com.ogu.soonnyang.domain.member.dto.MemberDTO;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class CreatePostRequest {

    private Long catId;

    private float latitude;

    private float longitude;

    private String content;

    /*
    public PostDTO toDto(MemberDTO memberDto) {
        return PostDTO(memberDto, null);
    }

    public PostDTO toDto(MemberDTO memberDTO) {
        return PostDTO.builder()
                .memberDTO(memberDTO)
                .catDTO()
                .build();

    }
     */
}
