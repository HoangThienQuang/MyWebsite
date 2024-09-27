package com.IFS.Identity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    NOT_IMPLEMENT_EXCEPTION(9999,"Uncategoried Error"),
    SUCCESS_STATUS(1000,"Request success"),
    USER_EXISTED(1102, "User Existed"),
    PASSWORD_INVALID(1103,"Password must be at least 8 characters"),
    USERNAME_INVALID(1104,"User name must be at least 3 characters")
    ;
    private final int code;
    private final String message;
}
