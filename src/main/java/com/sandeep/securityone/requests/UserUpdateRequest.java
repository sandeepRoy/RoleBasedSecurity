package com.sandeep.securityone.requests;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String city;
    private String phonenumber;
}
