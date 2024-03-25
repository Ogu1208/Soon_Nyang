package com.ogu.soonnyang.domain.post.dto;

import com.ogu.soonnyang.domain.cat.dto.CatDTO;
import com.ogu.soonnyang.domain.member.dto.MemberDTO;
import com.ogu.soonnyang.domain.post.entity.Post;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class PostDTO {

    private Long postId;
    private MemberDTO memberDTO;
    private CatDTO catDTO;
    private float latitude;
    private float longitude;
    private String content;

//    public static PostDTO of(MemberDTO memberDTO, String title, String content, Set<HashtagDto> hashtagDtos) {
//        return new PostDTO(null, userAccountDto, title, content, hashtagDtos, null, null, null, null);
//    }


    public static PostDTO from(Post entity) {
        return PostDTO.builder()
                .postId(entity.getPostId())
                .memberDTO(MemberDTO.from(entity.getMember()))
                .catDTO(CatDTO.from(entity.getCat()))
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .build();
    }
}
