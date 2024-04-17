package com.ogu.soonnyang.domain.cat.dto.response;

import com.ogu.soonnyang.domain.cat.entity.Cat;
import com.ogu.soonnyang.domain.cat.entity.type.CatGender;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDate;
import java.util.Random;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CatListResponse {
    private Long catId;
    private String name;
    private String age;
    private CatGender gender;
    private Long followerCnt;
    private String imageUrl;
    private LocalDate TNRDate;
    private int isFollowing;


    public static CatListResponse from(Cat cat) {
        return CatListResponse.builder()
                .catId(cat.getCatId())
                .name(cat.getName())
                .age(cat.getAge())
                .gender(cat.getGender())
                .followerCnt(cat.getFollowerCnt())
                .imageUrl(cat.getImageUrl())
                .TNRDate(cat.getTNRDate())
                .isFollowing(randomZeroOrOne())
                .build();
    }

    @QueryProjection
    public CatListResponse(Cat cat, int isFollowing) {
        this.catId = cat.getCatId();
        this.name = cat.getName();
        this.age = cat.getAge();
        this.gender = cat.getGender();
        this.followerCnt = cat.getFollowerCnt();
        this.imageUrl = cat.getImageUrl();
        this.isFollowing = isFollowing;
    }

    private static int randomZeroOrOne() {
        Random random = new Random();
        return random.nextInt(2);
    }
}