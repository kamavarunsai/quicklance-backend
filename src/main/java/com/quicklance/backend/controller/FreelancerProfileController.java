package com.quicklance.backend.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.quicklance.backend.model.FreelancerProfile;
import com.quicklance.backend.repository.FreelancerProfileRepository;

@RestController
@RequestMapping("/api/freelancer-profile")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class FreelancerProfileController {

    private final FreelancerProfileRepository repo;

    // Save or Update
    @PostMapping
    public FreelancerProfile saveProfile(@RequestBody FreelancerProfile profile) {
        return repo.findByUserId(profile.getUserId())
                .map(existing -> {
                    existing.setFullName(profile.getFullName());
                    existing.setTitle(profile.getTitle());
                    existing.setSkills(profile.getSkills());
                    existing.setExperience(profile.getExperience());
                    existing.setPortfolio(profile.getPortfolio());
                    existing.setLocation(profile.getLocation());
                    existing.setAbout(profile.getAbout());
                    existing.setContact(profile.getContact());
                    return repo.save(existing);
                })
                .orElseGet(() -> repo.save(profile));
    }

    // Fetch
    @GetMapping("/{userId}")
    public FreelancerProfile getProfile(@PathVariable Long userId) {
        return repo.findByUserId(userId).orElse(null);
    }
}
