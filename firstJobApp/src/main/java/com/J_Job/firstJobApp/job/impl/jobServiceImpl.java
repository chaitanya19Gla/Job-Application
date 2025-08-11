package com.J_Job.firstJobApp.job.impl;

import com.J_Job.firstJobApp.job.Job;
import com.J_Job.firstJobApp.job.JobRepository;
import com.J_Job.firstJobApp.job.jobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class jobServiceImpl implements jobService {

    private JobRepository jobRepository;
//this is change
    public jobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    //    private List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;
    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
//        job.setId(nextId++);
        //jobs.add(job);
        jobRepository.save(job);
    }
    @Override
    public Job getJobById(Long id){
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean DeleteById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        //This line will check whether the object is whether the Job exist or not if it does then the Joboptional.get()
        // will retreive the that present job object in Job
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()){
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());

            //chnage
            jobRepository.save(job);
            return true;
        }
        return false;
    }


}
