package com.ogu.soonnyang.domain.cat.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ogu.soonnyang.domain.cat.entity.Cat;
import com.ogu.soonnyang.domain.cat.entity.type.CatGender;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CatDetailResponse {
    private Long catId;
    private String name;
    private Integer age;
    private CatGender gender;
    private Long followerCnt;
    private String imageUrl;
    private Float lat;
    private Float lng;


    @Builder
    public CatDetailResponse(Long catId, String name, Integer age, CatGender gender,
                             Long followerCnt, String imageUrl, Float lat, Float lng) {
        this.catId = catId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.followerCnt = followerCnt;
        this.imageUrl = imageUrl;
        this.lat = lat;
        this.lng = lng;
    }

    public static CatDetailResponse from(Cat cat) {
        return CatDetailResponse.builder()
                .catId(cat.getCatId())
                .name(cat.getName())
                .age(cat.getAge())
                .gender(cat.getGender())
                .followerCnt(cat.getFollowerCnt())
                .imageUrl(cat.getImageUrl())
                .build();
    }
}
