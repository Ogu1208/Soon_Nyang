package com.ogu.soonnyang.domain.post.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.ogu.soonnyang.domain.cat.dto.CatDetailResponse;
import com.ogu.soonnyang.domain.cat.entity.Cat;
import com.ogu.soonnyang.domain.member.dto.MemberDetailResponse;
import com.ogu.soonnyang.domain.member.entity.Member;
import com.ogu.soonnyang.domain.post.entity.Post;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostListResponse {

    private Long postId;
    private CatDetailResponse catDetailResponse;
    private MemberDetailResponse memberDetailResponse;
    private Float latitude;
    private Float longitude;
    private String content;
    private String image;
    private Long likeCount;
    //내가 해당 게시글에 감정표시를 했는지 담겨있는 필드
    private String myEmotion;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;

    public static PostListResponse from(Post post) {
        return PostListResponse.builder()
                .postId(post.getPostId())
                .catDetailResponse(CatDetailResponse.from(post.getCat()))
                .memberDetailResponse(MemberDetailResponse.from(post.getMember()))
                .latitude(post.getLatitude())
                .longitude(post.getLongitude())
                .content(post.getContent())
                .likeCount(post.getLikeCount())
                .build();
    }

    @QueryProjection
    public PostListResponse(Post post, Cat cat, Member member) {
        this.postId = post.getPostId();
        this.catDetailResponse = CatDetailResponse.from(cat);
        this.memberDetailResponse = MemberDetailResponse.from(member);
        this.latitude = post.getLatitude();
        this.longitude = post.getLongitude();
        this.content = post.getContent();
        this.image = post.getImage();
        this.createdAt = post.getCreatedAt();
    }
}
