package com.ogu.soonnyang.domain.cat.dto.response;

import com.ogu.soonnyang.domain.post.entity.Post;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostSpotResponse {

    private Long postId;
    private String image;
    private Double latitude;
    private Double longitude;
    private LocalDateTime createdAt;

    public static PostSpotResponse fromPost(Post post) {
        return PostSpotResponse.builder()
                .postId(post.getPostId())
                .image(post.getImage())
                .latitude(post.getLatitude())
                .longitude(post.getLongitude())
                .createdAt(post.getCreatedAt())
                .build();

    }
}
