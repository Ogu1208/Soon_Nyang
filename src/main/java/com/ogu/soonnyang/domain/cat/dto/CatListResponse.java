package com.ogu.soonnyang.domain.cat.dto;

import com.ogu.soonnyang.domain.cat.entity.Cat;
import com.ogu.soonnyang.domain.cat.entity.type.CatGender;
import lombok.Builder;

public class CatListResponse {
    private Long catId;
    private String name;
    private Integer age;
    private CatGender gender;
    private Long followerCnt;
    private String imageUrl;

    @Builder
    public CatListResponse(Long catId, String name, Integer age, CatGender gender, Long followerCnt, String imageUrl) {
        this.catId = catId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.followerCnt = followerCnt;
        this.imageUrl = imageUrl;
    }

    public static CatListResponse from(Cat cat) {
        return CatListResponse.builder()
                .catId(cat.getCatId())
                .name(cat.getName())
                .age(cat.getAge())
                .gender(cat.getGender())
                .followerCnt(cat.getFollowerCnt())
                .imageUrl(cat.getImageUrl())
                .build();
    }
}