package com.Project.Bloom.controller;


import com.Project.Bloom.dto.HabitRequest;
import com.Project.Bloom.model.Habit;
import com.Project.Bloom.model.HabitLog;
import com.Project.Bloom.model.User;
import com.Project.Bloom.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/habits")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @PostMapping
    public ResponseEntity<Habit> createHabit(@RequestBody HabitRequest habitRequest,
                                             Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(habitService.createhabit(user, habitRequest.getTitle()));

    }

    @PostMapping("{habitId}/today")
    public ResponseEntity<HabitLog> toggleHabit(
            @PathVariable Long habitId,
            @RequestParam boolean completed,
            Authentication authentication
    ){
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(habitService.toggleTodayHabit(user,habitId,completed));
    }

    @GetMapping
    public ResponseEntity<List<Habit>> getHabits(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(habitService.getUserHabits(user));
    }

}
