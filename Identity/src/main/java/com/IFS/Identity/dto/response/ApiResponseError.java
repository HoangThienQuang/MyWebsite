package com.IFS.Identity.dto.response;


import lombok.Data;

@Data
public class ApiResponseError {
    private int code;
    private String message;
}
