package com.quicklance.backend.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.quicklance.backend.model.ClientProfile;
import com.quicklance.backend.repository.ClientProfileRepository;

@RestController
@RequestMapping("/api/client-profile")
@RequiredArgsConstructor
@CrossOrigin
public class ClientProfileController {

    private final ClientProfileRepository repo;

    @PostMapping
    public ClientProfile saveProfile(@RequestBody ClientProfile profile) {

        return repo.findByUserId(profile.getUserId())
                .map(existing -> {
                    existing.setCompany(profile.getCompany());
                    existing.setIndustry(profile.getIndustry());
                    existing.setWebsite(profile.getWebsite());
                    existing.setLocation(profile.getLocation());
                    existing.setAbout(profile.getAbout());
                    existing.setContact(profile.getContact());
                    return repo.save(existing);
                })
                .orElseGet(() -> repo.save(profile));
    }

    @GetMapping("/{userId}")
    public ClientProfile getProfile(@PathVariable Long userId) {
        return repo.findByUserId(userId).orElse(null);
    }
}
