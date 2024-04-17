package com.ogu.soonnyang.domain.cat.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ogu.soonnyang.domain.cat.entity.type.CatGender;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class CatRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String age;

    @NotBlank
    private CatGender gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate TNRDate;
//    private Float lat;
//    private Float lng;
}