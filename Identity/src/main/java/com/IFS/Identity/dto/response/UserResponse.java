package com.IFS.Identity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private String Id;

    private String userName;
    private String passWord;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
