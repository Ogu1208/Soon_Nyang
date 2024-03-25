package com.ogu.soonnyang.domain.cat.dto;

import com.ogu.soonnyang.domain.cat.entity.type.CatGender;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class CreateCatRequest {
    private String name;
    private Integer age;
    private CatGender gender;
//    private MultipartFile image;
//    private Float lat;
//    private Float lng;
}