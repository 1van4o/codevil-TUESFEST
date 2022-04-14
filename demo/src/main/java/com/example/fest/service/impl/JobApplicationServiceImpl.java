package com.example.fest.service.impl;

import com.example.fest.dto.JobApplicationDto;
import com.example.fest.entity.JobApplicationDomain;
import com.example.fest.entity.UserDomain;
import com.example.fest.mapper.Mapper;
import com.example.fest.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {
    @Autowired
    EntityManager entityManager;

    @Override
    @Transactional
    public Boolean save(JobApplicationDomain entity) {//TODO problem
        entityManager.persist(entity);
        entityManager.flush();
        return true;
    }

    @Override
    public JobApplicationDomain findById(Long id) {
        Query query = entityManager.createNamedQuery("JobApplicationDomain.findById").setParameter("id", id);
        List<JobApplicationDomain> apps = query.getResultList();
        if(apps.isEmpty()){
            return null;
        }else{
            return apps.get(0);
        }
    }

    @Override
    @Transactional
    public Boolean update(JobApplicationDomain entity) {
        JobApplicationDomain updated = entityManager.merge(entity);
        entityManager.flush();
        return  true;
    }

    @Override
    public List<JobApplicationDomain> getAll() {
        Query query = entityManager.createNamedQuery("JobApplicationDomain.getAll");

        List<JobApplicationDomain> apps =  query.getResultList();
        List<JobApplicationDto> dtos = new ArrayList<>();

        for (JobApplicationDomain app : apps) {
            dtos.add(Mapper.toJobDto(app));
        }
        return apps;
    }
}
