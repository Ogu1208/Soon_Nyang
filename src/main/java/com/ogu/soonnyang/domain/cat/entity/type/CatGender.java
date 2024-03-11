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
    MALE("남자"),
    FEMALE("여자");

    private final String description;

    @JsonCreator
    public static CatGender from(String value) {
        for (CatGender gender : CatGender.values()) {
            if (gender.getDescription().equals(value)) {
                return gender;
            }
        }
        log.debug("CatGender.from() exception occur sub: {}", value);
        throw new ApplicationException(ErrorCode.INVALID_CAT_GENDER);
    }
}