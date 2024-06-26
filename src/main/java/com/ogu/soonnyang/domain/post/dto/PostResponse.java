package com.ogu.soonnyang.domain.post.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.ogu.soonnyang.domain.cat.dto.response.CatDetailResponse;
import com.ogu.soonnyang.domain.cat.entity.Cat;
import com.ogu.soonnyang.domain.member.dto.MemberDetailResponse;
import com.ogu.soonnyang.domain.member.entity.Member;
import com.ogu.soonnyang.domain.post.entity.Post;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostResponse {

    private Long postId;
    private CatDetailResponse catDetailResponse;
    private MemberDetailResponse memberDetailResponse;
    private Double latitude;
    private Double longitude;
    private String content;
    private String image;
    private Long likeCount;
    //내가 해당 게시글에 감정표시를 했는지 담겨있는 필드
    private String myEmotion;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;
    private List<PostImageResponse> postImageResponses; // 이미지 URL을 담는 변수명을 files로 변경

    public static PostResponse from(Post post) {
        return PostResponse.builder()
                .postId(post.getPostId())
                .catDetailResponse(CatDetailResponse.from(post.getCat()))
                .memberDetailResponse(MemberDetailResponse.from(post.getMember()))
                .latitude(post.getLatitude())
                .longitude(post.getLongitude())
                .content(post.getContent())
                .likeCount(post.getLikeCount())
                .postImageResponses(post.getFiles()
                        .stream()
                        .map(PostImageResponse::from)
                        .toList())
                .build();
    }

    @QueryProjection
    public PostResponse(Post post, Cat cat, Member member) {
        this.postId = post.getPostId();
        this.catDetailResponse = CatDetailResponse.from(cat);
        this.memberDetailResponse = MemberDetailResponse.from(member);
        this.latitude = post.getLatitude();
        this.longitude = post.getLongitude();
        this.content = post.getContent();
        this.image = post.getImage();
        this.createdAt = post.getCreatedAt();
        this.postImageResponses = post.getFiles()
                .stream()
                .map(PostImageResponse::from)
                .toList();
    }

    public static String randomLikeOrUnlike() {
        Random random = new Random();
        int randomNum = random.nextInt(2); // 0 또는 1을 반환
        if (randomNum == 0) {
            return "like";
        } else {
            return "unlike";
        }
    }

    public static Long randomLikeCount() {
        Random random = new Random();
        return random.nextLong(20);
    }
}
