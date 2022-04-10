package com.example.fest.service;

import com.example.fest.dto.CommentDto;
import com.example.fest.entity.CommentDomain;

import java.util.List;

public interface CommentService {
    Boolean comment(CommentDomain entity);
    List<CommentDto> getComments();
}
