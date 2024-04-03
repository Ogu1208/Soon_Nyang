package com.ogu.soonnyang.common.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MessageDTO {
    private int status;
    private String message;
    private String redirectURI;
}
