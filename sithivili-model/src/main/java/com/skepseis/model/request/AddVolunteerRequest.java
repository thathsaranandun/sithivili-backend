package com.skepseis.model.request;

import lombok.Data;

@Data
public class AddVolunteerRequest {
    private String username;
    private String name;
    private String password;
    private String dateOfBirth;
    private String nic;
    private String email;
    private String mobile;
    private String gender;
}
