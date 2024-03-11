package com.ogu.soonnyang.domain.cat.dto;

import com.ogu.soonnyang.domain.cat.entity.type.CatGender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CatInfoRequest {
    private String name;
    private Integer age;
    private CatGender gender;
    private MultipartFile image;
    private Float lat;
    private Float lng;
}