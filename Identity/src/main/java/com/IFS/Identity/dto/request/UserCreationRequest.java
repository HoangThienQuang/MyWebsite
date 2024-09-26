package com.IFS.Identity.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserCreationRequest {
    private String userName;
    private String passWord;
    private String firstName;
    private String lastName;
    private LocalDate dob;
}
