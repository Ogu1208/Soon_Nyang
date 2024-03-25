package com.ogu.soonnyang.domain.cat.dto;

import com.ogu.soonnyang.domain.cat.entity.type.CatGender;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class CreateCatRequest {
    private String name;
    private Integer age;
    private CatGender gender;
//    private Float lat;
//    private Float lng;
}