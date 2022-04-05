package com.example.fest.service.impl;

import com.example.fest.entity.CommentDomain;
import com.example.fest.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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
}
