package com.ogu.soonnyang.domain.cat.entity.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.ogu.soonnyang.common.exception.ApplicationException;
import com.ogu.soonnyang.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
@AllArgsConstructor
public enum CatGender {
    MALE,
    FEMALE;


    @JsonCreator
    public CatGender convert(String gender) {
        try {
            return CatGender.valueOf(gender.toUpperCase());
        } catch (ApplicationException e) {
            log.debug("CatGender.convert() exception occur value: {}", gender);
            throw new ApplicationException(ErrorCode.INVALID_CAT_GENDER);
        }
    }
}