package com.example.fest.dto;


import com.example.fest.entity.UserDomain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobApplicationDto {
    private Long id;
    private String title;
    private String text;
    private UserDto employer;
    private UserDto volunteer;
}
