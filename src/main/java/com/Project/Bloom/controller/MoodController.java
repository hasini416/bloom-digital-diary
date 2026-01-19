package com.Project.Bloom.controller;

import com.Project.Bloom.dto.MoodRequest;
import com.Project.Bloom.model.Mood;
import com.Project.Bloom.model.User;
import com.Project.Bloom.service.MoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/moods")
@RequiredArgsConstructor
public class MoodController {

    private final MoodService moodService;

    @PostMapping
    public ResponseEntity<Mood> saveMood(@RequestBody MoodRequest moodRequest,
                                         Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(moodService.saveMood(user,moodRequest.getMoodType(),moodRequest.getNote()));

    }

    @GetMapping("/today")
    public ResponseEntity<Mood> getTodayMood(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(moodService.getTodayMood(user));
    }




}
