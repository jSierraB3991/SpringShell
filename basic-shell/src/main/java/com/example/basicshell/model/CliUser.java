package com.example.basicshell.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CliUser {
    private Long id;
    private String userName;
    private String password;
    private String fullName;
    private Gender gender;
}
