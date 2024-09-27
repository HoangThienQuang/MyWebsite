package com.IFS.Identity.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserCreationRequest {
    @Size(min = 3, message = "USERNAME_INVALID")
    private String userName;
    @Size(min = 8,message = "PASSWORD_INVALID")
    private String passWord;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    //private LocalDate birthDay;

}
