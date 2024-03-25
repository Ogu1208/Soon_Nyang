package com.ogu.soonnyang.domain.cat.dto;

import com.ogu.soonnyang.domain.cat.entity.Cat;
import com.ogu.soonnyang.domain.cat.entity.type.CatGender;
import com.ogu.soonnyang.domain.cat.entity.type.CatState;
import com.ogu.soonnyang.domain.member.entity.Member;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class CatDTO {

    private Long catId;
    private String name;
    private Integer age;
    private CatGender gender;
    private Long followerCnt;
    private String imageUrl;
    private CatState isActive;

    public static CatDTO from(Cat entity) {
        return CatDTO.builder()
                .catId(entity.getCatId())
                .name(entity.getName())
                .age(entity.getAge())
                .followerCnt(entity.getFollowerCnt())
                .imageUrl(entity.getImageUrl())
                .isActive(entity.getIsActive())
                .build();
    }

    public Cat toEntity() {
        return new Cat(
                catId,name,age,gender,followerCnt,imageUrl,isActive
        );
    }
}
