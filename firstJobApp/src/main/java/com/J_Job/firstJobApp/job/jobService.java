package com.J_Job.firstJobApp.job;

import java.util.List;

public interface jobService {
    List<Job> findAll();
    void createJob(Job job);


    Job getJobById(Long id);

    boolean DeleteById(Long id);



    boolean updateJob(Long id, Job updatedJob);
}
