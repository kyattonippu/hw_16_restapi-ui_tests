package com.kyattonippu.api.models;

import lombok.Data;

@Data
public class LoginRequest {
    String username, password;
}