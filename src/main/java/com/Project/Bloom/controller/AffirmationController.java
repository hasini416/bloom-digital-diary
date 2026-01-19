package com.Project.Bloom.controller;

import com.Project.Bloom.dto.AffirmationResponse;
import com.Project.Bloom.model.User;
import com.Project.Bloom.service.AffirmationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/affirmations")
@RequiredArgsConstructor
public class AffirmationController {

    private final AffirmationService affirmationService;

    @GetMapping("/today")
    public ResponseEntity<AffirmationResponse> getTodayAffirmation(
            Authentication authentication
    ){
        System.out.println("AFFIRMATION CONTROLLER HIT");
        User user = (User) authentication.getPrincipal();

        return ResponseEntity.ok(affirmationService.getTodayAffirmation(user));
    }
}
