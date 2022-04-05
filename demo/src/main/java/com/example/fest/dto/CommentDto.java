package com.example.fest.dto;

import com.example.fest.entity.JobApplicationDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Column;

@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private String text;
    private Long joApplicationId;//TODO
}
