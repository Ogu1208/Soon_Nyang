package com.ogu.soonnyang.domain.cat.dto;

import com.ogu.soonnyang.domain.cat.entity.Cat;
import com.ogu.soonnyang.domain.cat.entity.type.CatGender;
import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatDetailResponse {
    private Long catId;
    private String name;
    private Integer age;
    private CatGender gender;
    private Long followerCnt;
    private String imageUrl;
    private Float latitude;
    private Float longitude;

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
