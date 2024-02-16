package com.sandeep.securityone.responses;

import com.sandeep.securityone.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    public int id;
    public String firstname;
    public String lastname;
    public String email;
    public String city;
    public String phonenumber;
    public Role role;
    public String username;
}
