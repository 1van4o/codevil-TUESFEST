package com.example.fest.mapper;


//import com.example.fest.dto.CommentDto;
import com.example.fest.dto.CommentDto;
import com.example.fest.dto.JobApplicationDto;
import com.example.fest.dto.UserDto;
//import com.example.fest.entity.CommentDomain;
import com.example.fest.entity.CommentDomain;
import com.example.fest.entity.JobApplicationDomain;
import com.example.fest.entity.UserDomain;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static UserDto toDto(UserDomain entity){
        return new UserDto(entity.getUsername(), null, entity.getType());
    }

    public static UserDomain toUser(UserDto dto, UserDomain password){
        return new UserDomain(null, dto.getUsername(), null, dto.getType());
    }

    public static JobApplicationDto toJobDto(JobApplicationDomain app){
        return new JobApplicationDto(app.getId(), app.getTitle(), app.getText(), toDto(app.getEmployer()), toDto(app.getVolunteer()));
    }
    public static JobApplicationDomain toJob(JobApplicationDto dto){
        return new JobApplicationDomain(dto.getTitle(), dto.getText());
    }

    public static CommentDomain toComment(CommentDto dto){
        return new CommentDomain(null, null, null, dto.getText());
    }

    public static CommentDto toCommentDto(CommentDomain entity){
        return new CommentDto(toDto(entity.getUserId()),entity.getText(), entity.getJob_application_id());
    }
}
