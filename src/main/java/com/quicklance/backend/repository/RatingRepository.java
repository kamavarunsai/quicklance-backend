package com.quicklance.backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.quicklance.backend.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByFreelancerUserId(Long freelancerUserId);
}
