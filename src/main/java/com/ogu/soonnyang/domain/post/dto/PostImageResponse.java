package com.ogu.soonnyang.domain.post.dto;

import com.ogu.soonnyang.domain.member.dto.MemberDetailResponse;
import com.ogu.soonnyang.domain.member.entity.Member;
import com.ogu.soonnyang.domain.post.entity.Post;
import com.ogu.soonnyang.domain.post.entity.PostImage;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostImageResponse {

    private Long postImageId;
    private String originalFileName;
    private String imageUrl;

    public static PostImageResponse from(PostImage postImage) {
        return PostImageResponse.builder()
                .postImageId(postImage.getId())
                .originalFileName(postImage.getOriginalFileName())
                .imageUrl(postImage.getImageUrl())
                .build();
    }
}
