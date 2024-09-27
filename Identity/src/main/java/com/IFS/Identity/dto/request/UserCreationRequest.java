package com.IFS.Identity.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserCreationRequest {
    private String userName;

    @Size(min = 8,message = "Password must be at least 8 character")
    private String passWord;
    private String firstName;
    private String lastName;
    private LocalDate dob;
}
