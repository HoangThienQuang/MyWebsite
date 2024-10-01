package com.IFS.Identity.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    @Size(min = 8,message = "PASSWORD_INVALID")
    private String passWord;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    //private LocalDate birthDay;
}
