package com.J_Job.firstJobApp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTML;
import java.util.ArrayList;
import java.util.List;

@RestController

public class jobController {


    private jobService jobService;

    public jobController(jobService jobService) {
        this.jobService = jobService;
    }
    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll(){
        List<Job> jobs = jobService.findAll();
            return new ResponseEntity<>(jobs,HttpStatus.OK);
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
     //   jobService.createJob(job);
        if (job.getTitle() == null || job.getDescription() == null) {
            return new ResponseEntity<>("Oops! You have made a mistake", HttpStatus.BAD_REQUEST);
        }
        jobService.createJob(job);
        return new ResponseEntity<>("Job is added", HttpStatus.CREATED);
    }
    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJob(@PathVariable Long id){
        Job job = jobService.getJobById(id);
        if (job!=null){
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> DeleteJob(@PathVariable Long id){
        boolean deleted = jobService.DeleteById(id);
        if (deleted){
            return new ResponseEntity<>("SuccessFully deleted",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Opps You have made Mistake",HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/jobs/{id}")
    public ResponseEntity<String> UpdateJob(@PathVariable Long id , @RequestBody Job updatedJob){

        boolean updated = jobService.updateJob(id,updatedJob);
        if (updated)return new ResponseEntity<>("JOB UPDATED", HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
