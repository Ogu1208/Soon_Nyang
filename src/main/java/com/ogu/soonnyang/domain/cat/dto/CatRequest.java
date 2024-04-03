package com.ogu.soonnyang.domain.cat.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ogu.soonnyang.domain.cat.entity.type.CatGender;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class CatRequest {
    private String name;
    private Integer age;
    private CatGender gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate TNRDate;
//    private Float lat;
//    private Float lng;
}