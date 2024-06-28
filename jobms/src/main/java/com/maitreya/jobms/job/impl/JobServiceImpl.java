package com.maitreya.jobms.job.impl;

import com.maitreya.jobms.job.Job;
import com.maitreya.jobms.job.JobRepository;
import com.maitreya.jobms.job.JobService;
import com.maitreya.jobms.job.dto.JobWithCompanyDTO;
import com.maitreya.jobms.job.external.Company;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public void addJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public List<JobWithCompanyDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        List<JobWithCompanyDTO> jobWithCompanyDTOS = new ArrayList<>();

        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private JobWithCompanyDTO convertToDto(Job job) {
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setJob(job);

        RestTemplate restTemplate = new RestTemplate();
        Company company =  restTemplate.getForObject("http://localhost:8081/companies/" + job.getCompanyId(), Company.class);
        jobWithCompanyDTO.setCompany(company);

        return jobWithCompanyDTO;
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateJobById(Long id, Job updatedjob) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if (optionalJob.isPresent()) {
            Job jobToUpdate = optionalJob.get();
            jobToUpdate.setTitle(updatedjob.getTitle());
            jobToUpdate.setDescription(updatedjob.getDescription());
            jobToUpdate.setMinSalary(updatedjob.getMinSalary());
            jobToUpdate.setMaxSalary(updatedjob.getMaxSalary());
            jobToUpdate.setLocation(updatedjob.getLocation());
            jobRepository.save(jobToUpdate);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteJobById(Long id) {
        if(jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
