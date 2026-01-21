package com.quicklance.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import com.quicklance.backend.model.Rating;
import com.quicklance.backend.repository.ApplicationRepository;
import com.quicklance.backend.repository.RatingRepository;

@RestController
@RequestMapping("/api/freelancer-analytics")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class FreelancerAnalyticsController {

    private final ApplicationRepository applicationRepo;
    private final RatingRepository ratingRepo;

    @GetMapping("/{freelancerUserId}")
    public Map<String, Object> getAnalytics(@PathVariable Long freelancerUserId) {

        long applied = applicationRepo.countByFreelancerUserId(freelancerUserId);
        long accepted = applicationRepo.countByFreelancerUserIdAndStatus(
                freelancerUserId, "ACCEPTED");
        long rejected = applicationRepo.countByFreelancerUserIdAndStatus(
                freelancerUserId, "REJECTED");

        // ⭐ Rating
        List<Rating> ratings = ratingRepo.findByFreelancerUserId(freelancerUserId);
        double avgRating = ratings.isEmpty()
                ? 0
                : ratings.stream()
                         .mapToInt(Rating::getStars)
                         .average()
                         .orElse(0);

        int earnings = (int) accepted * 5000;

        Map<String, Object> res = new HashMap<>();
        res.put("applied", applied);
        res.put("accepted", accepted);
        res.put("rejected", rejected);
        res.put("rating", avgRating);
        res.put("earnings", earnings);

        return res;
    }
}
