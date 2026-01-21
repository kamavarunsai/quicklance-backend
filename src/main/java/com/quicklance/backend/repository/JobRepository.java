package com.quicklance.backend.repository;

import com.quicklance.backend.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByStatus(String status);

    List<Job> findByClientEmail(String clientEmail);
}
