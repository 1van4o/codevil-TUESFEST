package com.example.fest.service.impl;

import com.example.fest.dto.CommentDto;
import com.example.fest.dto.JobApplicationDto;
import com.example.fest.entity.CommentDomain;
import com.example.fest.entity.JobApplicationDomain;
import com.example.fest.mapper.Mapper;
import com.example.fest.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    EntityManager entityManager;

    @Override
    @Transactional
    public Boolean comment(CommentDomain entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return true;
    }

    @Override
    public List<CommentDto> getComments() {
        Query query = entityManager.createNamedQuery("CommentsDomain.findByAppId");

        List<CommentDomain> comments =  query.getResultList();
        List<CommentDto> dtos = new ArrayList<>();

        for (CommentDomain comment : comments) {
            dtos.add(Mapper.toCommentDto(comment));
        }
        return dtos;
    }
}
