package com.example.fest.service;

import com.example.fest.dto.JobApplicationDto;
import com.example.fest.entity.JobApplicationDomain;

import java.util.List;

public interface JobApplicationService {
    Boolean save(JobApplicationDomain entity);
    JobApplicationDomain findById(Long id);
    Boolean update(JobApplicationDomain entity);
    List<JobApplicationDto> getAll();
}
