package com.quicklance.backend.controller;

import com.quicklance.backend.entity.Application;
import com.quicklance.backend.entity.Job;
import com.quicklance.backend.repository.ApplicationRepository;
import com.quicklance.backend.repository.JobRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")

public class ApplicationController {

    @Autowired
    private ApplicationRepository repo;

    @Autowired
    private JobRepository jobRepo;

    // Freelancer applies
    @PostMapping
    public Application apply(@RequestBody Application app) {
        app.setStatus("PENDING");
        return repo.save(app);
    }

    // Client sees applications
    @GetMapping("/client/{email}")
    public List<Application> getForClient(@PathVariable String email) {
        return repo.findByClientEmail(email);
    }

    // Freelancer sees applications
    @GetMapping("/freelancer/{email}")
    public List<Application> getForFreelancer(@PathVariable String email) {
        return repo.findByFreelancerEmail(email);
    }

    // Client accepts freelancer
    @PutMapping("/accept/{appId}")
    public String accept(@PathVariable Long appId) {

        Application app = repo.findById(appId).orElseThrow();

        // 1. Mark selected freelancer as ACCEPTED
        app.setStatus("ACCEPTED");
        repo.save(app);

        // 2. Reject all other applications for this job
        List<Application> all = repo.findAll();
        for (Application a : all) {
            if (a.getJobId().equals(app.getJobId()) && !a.getId().equals(appId)) {
                a.setStatus("REJECTED");
                repo.save(a);
            }
        }

        // 3. Mark job as IN_PROGRESS
        Job job = jobRepo.findById(app.getJobId()).orElseThrow();
        job.setStatus("IN_PROGRESS");
        jobRepo.save(job);

        return "HIRED";
    }
    @PutMapping("/complete/{appId}")
public String completeJob(@PathVariable Long appId) {

    Application app = repo.findById(appId).orElseThrow();

    // Mark application completed
    app.setStatus("COMPLETED");
    repo.save(app);

    // Mark job completed
    Job job = jobRepo.findById(app.getJobId()).orElseThrow();
    job.setStatus("COMPLETED");
    jobRepo.save(job);

    return "COMPLETED";
}


    // Client manually changes status
    @PutMapping("/{id}/{status}")
    public Application updateStatus(@PathVariable Long id, @PathVariable String status) {
        Application app = repo.findById(id).orElseThrow();
        app.setStatus(status);
        return repo.save(app);
    }
   



}