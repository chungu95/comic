package com.comic.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServiceErrorCode {
    ROLE("ROL"),
    AUTHENTICATION("AUT"),
    USER("USR"),
    CATEGORY("CAT"),
    STORY_TYPE("STY");

    private final String code;
}