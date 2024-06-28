package com.maitreya.jobms.job;

import com.maitreya.jobms.job.dto.JobWithCompanyDTO;

import java.util.List;

public interface JobService {
    void addJob(Job job);

    List<JobWithCompanyDTO> findAll();

    Job getJobById(Long id);

    boolean updateJobById(Long id, Job job);

    boolean deleteJobById(Long id);
}
