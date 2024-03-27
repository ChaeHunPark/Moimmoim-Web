package com.example.MoimMoim.dto;

import lombok.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotNull
    @Size(min = 3, max = 50)
    private String userId;

    @NotNull
    @Size(min = 3, max = 100)
    private String password;
}