package com.quicklance.backend.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.quicklance.backend.model.Rating;
import com.quicklance.backend.repository.RatingRepository;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://quicklancefrontend.vercel.app")
public class RatingController {

    private final RatingRepository repo;

    // client submits rating
    @PostMapping
    public Rating addRating(@RequestBody Rating rating) {
        return repo.save(rating);
    }

    // get ratings for freelancer
    @GetMapping("/freelancer/{freelancerUserId}")
    public Object getRatings(@PathVariable Long freelancerUserId) {
        return repo.findByFreelancerUserId(freelancerUserId);
    }
}
