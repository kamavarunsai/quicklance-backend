package com.quicklance.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ---------- Job Details ---------- */
    @Column(name = "job_id", nullable = false)
    private Long jobId;

    @Column(name = "job_title")
    private String jobTitle;

    /* ---------- Freelancer Details ---------- */
    @Column(name = "freelancer_name")
    private String freelancerName;

    @Column(name = "freelancer_email")
    private String freelancerEmail;

    @Column(name = "freelancer_user_id", nullable = false)
    private Long freelancerUserId;

    /* ---------- Client Details ---------- */
    @Column(name = "client_email")
    private String clientEmail;

    @Column(name = "client_user_id", nullable = false)
    private Long clientUserId;

    /* ---------- Status ---------- */
    @Column(nullable = false)
    private String status;
    // PENDING, ACCEPTED, REJECTED, COMPLETED

    /* ---------- Constructors ---------- */
    public Application() {}

    /* ---------- Getters & Setters ---------- */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getFreelancerName() {
        return freelancerName;
    }

    public void setFreelancerName(String freelancerName) {
        this.freelancerName = freelancerName;
    }

    public String getFreelancerEmail() {
        return freelancerEmail;
    }

    public void setFreelancerEmail(String freelancerEmail) {
        this.freelancerEmail = freelancerEmail;
    }

    public Long getFreelancerUserId() {
        return freelancerUserId;
    }

    public void setFreelancerUserId(Long freelancerUserId) {
        this.freelancerUserId = freelancerUserId;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public Long getClientUserId() {
        return clientUserId;
    }

    public void setClientUserId(Long clientUserId) {
        this.clientUserId = clientUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
