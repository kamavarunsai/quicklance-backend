package com.quicklance.backend.repository;

import com.quicklance.backend.entity.Application;

import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    // ✅ Used by ApplicationController (client view)
    List<Application> findByClientEmail(String email);

    // ✅ Used by ApplicationController (freelancer view)
    List<Application> findByFreelancerEmail(String email);

    // ✅ Used when job is completed
    @Modifying
    @Transactional
    @Query("UPDATE Application a SET a.status = :status WHERE a.jobId = :jobId")
    void updateStatusByJobId(
        @Param("jobId") Long jobId,
        @Param("status") String status
    );

    // ✅ Freelancer analytics
    long countByFreelancerUserId(Long freelancerUserId);

    long countByFreelancerUserIdAndStatus(
        Long freelancerUserId,
        String status
    );
}
