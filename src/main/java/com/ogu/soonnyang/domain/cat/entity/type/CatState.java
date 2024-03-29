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
public enum CatState {
    ACTIVE("활성화"),
    INACTIVE("비활성화");

    private String description;

    @JsonCreator
    public CatState convert(String catState) {
        try {
            return CatState.valueOf(catState.toUpperCase());
        } catch (ApplicationException e) {
            log.debug("CatState.convert() exception occur value: {}", catState);
            throw new ApplicationException(ErrorCode.INVALID_CAT_STATUS);
        }
    }
}