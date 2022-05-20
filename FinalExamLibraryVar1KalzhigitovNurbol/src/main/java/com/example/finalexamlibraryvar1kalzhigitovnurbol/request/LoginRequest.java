package com.example.finalexamlibraryvar1kalzhigitovnurbol.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor

public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
