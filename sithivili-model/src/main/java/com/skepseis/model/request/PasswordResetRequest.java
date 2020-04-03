package com.skepseis.model.request;

import lombok.Data;

@Data
public class PasswordResetRequest {
    String username;
    String password;
}
