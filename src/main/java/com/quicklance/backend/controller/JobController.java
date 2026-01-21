package com.quicklance.backend.controller;

import com.quicklance.backend.entity.Job;
import com.quicklance.backend.repository.JobRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "https://quicklancefrontend.vercel.app")
public class JobController {

    @Autowired
    private JobRepository jobRepo;

    // POST A JOB
    @PostMapping
    public Job postJob(@RequestBody Job job) {
        job.setStatus("OPEN");
        return jobRepo.save(job);
    }
    @PutMapping("/{jobId}/status")
public Job updateStatus(
        @PathVariable Long jobId,
        @RequestParam String status
) {
    Job job = jobRepo.findById(jobId)
            .orElseThrow(() -> new RuntimeException("Job not found"));

    job.setStatus(status);
    return jobRepo.save(job);
}


    // GET ALL OPEN JOBS (for freelancers)
    @GetMapping("/open")
    public List<Job> getOpenJobs() {
        return jobRepo.findByStatus("OPEN");
    }

    // GET JOBS BY CLIENT
    @GetMapping("/client/{email}")
    public List<Job> getClientJobs(@PathVariable String email) {
        return jobRepo.findByClientEmail(email);
    }
    
}
