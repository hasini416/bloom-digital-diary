package com.Project.Bloom.controller;


import com.Project.Bloom.model.Goal;
import com.Project.Bloom.model.User;
import com.Project.Bloom.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/goals")
@RequiredArgsConstructor
public class GoalController {

    private final GoalService goalService;

    @PostMapping
    public ResponseEntity<Goal> createGoal(@RequestBody Goal goal , Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        return ResponseEntity.ok(goalService.createGoal(user,goal));
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<Goal> completeGoal(@PathVariable Integer id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(goalService.completeGoal(user,id));
    }


    @GetMapping("/active")
    public ResponseEntity<List<Goal>> getActiveGoals(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(goalService.getActiveGoals(user));
    }


}
